package com.nld.rxjavademo.local;

import com.nld.rxjavademo.local.bean.User;

import java.util.List;
import java.util.Map;
import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 */

public interface UserDao {
    //单一插入
    int insert(RealmObject realmModel);
    //批量插入
    RealmAsyncTask insert(List<? extends RealmObject> realms);
    //查询部分数据
    RealmResults<User> query(Class clazz, Map<String,Object> values);
    //查询所有的数据
    RealmResults<User> queryAll(Class clazz);
    //删除一条数据
    void delete(Class clazz, Map<String,Object> values);
    //删除所有的数据
    void deleteAll(Class clazz);
    //更新数据
    void upDate(String value,Class clazz,Map<String, Object> values);

}
