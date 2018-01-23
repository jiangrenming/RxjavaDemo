package com.nld.rxjavademo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.jrm.realmlibrary.factory.RealmFactory;
import com.nld.retrofitlibrary.retrofit.RetrofitBuilder;
import com.nld.retrofitlibrary.retrofit.RetrofitService;
import com.nld.rxjavademo.api.ApiService;
import com.nld.rxjavademo.ui.injector.component.ApplicationComponent;
import com.nld.rxjavademo.ui.injector.component.DaggerApplicationComponent;
import com.nld.rxjavademo.ui.injector.module.ApplicationModule;
import com.nld.rxjavademo.util.Constant;
import java.util.ArrayList;
import java.util.List;
import io.realm.Realm;

/**
 *
 * @author jiangrenming
 * @date 2018/1/3
 */

public class AndroidApplication extends Application{

    private static Context sContext;
    public static  ApiService apiService;
    public static ApplicationComponent applicationComponent;
    public  static Realm mRealm;
    /**
     * 本地activity栈
     */
    public static List<Activity> activitys = new ArrayList<Activity>();
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
        //实例化数据库
        mRealm = RealmFactory.getInstance().initRealm(sContext,Constant.version_code,Constant.version_name);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mRealm != null && mRealm.isClosed()){
            mRealm.close();
            mRealm = null;
        }
    }

    /**
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public static  void addActivity(Activity activity)
    {
        activitys.add(activity);
    }

    /**
     * <删除>
     * <功能详细描述>
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public static void deleteActivity(Activity activity) {
        if (activity != null) {
            activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public static Realm getmRealm() {
        return mRealm;
    }

    public static Context getsContext() {
        return sContext;
    }

    public static  ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
