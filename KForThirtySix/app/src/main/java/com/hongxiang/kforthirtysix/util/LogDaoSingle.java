package com.hongxiang.kforthirtysix.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hongxiang.kforthirtysix.logsql.DaoMaster;
import com.hongxiang.kforthirtysix.logsql.DaoSession;

import com.hongxiang.kforthirtysix.logsql.LogDao;

/**
 * Created by dllo on 16/5/28.
 */
public class LogDaoSingle {
    private SQLiteDatabase db;//数据库
    private DaoMaster daoMaster;//管理者
    private DaoSession daoSession;//回话者
    private LogDao logDao;//数据库内相应表的操作对象
    private Context context;
    private DaoMaster.DevOpenHelper helper;

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "Log.db", null);
        }
        return helper;
    }

    private static LogDaoSingle ourInstance = new LogDaoSingle();

    public static LogDaoSingle getInstance() {
        return ourInstance;
    }

    private LogDaoSingle() {
        context = MyApplication.getContext();
    }

    public SQLiteDatabase getDb() {
        if (db == null) {
            db = getHelper().getWritableDatabase();
        }
        return db;
    }

    public DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            daoMaster = new DaoMaster(getDb());
        }
        return daoMaster;
    }

    public DaoSession getDaoSession() {
        if (daoSession == null) {
            daoSession = getDaoMaster().newSession();
        }
        return daoSession;
    }

    public LogDao getLogDao() {
        if (logDao == null) {
            logDao = getDaoSession().getLogDao();
        }
        return logDao;
    }
}
