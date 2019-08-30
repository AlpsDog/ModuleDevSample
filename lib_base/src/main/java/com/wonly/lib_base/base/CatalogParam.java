package com.wonly.lib_base.base;

/**
 * @Author: HSL
 * @Time: 2019/8/29 15:53
 * @E-mail: xxx@163.com
 * @Description: 应用目录~
 */
public class CatalogParam {

    /**
     * app目录
     */
    private String appDir = "loadMarket";
    /**
     * 图片目录
     */
    private String imagesDir = "images";
    /**
     * 语音文件
     */
    private String audiosDir = "audios";
    /**
     * 视频文件
     */
    private String videoDir = "videos";
    /**
     * 错误日志目录
     */
    private String errorsDir = "errors";
    /**
     * 信息日志目录
     */
    private String InfosDir = "infos";
    /**
     * 二维码
     */
    private String qrcodesDir = "qrcodes";
    /**
     * oss持久保存记录目录
     */
    private String ossRecord = "ossrecord";
    /**
     * 默认下载路径
     */
    private String download = "download";

    /**
     * 获取app目录
     */
    public String getAppDir() {
        return appDir;
    }

    /**
     * 设置app目录
     * <p>
     * param appDir
     */
    public void setAppDir(String appDir) {
        this.appDir = appDir;
    }

    /**
     * 获取图片目录
     */
    public String getImagesDir() {
        return imagesDir;
    }

    /**
     * 获取语音文件
     */
    public String getAudiosDir() {
        return audiosDir;
    }

    /**
     * 获取视频文件
     */
    public String getVideoDir() {
        return videoDir;
    }

    /**
     * 获取错误日志目录
     */
    public String getErrorsDir() {
        return errorsDir;
    }

    /**
     * 获取信息日志目录
     */
    public String getInfosDir() {
        return InfosDir;
    }

    /**
     * 获取二维码
     */
    public String getQrcodesDir() {
        return qrcodesDir;
    }

    /**
     * 获取oss持久保存记录目录
     */
    public String getOssRecord() {
        if (ossRecord == null) {
            ossRecord = "";
        }
        return ossRecord;
    }

    /**
     * 设置oss持久保存记录目录
     * <p>
     * param ossRecord
     */
    public void setOssRecord(String ossRecord) {
        this.ossRecord = ossRecord;
    }

    public String getDownload() {
        return download;
    }
}
