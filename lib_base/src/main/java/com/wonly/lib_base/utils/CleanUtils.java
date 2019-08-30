package com.wonly.lib_base.utils;

import android.os.Environment;

import com.wonly.lib_base.application.BaseApplication;
import java.io.File;

/**
 * 清除相关
 */
public final class CleanUtils {

    private CleanUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 清除内部缓存
     * context.getCacheDir()
     * directory: /data/data/package/cache</p>
     *
     * @return
     */
    public static boolean cleanInternalCache() {
        return deleteFilesInDir(BaseApplication.getInstance().getCacheDir());
    }

    /**
     * 清除内部文件
     * context.getFilesDir()
     * directory: /data/data/package/files
     *
     * @return
     */
    public static boolean cleanInternalFiles() {
        return deleteFilesInDir(BaseApplication.getInstance().getFilesDir());
    }

    /**
     * 清除内部数据库
     * directory: /data/data/package/databases
     *
     * @return
     */
    public static boolean cleanInternalDbs() {
        return deleteFilesInDir(new File(BaseApplication.getInstance().getFilesDir().getParent(), "databases"));
    }

    /**
     * 根据名称清除数据库
     * directory: /data/data/package/databases/dbName
     *
     * @param dbName The name of database.
     * @return
     */
    public static boolean cleanInternalDbByName(final String dbName) {
        return BaseApplication.getInstance().deleteDatabase(dbName);
    }

    /**
     * 清除内部 SP
     * directory: /data/data/package/shared_prefs
     *
     * @return
     */
    public static boolean cleanInternalSp() {
        return deleteFilesInDir(new File(BaseApplication.getInstance().getFilesDir().getParent(), "shared_prefs"));
    }

    /**
     * 清除外部缓存
     * 这个路径被Android系统认定为应用程序的缓存路径，当程序被卸载的时候，这里的数据也会一起被清除掉
     * directory: /storage/emulated/0/android/data/package/cache
     *
     * @return
     */
    public static boolean cleanExternalCache() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && deleteFilesInDir(BaseApplication.getInstance().getExternalCacheDir());
    }

    /**
     * 清除自定义目录下的文件
     *
     * @param dirPath The path of directory.
     * @return
     */
    public static boolean cleanCustomDir(final String dirPath) {
        return deleteFilesInDir(dirPath);
    }

    /**
     * 清除自定义目录下的文件
     *
     * @param dir The directory.
     * @return
     */
    public static boolean cleanCustomDir(final File dir) {
        return deleteFilesInDir(dir);
    }

    /**
     * 清除自定义目录下的文件
     *
     * @param dirPath
     * @return
     */
    public static boolean deleteFilesInDir(final String dirPath) {
        return deleteFilesInDir(getFileByPath(dirPath));
    }

    ///////////////////////////////////////////////////////////////////////////
    // other utils methods
    ///////////////////////////////////////////////////////////////////////////

    private static boolean deleteFilesInDir(final File dir) {
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return true;
    }

    private static boolean deleteDir(final File dir) {
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    /**
     * 字符串是否全为空格
     *
     * @param s
     * @return
     */
    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
