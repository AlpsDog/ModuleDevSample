package com.wonly.lib_common.db.manager;

import com.wonly.lib_base.application.BaseApplication;
import com.wonly.lib_base.utils.JsonUtils;
import com.wonly.lib_base.utils.ObjectUtils;
import com.wonly.lib_common.dao.CacheEntityDao;
import com.wonly.lib_common.db.DbManager;
import com.wonly.lib_common.db.entity.CacheEntity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @Author: HSL
 * @Time: 2019/9/3 11:06
 * @E-mail: xxx@163.com
 * @Description: 数据库缓存工具类
 */
public class CacheManager {

    private static final CacheEntityDao CACHE_ENTITY_DAO;

    static {
        CACHE_ENTITY_DAO = DbManager.getDaoSession(BaseApplication.getInstance()).getCacheEntityDao();
    }

    /**
     * 删除数据
     *
     * @param key
     */
    public static void deleteData(String key) {
        CacheEntity entity = queryCache(key);
        if (entity != null) {
            CACHE_ENTITY_DAO.delete(entity);
        }
    }

    /**
     * 删除所有
     */
    public static void deleteAllData() {
        CACHE_ENTITY_DAO.deleteAll();
    }

    /**
     * 查询数据
     *
     * @param key
     * @return
     */
    public static String queryCacheJson(String key) {
        CacheEntity cacheEntity = queryCache(key);
        if (!ObjectUtils.isEmpty(cacheEntity)) {
            return cacheEntity.getCacheData();
        }
        return "";
    }

    /**
     * 查询数据
     *
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T queryCacheBean(String key, Class<T> cls) {
        T data = null;
        CacheEntity cacheEntity = queryCache(key);
        if (!ObjectUtils.isEmpty(cacheEntity) &&
                !ObjectUtils.isEmpty(cacheEntity.getCacheData())) {
            data = JsonUtils.parseT(cacheEntity.getCacheData(), cls);
        }
        return data;
    }

    /**
     * 查询数据
     *
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> queryCacheList(String key, Class<T> cls) {
        List<T> data = null;
        CacheEntity cacheEntity = queryCache(key);
        if (!ObjectUtils.isEmpty(cacheEntity) &&
                !ObjectUtils.isEmpty(cacheEntity.getCacheData())) {
            data = JsonUtils.parseArray(cacheEntity.getCacheData(), cls);
        }
        return data;
    }

    /**
     * 根据条件查询
     *
     * @param key
     * @return
     */
    private static CacheEntity queryCache(String key) {
        CacheEntity entity = null;
        QueryBuilder<CacheEntity> queryBuilder = CACHE_ENTITY_DAO.queryBuilder();
        List<CacheEntity> entities = queryBuilder
                .where(CacheEntityDao.Properties.Key.eq(key))
                .list();
        if (!entities.isEmpty()) {
            entity = entities.get(0);
        }
        return entity;
    }

    /**
     * 添加数据至数据库
     * 如果存在，将原来的数据覆盖
     *
     * @param key        标识
     * @param cacheJson  缓存json
     * @param api        接口
     * @param pagerClass 页面
     */
    public static void saveCache(String key, String cacheJson, String api, String pagerName) {
        CacheEntity entity = queryCache(key);
        if (entity == null) {
            CacheEntity cacheEntity = new CacheEntity();
            cacheEntity.setKey(key);
            cacheEntity.setCacheData(cacheJson);
            cacheEntity.setApi(api);
            cacheEntity.setPagerClass(pagerName);
            cacheEntity.setUpdate(System.currentTimeMillis());
            CACHE_ENTITY_DAO.insert(cacheEntity);
        } else {
            entity.setKey(key);
            entity.setCacheData(cacheJson);
            entity.setApi(api);
            entity.setPagerClass(pagerName);
            entity.setUpdate(System.currentTimeMillis());
            CACHE_ENTITY_DAO.update(entity);
        }
    }

    /**
     * 添加数据至数据库
     * 如果存在，将原来的数据覆盖
     *
     * @param key
     * @param cacheJson
     */
    public static void saveCache(String key, String cacheJson) {
        saveCache(key, cacheJson, "", "");
    }
}
