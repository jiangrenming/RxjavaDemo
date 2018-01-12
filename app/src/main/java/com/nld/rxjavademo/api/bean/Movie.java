package com.nld.rxjavademo.api.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jiangrenming
 * @date 2018/1/4
 */

public class Movie implements Serializable{


    private String title;
    private List<Subjects> subjects;
    public String getTitle() {
        return title;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }
}

