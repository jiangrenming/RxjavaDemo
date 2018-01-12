package com.nld.rxjavademo.ui.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by jiangrenming on 2018/1/10.
 * activity里中使用的局部单例模式
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {
}
