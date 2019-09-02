package com.wonly.lib_base.net;

import com.wonly.lib_base.BuildConfig;
import com.wonly.lib_base.application.BaseApplication;
import com.wonly.lib_base.net.cert.TrustAllCerts;
import com.wonly.lib_base.net.cert.TrustAllHostnameVerifier;
import com.wonly.lib_base.net.interceptor.CacheInterceptor;
import com.wonly.lib_base.net.interceptor.CookieInterceptor;
import com.wonly.lib_base.net.interceptor.HeaderInterceptor;
import com.wonly.lib_base.net.interceptor.log.HttpLoggingInterceptor;

import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author chenxz
 * @date 2018/8/21
 * @desc RetrofitManager
 */
public class RetrofitManager {

    // 网络请求的超时时间
    private static final long DEFAULT_TIME_OUT = 30;
    // Service
    private HashMap<String, Object> mRetrofitService = new HashMap<>();
    // Retrofit实例
    private HashMap<String, Retrofit> mRetrofit = new HashMap<>();
    // cacheService
    private HashMap<String, Object> mCache = new HashMap<>();

    private RetrofitManager() {
    }

    private static final class RetrofitManagerHolder {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return RetrofitManagerHolder.INSTANCE;
    }

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param baseUrl
     * @param service
     * @param <T>
     * @return
     */
    public <T> T obtainRetrofitService(String baseUrl, Class<T> service) {
        T retrofitService = (T) mRetrofitService.get(service.getCanonicalName());
        if (retrofitService == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitService == null) {
                    retrofitService = createRetrofit(baseUrl).create(service);
                    mRetrofitService.put(service.getCanonicalName(), retrofitService);
                }
            }
        }
        return retrofitService;
    }

    /**
     * 根据BaseUrl创建对应的Retrofit
     *
     * @param baseUrl
     * @return
     */
    private Retrofit createRetrofit(String baseUrl) {
        Retrofit retrofit = mRetrofit.get(baseUrl);
        if (retrofit == null) {
            synchronized ((RetrofitManager.class)) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(baseUrl)
                            .client(createOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    mRetrofitService.put(baseUrl, retrofit);
                }
            }
        }
        return retrofit;
    }

    /**
     * 根据传入的 Class 获取对应的 RxCache service
     *
     * @param cache
     * @param <T>
     * @return
     */
    public <T> T obtainCacheService(Class<T> cache) {
        T cacheService = (T) mCache.get(cache.getCanonicalName());
        if (cacheService == null) {
            synchronized (RetrofitManager.class) {
                if (cacheService == null) {
                    cacheService = createCache().using(cache);
                    mCache.put(cache.getCanonicalName(), cacheService);
                }
            }
        }
        return cacheService;
    }

    private RxCache createCache() {
        RxCache rxCache = new RxCache.Builder()
                .persistence(BaseApplication.getInstance().getCacheDir(), new GsonSpeaker());
        return rxCache;
    }

    /**
     * 配置OKHttpClient
     */
    private static OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //缓存
        File cacheFile = new File(BaseApplication.getInstance().getCacheDir(), "cache");
        // 50M 缓存大小
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        //LOG
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);
        loggingInterceptor.setPrintLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        OkHttpClient okHttpClient = builder
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new CookieInterceptor())
                .addInterceptor(new CacheInterceptor())
                .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())// 创建一个证书对象
                .hostnameVerifier(new TrustAllHostnameVerifier()) // 校验名称,这个对象就是信任所有的主机,也就是信任所有https的请求
                .cache(cache) // 添加缓存
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true) // 连接不上是否重连
                .build();
        return okHttpClient;
    }

    /**
     * 实现 HTTPS 请求
     */
    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sslSocketFactory = null;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sslSocketFactory;
    }

}
