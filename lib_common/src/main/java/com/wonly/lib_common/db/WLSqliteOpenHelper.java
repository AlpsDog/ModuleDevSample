package com.wonly.lib_common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.github.yuweiguocn.library.greendao.MigrationHelper;
import com.wonly.lib_common.dao.CacheEntityDao;
import com.wonly.lib_common.dao.DaoMaster;
import com.wonly.lib_common.dao.SearchEntityDao;

import org.greenrobot.greendao.database.Database;

/**
 * @Author: HSL
 * @Time: 2019/9/3 11:15
 * @E-mail: xxx@163.com
 * @Description: 添加任意一张表，均在此处添加其dao的类类型，以确保正常升级
 */
public class WLSqliteOpenHelper extends DaoMaster.OpenHelper {

    public WLSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

                    @Override
                    public void onCreateAllTables(Database db, boolean ifNotExists) {
                        DaoMaster.createAllTables(db, ifNotExists);
                    }

                    @Override
                    public void onDropAllTables(Database db, boolean ifExists) {
                        DaoMaster.dropAllTables(db, ifExists);
                    }
                }, SearchEntityDao.class, CacheEntityDao.class
        );
    }
}
