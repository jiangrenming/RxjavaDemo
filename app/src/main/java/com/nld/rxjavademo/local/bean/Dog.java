package com.nld.rxjavademo.local.bean;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 *
 * @author jiangrenming
 * @date 2018/1/17
 */
@RealmClass
public class Dog extends RealmObject{

    @Required
    private int age;
    private String sex;
    @Required
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
