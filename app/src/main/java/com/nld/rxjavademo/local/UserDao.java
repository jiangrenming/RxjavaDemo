package com.nld.rxjavademo.local;

import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 */

public interface UserDao {
    //单一插入
    void insert(RealmObject realmModel);
    //批量插入
    void insert(List<? extends RealmObject> realms);
    //查询一条数据
    RealmModel query(String key);
    //查询所有的数据
    List<? extends RealmObject> queryAll();
    //删除一条数据
    void delete(String key,RealmObject realmModel);
    //删除所有的数据
    void deleteAll();
    //更新数据
    void upDate(String key);

}
