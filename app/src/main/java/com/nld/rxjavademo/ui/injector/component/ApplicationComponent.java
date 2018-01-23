package com.nld.rxjavademo.ui.injector.component;

import android.content.Context;
import com.nld.rxjavademo.ui.injector.module.ApplicationModule;
import javax.inject.Singleton;
import dagger.Component;

/**
 *
 * @author jiangrenming
 * @date 2018/1/9
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context getContext();



}
