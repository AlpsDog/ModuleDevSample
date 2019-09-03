package com.wonly.lib_common.db.manager;

import com.wonly.lib_base.application.BaseApplication;
import com.wonly.lib_common.dao.SearchEntityDao;
import com.wonly.lib_common.db.DbManager;
import com.wonly.lib_common.db.entity.SearchEntity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @Author: HSL
 * @Time: 2019/9/3 11:16
 * @E-mail: xxx@163.com
 * @Description: 这个人太懒，没留下什么踪迹~
 */
public class SearchManager {

    private static final SearchEntityDao SEARCH_ENTITY_DAO;

    static {
        SEARCH_ENTITY_DAO = DbManager.getDaoSession(BaseApplication.getInstance()).getSearchEntityDao();
    }

    /**
     * 删除数据
     */
    public static void deleteData(String content) {
        SearchEntity entity = queryHistory(content);
        if (entity != null) {
            SEARCH_ENTITY_DAO.delete(entity);
        }
    }

    /**
     * 删除所有
     */
    public static void deleteAllData() {
        SEARCH_ENTITY_DAO.deleteAll();
    }

    /**
     * 根据条件查询
     *
     * @return
     */
    public static SearchEntity queryHistory(String content) {
        SearchEntity entity = null;
        QueryBuilder<SearchEntity> queryBuilder = SEARCH_ENTITY_DAO.queryBuilder();
        List<SearchEntity> entities = queryBuilder
                .where(SearchEntityDao.Properties.Content.eq(content))
                .list();
        if (!entities.isEmpty()) {
            entity = entities.get(0);
        }
        return entity;
    }

    public static List<SearchEntity> queryAllHistory() {
        QueryBuilder<SearchEntity> queryBuilder = SEARCH_ENTITY_DAO.queryBuilder();
        List<SearchEntity> entities = queryBuilder
                .list();
        if (!entities.isEmpty()) {
            return entities;
        }
        return null;
    }

    /**
     * 添加数据至数据库
     * 如果存在，将原来的数据覆盖
     *
     * @param content
     */
    public static void saveSearch(String content) {
        SearchEntity entity = queryHistory(content);
        if (entity == null) {
            SearchEntity searchEntity = new SearchEntity();
            searchEntity.setContent(content);
            SEARCH_ENTITY_DAO.insert(searchEntity);
        } else {
            entity.setContent(content);
            SEARCH_ENTITY_DAO.update(entity);
        }
    }
}
