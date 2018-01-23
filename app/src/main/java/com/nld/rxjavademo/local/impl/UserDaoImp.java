package com.nld.rxjavademo.local.impl;

import android.util.Log;
import com.nld.rxjavademo.local.BaseDao;
import com.nld.rxjavademo.local.UserDao;
import com.nld.rxjavademo.local.bean.User;
import java.util.List;
import java.util.Map;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * @author jiangrenming
 * @date 2018/1/17
 */

public class UserDaoImp extends BaseDao implements UserDao {


    private static final String TAG = UserDaoImp.class.getName();

    public UserDaoImp(Realm realm){
        super(realm,null);
    }
    public  UserDaoImp(Realm realm,String tableName){
        super(realm,tableName);
    }

    /**
     * 单条插入
     * @param realmModel
     * @return
     */
    @Override
    public int insert(RealmObject realmModel) {
       return  super.insert(realmModel);
    }

    /**
     * 批量插入
     * @param realms
     * @return
     */
    @Override
    public RealmAsyncTask insert(List<? extends RealmObject> realms) {
        return super.insert(realms);
    }

    /**
     * 查询部分数据
     * @param clazz
     * @param values
     * @return
     */
    @Override
    public RealmResults<User> query(Class clazz, Map<String, Object> values) {
        try{
            RealmResults<User> query = (RealmResults<User>) super.query(clazz, values);
            if (!query.isEmpty()){
                return  query;
            }
        }catch (Exception e){
            Log.e(TAG,"转换数据失败");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询所有的数据
     * @param clazz
     * @return
     */
    @Override
    public RealmResults<User> queryAll(Class clazz) {
        try{
            RealmResults<User> users = (RealmResults<User>) super.queryAll(clazz);
            if (!users.isEmpty()){
                return  users;
            }
        }catch (Exception e){
            Log.e(TAG,"转换数据失败");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 删除符合条件的数据
     * @param clazz
     * @param values
     */
    @Override
    public void delete(Class clazz, Map<String,Object> values) {
        try{
            super.delete(clazz,values);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 删除所有的数据
     * @param clazz
     */
    @Override
    public void deleteAll(Class clazz) {
        try{
            super.delete(clazz,null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void upDate(String value,Class clazz,Map<String, Object> values) {
        super.update(value,clazz,values);
    }
}
