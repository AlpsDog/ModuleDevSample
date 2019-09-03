package com.wonly.lib_common.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * @Author: HSL
 * @Time: 2019/9/3 11:17
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
@Entity
public class SearchEntity {

    @Id(autoincrement = true)
    private Long id;
    /**
     * 搜索内容
     */
    @Unique
    private String content;
    /**
     * 时间戳
     */
    private long date;
    @Generated(hash = 723310565)
    public SearchEntity(Long id, String content, long date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }
    @Generated(hash = 1021466028)
    public SearchEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public long getDate() {
        return this.date;
    }
    public void setDate(long date) {
        this.date = date;
    }
}
