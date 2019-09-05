package com.wonly.lib_base.download;

import java.io.File;

/**
 * @Author: HSL
 * @Time: 2019/9/5 14:38
 * @E-mail: xxx@163.com
 * @Description: 下载进度回调
 */
public interface DownloadListener {

    void onStartDownload();

    void onProgress(int progress);

    void onFinishDownload(File file);

    void onFail(Throwable ex);

}
