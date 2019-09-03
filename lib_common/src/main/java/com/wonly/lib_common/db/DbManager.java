package com.wonly.lib_common.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wonly.lib_common.dao.DaoMaster;
import com.wonly.lib_common.dao.DaoSession;


/**
 * @Description:数据库工具类
 */
public class DbManager {
    // 是否加密
    public static final boolean ENCRYPTED = true;

    private static final String DB_NAME = "wonly.db";
    private static DbManager mDbManager;
    private static DaoMaster.OpenHelper mOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private Context mContext;

    private DbManager(Context context) {
        this.mContext = context;
        // 初始化数据库信息
        mOpenHelper = new WLSqliteOpenHelper(context, DB_NAME, null);
        getDaoMaster(context);
        getDaoSession(context);
    }

    public static DbManager getInstance(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context);
                }
            }
        }
        return mDbManager;
    }

    /**
     * @desc 获取可读数据库
     * @autor Tiany
     * @time 2016/8/13
     **/
    private static SQLiteDatabase getReadableDatabase(Context context) {
        if (null == mOpenHelper) {
            getInstance(context);
        }
        return mOpenHelper.getReadableDatabase();
    }

    /**
     * @desc 获取可写数据库
     * @autor Tiany
     * @time 2016/8/13
     **/
    private static SQLiteDatabase getWritableDatabase(Context context) {
        if (null == mOpenHelper) {
            getInstance(context);
        }
        return mOpenHelper.getWritableDatabase();
    }

    /**
     * @desc 获取DaoMaster
     * @autor Tiany
     * @time 2016/8/13
     **/
    private static DaoMaster getDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    mDaoMaster = new DaoMaster(getWritableDatabase(context));
                }
            }
        }
        return mDaoMaster;
    }

    /**
     * @desc 获取DaoSession
     * @autor Tiany
     * @time 2016/8/13
     **/
    public static DaoSession getDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster(context).newSession();
            }
        }
        return mDaoSession;
    }
}
