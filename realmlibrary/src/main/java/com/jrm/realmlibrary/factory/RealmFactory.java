package com.jrm.realmlibrary.factory;

import android.content.Context;
import android.util.Log;
import java.security.SecureRandom;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 */

public class RealmFactory {

    private static  final String TAG = "RealmFactory";

    private static class  InnerRealm {
        private static  final  RealmFactory FACTORY = new RealmFactory();
    }

    public  static  RealmFactory getInstance(){
        return  InnerRealm.FACTORY;
    }


    /**
     * 初始化realm的数据库的版本号，名称配置
     * @param context
     * @param  dbVersion 默认版本号从0开始
     * @param dbName
     */
    public Realm initRealm(Context context, int dbVersion,String dbName){
        try{
            byte[] bytes = new byte[64];
            new SecureRandom().nextBytes(bytes);
            Realm.init(context);
            RealmConfiguration configuration = new RealmConfiguration.Builder()
                    .schemaVersion(dbVersion)
                    .name(dbName)
    //                .assetFile(context.getFilesDir().getName())  //数据库数据存储的路径
                    .migration(new MigrationUp())    //数据库升级
                    .deleteRealmIfMigrationNeeded()  //声明当出现版本冲突时自动删除原版本数据库
         //           .encryptionKey(bytes)            //数据库加密钥
                    .build();
                return  Realm.getInstance(configuration);
        }catch (Exception e){
            Log.i(TAG,e.getMessage());
            e.printStackTrace();
        }
        return  null;
    }
}
