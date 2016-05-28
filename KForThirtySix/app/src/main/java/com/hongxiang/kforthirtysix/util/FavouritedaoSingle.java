package com.hongxiang.kforthirtysix.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.hongxiang.kforthirtysix.favouritesql.DaoMaster;
import com.hongxiang.kforthirtysix.favouritesql.DaoSession;
import com.hongxiang.kforthirtysix.favouritesql.FavouriteTextDao;

/**
 * Created by dllo on 16/5/25.
 */
public class FavouritedaoSingle {
    private SQLiteDatabase db;//数据库
    private DaoMaster daoMaster;//管理者
    private DaoSession daoSession;//回话者
    private FavouriteTextDao favouriteTextDao;//数据库内相应表的操作对象
    private Context context;
    private DaoMaster.DevOpenHelper helper;

    public DaoMaster.DevOpenHelper getHelper() {
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(context, "Favourite.db", null);
        }
        return helper;
    }
    private static FavouritedaoSingle ourInstance = new FavouritedaoSingle();

    public static FavouritedaoSingle getInstance() {
        return ourInstance;
    }

    private FavouritedaoSingle() {
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

    public FavouriteTextDao getFavouriteTextDao() {
        if (favouriteTextDao == null) {
            favouriteTextDao = getDaoSession().getFavouriteTextDao();
        }
        return favouriteTextDao;
    }

}
