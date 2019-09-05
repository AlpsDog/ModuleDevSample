package com.wonly.lib_base.net.interceptor;

import android.text.TextUtils;

import com.wonly.lib_base.net.HttpConstant;
import com.wonly.lib_base.utils.SPUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: HSL
 * @Time: 2019/9/2 10:36
 * @E-mail: xxx@163.com
 * @Description: 设置请求头
 */
public class HeaderInterceptor implements Interceptor {

    private HashMap<String, Object> headers;

    public HeaderInterceptor() {
    }

    public HeaderInterceptor(HashMap<String, Object> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        // 如果公共请求头不为空,则构建新的请求
        if (headers != null) {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue().toString());
            }
        }
        //添加Token
        Request requestB = requestBuilder.build();
        Response.Builder responseBuilder = chain.proceed(requestB).newBuilder();
        if (!TextUtils.isEmpty(requestB.cacheControl().toString())) {
            responseBuilder
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + requestB.cacheControl().maxAgeSeconds());
        }
        return responseBuilder.build();
    }
}
