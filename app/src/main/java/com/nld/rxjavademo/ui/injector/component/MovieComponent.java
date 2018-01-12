package com.nld.rxjavademo.ui.injector.component;

import com.nld.rxjavademo.ui.activity.MainActivity;
import com.nld.rxjavademo.ui.injector.PerActivity;
import com.nld.rxjavademo.ui.injector.module.MovieModule;

import dagger.Component;

/**
 *
 * @author jiangrenming
 * @date 2018/1/10
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class,modules = MovieModule.class)
public interface MovieComponent {
    void inject(MainActivity activity);
}
