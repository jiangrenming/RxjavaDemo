package com.nld.rxjavademo.ui.injector.component;

import android.app.Activity;

import com.nld.rxjavademo.ui.injector.ActivityScope;
import com.nld.rxjavademo.ui.injector.PerActivity;
import com.nld.rxjavademo.ui.injector.module.ActivityModule;
import dagger.Component;

/**
 * @author jiangrenming
 * @date 2018/1/9
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();
}
