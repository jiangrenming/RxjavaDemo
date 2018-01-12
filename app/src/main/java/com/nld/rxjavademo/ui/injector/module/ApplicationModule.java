package com.nld.rxjavademo.ui.injector.module;

import android.content.Context;
import com.nld.rxjavademo.AndroidApplication;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 *
 * @author jiangrenming
 * @date 2018/1/9
 */
@Module
public class ApplicationModule {

    private final AndroidApplication mApplication;

    public ApplicationModule(AndroidApplication application){
        this.mApplication = application;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext(){
        return mApplication.getApplicationContext();
    }

}
