package com.nld.rxjavademo.local;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.nld.rxjavademo.local.bean.User;
import java.util.List;
import java.util.Map;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.RealmSchema;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 * 提取公用的增删改查操作
 */

public class BaseDao {

    private static  final String TAG = BaseDao.class.getName();

    private Realm mRealm;
    private String mTableName;

    public BaseDao (Realm realm,String tableName){
        this.mRealm = realm;
        this.mTableName = tableName;
    }

    /**
     * 单一插入
     * @param realmModel
     */
    public int insert(final RealmObject realmModel){
        try{
            mRealm.executeTransaction(getTransaction(realmModel));
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            Log.i(TAG,"插入数据库失败");
            return  -1;
        }
    }

    @NonNull
    private Realm.Transaction getTransaction(final RealmObject realmModel) {
        return new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmSchema realmSchema = realm.getSchema();
                if (realmSchema != null){
                    RealmObjectSchema realmObjectSchema = realmSchema.get(mTableName);
                    String primaryKey = realmObjectSchema.getPrimaryKey();
                    if (TextUtils.isEmpty(primaryKey)){
                        realm.copyToRealm(realmModel);
                    }else {
                        realm.copyToRealmOrUpdate(realmModel);
                    }
                }
            }
        };
    }

    /**
     * 批量插入
     * @param realms
     */
    public  RealmAsyncTask insert (final List<? extends RealmObject> realms){
        RealmAsyncTask realmAsyncTask = null;
        try {
             realmAsyncTask = mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmSchema realmSchema = realm.getSchema();
                    if (realmSchema != null) {
                        RealmObjectSchema realmObjectSchema = realmSchema.get(mTableName);
                        String primaryKey = realmObjectSchema.getPrimaryKey();
                        if (TextUtils.isEmpty(primaryKey)) {
                            realm.copyToRealm(realms);
                        } else {
                            realm.copyToRealmOrUpdate(realms);
                        }
                    }
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    Log.i(TAG, "插入成功");
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {
                    mRealm.cancelTransaction();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            Log.i(TAG,e.getMessage());
            mRealm.cancelTransaction();
        }
        return realmAsyncTask != null ? realmAsyncTask :null;
    }


    public RealmResults<? extends RealmObject> queryAll(Class clazz){
        return query(clazz,null);
    }

    public RealmResults<? extends RealmObject> query(Class clazz, Map<String,Object> values) {
        RealmQuery query = null;
        try{
            query = mRealm.where(clazz);
            if (values != null){
                for (Map.Entry<String,Object> entry: values.entrySet()){
                    if (entry.getValue() instanceof  String){
                        query.equalTo(entry.getKey(), String.valueOf(entry.getValue()));
                    }else if (entry.getValue() instanceof  Integer){
                        query.equalTo(entry.getKey(),Integer.valueOf(String.valueOf(entry.getValue())));
                    }else if (entry.getValue() instanceof Double){
                        query.equalTo(entry.getKey(),Double.valueOf(String.valueOf(entry.getValue())));
                    }else if (entry.getValue() instanceof  Boolean){
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return query.findAll();
    }

    /**
     * 更新数据<先查询再更新></>
     * @param value
     * @param clazz
     * @param values
     */
    public  void update(final String value, final Class clazz,final Map<String,Object> values){
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<? extends RealmObject> query = query(clazz, values);
                RealmObject realmObject = query.get(0);
                if (realmObject instanceof  User){
                    ((User) realmObject).setAge(Integer.valueOf(value));
                }
            }
        });
    }

    /**
     * 删除数据<包含符合条件的单挑数据以及所有的数据></>
     * @param clazz
     * @param values
     */
    public  void delete(Class clazz ,Map<String,Object> values){
        final RealmResults<? extends RealmObject> query = query(clazz, values);
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //移除所有符合条件的数据
                 query.deleteAllFromRealm();
            }
        });
    }

}
