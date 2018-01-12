package com.nld.rxjavademo.api.bean;

import java.io.Serializable;

/**
 * Created by jiangrenming on 2018/1/4.
 */

public class Subjects  implements Serializable{

    private String title, year, id;

    public Subjects(String title, String year, String id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getId() {
        return id;
    }
}
