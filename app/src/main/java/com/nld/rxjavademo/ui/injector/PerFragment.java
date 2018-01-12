package com.nld.rxjavademo.ui.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jiangrenming on 2018/1/9.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
