package com.nld.rxjavademo.ui.injector.module;

import android.app.Activity;

import com.nld.rxjavademo.ui.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 *
 * @author jiangrenming
 * @date 2018/1/9
 */
@Module
public class ActivityModule {

    private Activity mActivity;

    public  ActivityModule(Activity activity){
        this.mActivity = activity;
    }

    @PerActivity
    @Provides
    Activity getmActivity(){
        return mActivity;
    }

}
