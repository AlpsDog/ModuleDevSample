package com.wonly.lib_base.download;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author: HSL
 * @Time: 2019/9/5 14:38
 * @E-mail: xxx@163.com
 * @Description: 下载文件的url接口
 */
public interface DownloadService {
    /**
     * 下载文件
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);//直接使用网址下载
}
