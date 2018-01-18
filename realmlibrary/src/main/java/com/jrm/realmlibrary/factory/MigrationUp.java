package com.jrm.realmlibrary.factory;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 * 当新旧版本号不同时，从事数据迁移的工作
 *
 */

public class MigrationUp implements RealmMigration{



    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

    }

}
