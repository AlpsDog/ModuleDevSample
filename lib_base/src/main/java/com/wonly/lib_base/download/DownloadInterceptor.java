package com.wonly.lib_base.download;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @Author: HSL
 * @Time: 2019/9/5 14:38
 * @E-mail: xxx@163.com
 * @Description: 处理数据
 */
public class DownloadInterceptor implements Interceptor {

    private DownloadListener downloadListener;

    DownloadInterceptor(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder().body(
                new DownloadResponseBody(response.body(), downloadListener))
                .build();
    }
}
