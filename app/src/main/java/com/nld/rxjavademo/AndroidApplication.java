package com.nld.rxjavademo;

import android.app.Application;
import android.content.Context;
import com.nld.retrofitlibrary.retrofit.RetrofitBuilder;
import com.nld.retrofitlibrary.retrofit.RetrofitService;
import com.nld.rxjavademo.api.ApiService;
import com.nld.rxjavademo.ui.injector.component.ApplicationComponent;
import com.nld.rxjavademo.ui.injector.component.DaggerApplicationComponent;
import com.nld.rxjavademo.ui.injector.module.ApplicationModule;
import com.nld.rxjavademo.util.Constant;

/**
 *
 * @author jiangrenming
 * @date 2018/1/3
 */

public class AndroidApplication extends Application{

    private static Context sContext;
    public static  ApiService apiService;
    public static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        initData();
    }

    private void initData() {
        //实现dagger2的全局单例实例
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();

        RetrofitBuilder builder = new RetrofitBuilder.Builder().setBaseUrl(Constant.baseUrl)
                .setContext(getApplicationContext()).build();
        RetrofitService.setConfig(builder);
        RetrofitService.init();
        apiService = RetrofitService.getRetrofit().create(ApiService.class);
    }

    public static Context getsContext() {
        return sContext;
    }

    public static  ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
