package com.nld.rxjavademo.local;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.nld.rxjavademo.base.BaseActivity;
import com.nld.rxjavademo.local.bean.Dog;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmObjectSchema;
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

    public BaseDao (Realm realm){
        this.mRealm = realm;
        Log.i(TAG,"名称是"+mRealm.getClass().getSimpleName()+"/"+mRealm.getSchema().toString());
    }

    /**
     * 单一插入
     * @param realmModel
     */
    public void insert(final RealmObject realmModel){
        try{
            mRealm.executeTransaction(getTransaction(realmModel));
        }catch (Exception e){
            e.printStackTrace();
            Log.i(TAG,e.getMessage());
        }
    }

    @NonNull
    private Realm.Transaction getTransaction(final RealmObject realmModel) {
        return new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                try {
                    realm.copyToRealmOrUpdate(realmModel);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 批量插入
     * @param realms
     */
    public  void insert (final List<? extends RealmObject> realms){
        try {
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(realms);
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
    }
}
