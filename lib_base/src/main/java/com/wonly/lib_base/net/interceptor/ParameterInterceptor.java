package com.wonly.lib_base.net.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: HSL
 * @Time: 2019/9/2 10:36
 * @E-mail: xxx@163.com
 * @Description: 接口通用参数，支持 GET、POST 方式
 * * 使用 {@link OkHttpClient.Builder#addInterceptor(Interceptor)} 这种方式为接口统一添加共通参数，如版本号、Token 等
 */
public class ParameterInterceptor implements Interceptor {

    private HashMap<String, Object> params;

    public ParameterInterceptor() {

    }

    public ParameterInterceptor(HashMap<String, Object> params) {
        this.params = params;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        if (request.method().equals("GET")) {
            // 为GET方式统一添加请求参数
            HttpUrl.Builder modifiedUrl = request.url().newBuilder()
                    .scheme(request.url().scheme())
                    .host(request.url().host());

            if (params != null && params.size() != 0) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    modifiedUrl.addQueryParameter(entry.getKey(), entry.getValue().toString());
                }
            }

            request = request.newBuilder()
                    .method(request.method(), request.body())
                    .url(modifiedUrl.build())
                    .build();

        } else if (request.method().equals("POST")) {
            // 为POST方式统一添加请求参数
            if (request.body() instanceof FormBody) {
                FormBody.Builder body = new FormBody.Builder();
                if (params != null && params.size() != 0) {
                    for (Map.Entry<String, Object> entry : params.entrySet()) {
                        body.addEncoded(entry.getKey(), entry.getValue().toString());
                    }
                }
                body.build();

                FormBody oldBody = (FormBody) request.body();
                if (oldBody != null && oldBody.size() != 0) {
                    for (int i = 0; i < oldBody.size(); i++) {
                        body.addEncoded(oldBody.encodedName(i), oldBody.encodedValue(i));
                    }
                }

                request = request.newBuilder()
                        .post(body.build())
                        .build();
            }
        }
        return chain.proceed(request);
    }
}
