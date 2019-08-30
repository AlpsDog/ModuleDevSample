package com.wonly.lib_base.utils;

import com.orhanobut.logger.Logger;
import com.wonly.lib_base.application.BaseApplication;

import java.io.File;
import java.util.Locale;

/**
 * @Description:应用缓存处理 包括：内部缓存 内部文件 外部缓存 自定义目录
 * 不包括：内部SP 内部Sqlite
 */
public class AppCacheUtils {

    /**
     * 清除缓存
     */
    public static void clearAllCache() {
        try {
            //清除内部缓存
            //directory: /data/data/package/cache
            CleanUtils.cleanInternalCache();
            //清除内部文件
            //directory: /data/data/package/file
            CleanUtils.cleanInternalFiles();
            //清除外部缓存
            //directory: /storage/emulated/0/android/data/package/cache
            CleanUtils.cleanExternalCache();
            //清除自定义目录
            FileUtils.deleteAllInDir(CatalogUtils.getRootDir());
        } catch (Exception e) {
            Logger.e("clear all cache error:%s", e);
        }
    }

    /**
     * 获取缓存大小
     *
     * @return
     */
    public static String getCacheSize() {
        //内部缓存
        //directory: /data/data/package/cache
        long dirCacheSize = FileUtils.getDirLength(BaseApplication.getInstance().getCacheDir());
        //内部文件
        long dirFileSize = FileUtils.getDirLength(BaseApplication.getInstance().getFilesDir());
        //外部缓存
        //directory: /storage/emulated/0/android/data/package/cache
        long dirSDCacheSize = 0;
        if (SDCardUtils.hasSDCard()) {
            dirSDCacheSize = FileUtils.getDirLength(BaseApplication.getInstance().getExternalCacheDir());
        }
        //自定义目录
        long dirCustomSize = 0;
        File customDir = CatalogUtils.getRootDir();
        if (!ObjectUtils.isEmpty(customDir)) {
            dirCustomSize = FileUtils.getDirLength(customDir);
        }
        long allCacheSize = dirCacheSize + dirFileSize + dirSDCacheSize + dirCustomSize;
        return byte2FitMemorySize(allCacheSize);
    }

    private static String byte2FitMemorySize(final long byteNum) {
        if (byteNum < 0) {
            return "shouldn't be less than zero!";
        } else if (byteNum < 1024) {
            return String.format(Locale.getDefault(), "%.3fB", (double) byteNum);
        } else if (byteNum < 1048576) {
            return String.format(Locale.getDefault(), "%.3fKB", (double) byteNum / 1024);
        } else if (byteNum < 1073741824) {
            return String.format(Locale.getDefault(), "%.3fMB", (double) byteNum / 1048576);
        } else {
            return String.format(Locale.getDefault(), "%.3fGB", (double) byteNum / 1073741824);
        }
    }
}
