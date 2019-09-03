package com.wonly.lib_common.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @Author: HSL
 * @Time: 2019/9/3 11:17
 * @E-mail: xxx@163.com
 * @Description: 缓存实体类
 */
@Entity
public class CacheEntity {

    @Id(autoincrement = true)
    private Long id;

    /**
     * 缓存内容
     */
    private String cacheData;

    /**
     * 缓存的key
     */
    private String key;

    /**
     * 页面class
     */
    private String pagerClass;

    /**
     * 接口
     */
    private String api;

    /**
     * 更新时间
     */
    private long update;

    @Generated(hash = 17707170)
    public CacheEntity(Long id, String cacheData, String key, String pagerClass,
                       String api, long update) {
        this.id = id;
        this.cacheData = cacheData;
        this.key = key;
        this.pagerClass = pagerClass;
        this.api = api;
        this.update = update;
    }

    @Generated(hash = 1391258017)
    public CacheEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCacheData() {
        return this.cacheData;
    }

    public void setCacheData(String cacheData) {
        this.cacheData = cacheData;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPagerClass() {
        return this.pagerClass;
    }

    public void setPagerClass(String pagerClass) {
        this.pagerClass = pagerClass;
    }

    public String getApi() {
        return this.api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public long getUpdate() {
        return this.update;
    }

    public void setUpdate(long update) {
        this.update = update;
    }
}
