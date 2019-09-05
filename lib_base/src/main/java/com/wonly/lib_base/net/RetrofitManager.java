package com.wonly.lib_base.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.orhanobut.logger.Logger;
import com.wonly.lib_base.BuildConfig;
import com.wonly.lib_base.application.BaseApplication;
import com.wonly.lib_base.net.cert.TrustAllCerts;
import com.wonly.lib_base.net.cert.TrustAllHostnameVerifier;
import com.wonly.lib_base.net.cookie.CookieJarImpl;
import com.wonly.lib_base.net.cookie.store.PersistentCookieStore;
import com.wonly.lib_base.net.interceptor.CacheInterceptor;
import com.wonly.lib_base.net.interceptor.CookieInterceptor;
import com.wonly.lib_base.net.interceptor.HeaderInterceptor;
import com.wonly.lib_base.net.interceptor.ParameterInterceptor;
import com.wonly.lib_base.net.interceptor.log.HttpLoggingInterceptor;
import com.wonly.lib_base.utils.SPUtils;

import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

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
    //gson
    private static final Gson GSON;

    static {
        //当使用GsonBuilder方式时属性为空的时候输出来的json字符串是有键值key的,显示形式是"key":null，
        //而直接new出来的就没有"key":null的
        GSON = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
    }

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
        Logger.t("retrofit").d(mRetrofitService + "\n" + mRetrofit);
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
                            .addConverterFactory(GsonConverterFactory.create(GSON))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    mRetrofit.put(baseUrl, retrofit);
                }
            }
        }
        return retrofit;
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

        //使用sp保持cookie，如果cookie不过期，则一直有效
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(BaseApplication.getInstance()));
        //拦截器自定义cookie处理
        CookieInterceptor cookieInterceptor = new CookieInterceptor();

        //公共头部:非中文
        HashMap<String, Object> headers = new HashMap<>(1);
        headers.put("os", SPUtils.getInstance().getString("hsl"));

        //注意LOG拦截顺序
        OkHttpClient okHttpClient = builder
                .cookieJar(cookieJar)
                .cache(cache) // 添加缓存
                .addInterceptor(new HeaderInterceptor(headers))
                .addInterceptor(new ParameterInterceptor())
                .addInterceptor(new CacheInterceptor())
                .sslSocketFactory(createSSLSocketFactory(), new TrustAllCerts())// 创建一个证书对象
                .hostnameVerifier(new TrustAllHostnameVerifier()) // 校验名称,这个对象就是信任所有的主机,也就是信任所有https的请求
                .addInterceptor(loggingInterceptor)
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
