package com.hongxiang.kforthirtysix.sql;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.hongxiang.kforthirtysix.sql.FavouriteText;

import com.hongxiang.kforthirtysix.sql.FavouriteTextDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig favouriteTextDaoConfig;

    private final FavouriteTextDao favouriteTextDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        favouriteTextDaoConfig = daoConfigMap.get(FavouriteTextDao.class).clone();
        favouriteTextDaoConfig.initIdentityScope(type);

        favouriteTextDao = new FavouriteTextDao(favouriteTextDaoConfig, this);

        registerDao(FavouriteText.class, favouriteTextDao);
    }
    
    public void clear() {
        favouriteTextDaoConfig.getIdentityScope().clear();
    }

    public FavouriteTextDao getFavouriteTextDao() {
        return favouriteTextDao;
    }

}
