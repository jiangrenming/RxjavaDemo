package com.nld.rxjavademo.local.impl;

import com.nld.rxjavademo.local.BaseDao;
import com.nld.rxjavademo.local.UserDao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 */

public class UserDaoImp extends BaseDao implements UserDao {


    public  UserDaoImp(Realm realm){
        super(realm);
    }

    @Override
    public void insert(RealmObject realmModel) {
        super.insert(realmModel);
    }

    @Override
    public void insert(List<? extends RealmObject> realms) {

    }

    @Override
    public RealmModel query(String key) {
        return null;
    }

    @Override
    public List<? extends RealmObject> queryAll() {
        return null;
    }

    @Override
    public void delete(String key, RealmObject realmModel) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void upDate(String key) {

    }
}
