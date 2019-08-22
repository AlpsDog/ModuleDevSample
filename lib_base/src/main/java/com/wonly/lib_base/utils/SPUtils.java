package com.wonly.lib_base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;

import com.wonly.lib_base.application.BaseApplication;

import java.util.Collections;
import java.util.Map;
import java.util.Set;


/**
 * @Author: HouShengLi
 * @Time: 2018/9/28 10:32
 * @E-mail: 13967189624@163.com
 * @Description:SP工具类 commit提交到数据库，从提交数据到存在Disk中都是同步过程，中间不可打断。
 * apply提交内存中，而非数据库，所以在提交到内存中时不可打断，之后再异步提交数据到数据库中，因此也不会有相应的返回值。
 * 所有commit提交是同步过程，效率会比apply异步提交的速度慢，但是apply没有返回值，永远无法知道存储是否失败。
 * 在不关心提交结果是否成功的情况下，优先考虑apply方法。
 */
@SuppressLint("ApplySharedPref")
public final class SPUtils {

    private static final SimpleArrayMap<String, SPUtils> SP_UTILS_MAP = new SimpleArrayMap<>();
    private SharedPreferences sp;

    /**
     * 默认SP名称
     */
    public static SPUtils getInstance() {
        return getInstance("default_sp", Context.MODE_PRIVATE);
    }

    /**
     * 自定义名称
     * 区分sp时使用
     *
     * @param spName
     * @return
     */
    public static SPUtils getInstance(String spName) {
        return getInstance(spName, Context.MODE_PRIVATE);
    }

    /**
     * 自定义 名称 模式
     *
     * @param spName
     * @param mode
     * @return
     */
    public static SPUtils getInstance(String spName, final int mode) {
        if (isSpace(spName)) spName = "spUtils";
        SPUtils spUtils = SP_UTILS_MAP.get(spName);
        if (spUtils == null) {
            spUtils = new SPUtils(spName, mode);
            SP_UTILS_MAP.put(spName, spUtils);
        }
        return spUtils;
    }

    private SPUtils(final String spName, final int mode) {
        sp = BaseApplication.getInstance().getSharedPreferences(spName, mode);
    }

    /**
     * 存放String
     * 默认Commit提交
     *
     * @param key
     * @param value
     */
    public void putString(@NonNull final String key, final String value) {
        putString(key, value, false);
    }

    /**
     * put String
     *
     * @param key
     * @param value
     * @param isCommit
     */
    public void putString(@NonNull final String key, final String value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putString(key, value).commit();
        } else {
            sp.edit().putString(key, value).apply();
        }
    }

    /**
     * get String
     *
     * @param key
     * @return
     */
    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * get String
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public String getString(@NonNull final String key, final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * put Int
     *
     * @param key
     * @param value
     */
    public void putInt(@NonNull final String key, final int value) {
        putInt(key, value, false);
    }

    /**
     * put Int
     *
     * @param key
     * @param value
     * @param isCommit
     */
    public void putInt(@NonNull final String key, final int value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit();
        } else {
            sp.edit().putInt(key, value).apply();
        }
    }

    /**
     * get Int
     *
     * @param key
     * @return
     */
    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    /**
     * get Int
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInt(@NonNull final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * put Long
     *
     * @param key
     * @param value
     */
    public void putLong(@NonNull final String key, final long value) {
        putLong(key, value, false);
    }

    /**
     * put Long
     *
     * @param key
     * @param value
     * @param isCommit
     */
    public void putLong(@NonNull final String key, final long value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }

    /**
     * get Long
     *
     * @param key
     * @return
     */
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * get Long
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public long getLong(@NonNull final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * put Float
     *
     * @param key
     * @param value
     */
    public void putFloat(@NonNull final String key, final float value) {
        putFloat(key, value, false);
    }

    /**
     * put Float
     *
     * @param key
     * @param value
     * @param isCommit
     */
    public void putFloat(@NonNull final String key, final float value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit();
        } else {
            sp.edit().putFloat(key, value).apply();
        }
    }

    /**
     * get Float
     *
     * @param key
     * @return
     */
    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    /**
     * get Float
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public float getFloat(@NonNull final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * put boolean
     *
     * @param key
     * @param value
     */
    public void putBoolean(@NonNull final String key, final boolean value) {
        putBoolean(key, value, false);
    }

    /**
     * put boolean
     *
     * @param key
     * @param value
     * @param isCommit
     */
    public void putBoolean(@NonNull final String key, final boolean value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit();
        } else {
            sp.edit().putBoolean(key, value).apply();
        }
    }

    /**
     * get boolean
     *
     * @param key
     * @return
     */
    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * get boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * put set
     *
     * @param key
     * @param value
     */
    public void putSet(@NonNull final String key, final Set<String> value) {
        putSet(key, value, false);
    }

    /**
     * put set
     *
     * @param key
     * @param value
     * @param isCommit
     */
    public void putSet(@NonNull final String key,
                       final Set<String> value,
                       final boolean isCommit) {
        if (isCommit) {
            sp.edit().putStringSet(key, value).commit();
        } else {
            sp.edit().putStringSet(key, value).apply();
        }
    }

    /**
     * get set
     *
     * @param key
     * @return
     */
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * get set
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public Set<String> getStringSet(@NonNull final String key,
                                    final Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * get All
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * 是否包key键值对
     *
     * @param key
     * @return
     */
    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    /**
     * 移除某键值对
     *
     * @param key
     */
    public void remove(@NonNull final String key) {
        remove(key, false);
    }

    /**
     * 移除某键值对
     *
     * @param key
     * @param isCommit
     */
    public void remove(@NonNull final String key, final boolean isCommit) {
        if (isCommit) {
            sp.edit().remove(key).commit();
        } else {
            sp.edit().remove(key).apply();
        }
    }

    /**
     * 清除
     */
    public void clear() {
        clear(false);
    }

    /**
     * 清除
     *
     * @param isCommit
     */
    public void clear(final boolean isCommit) {
        if (isCommit) {
            sp.edit().clear().commit();
        } else {
            sp.edit().clear().apply();
        }
    }

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
