package com.nld.rxjavademo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.nld.rxjavademo.AndroidApplication;
import com.nld.rxjavademo.R;
import com.nld.rxjavademo.base.IBase.IBasePresenter;
import com.nld.rxjavademo.base.IBase.IBaseView;
import com.nld.rxjavademo.ui.injector.component.ApplicationComponent;
import com.nld.rxjavademo.ui.injector.module.ActivityModule;
import com.nld.rxjavademo.view.EmptyLayout;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * @author jiangrenming
 * @date 2018/1/8
 */

public abstract class BaseActivity<T extends IBasePresenter> extends Activity implements IBaseView{


    /**
     * 把 EmptyLayout 放在基类统一处理
     */
    @Nullable
    @BindView(R.id.empty_layout)
    protected EmptyLayout mEmptyLayout;


    @Inject
    public T mPresenter;

    @LayoutRes
    public  abstract int attchLayoutRes();
    /**
     * Dagger 注入
     */
    protected abstract void initInjector();
    public  abstract  void initDatas();
    public  abstract  void upDateViews();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attchLayoutRes());
        ButterKnife.bind(this);
        initInjector();
        initDatas();
        upDateViews();
    }

    @Override
    public void showLoading() {
        if (mEmptyLayout  != null){
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    @Override
    public void hideLoading() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }

    @Override
    public void showNetError(EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
    }

    /**
     * 获取applicationComponent
     * @return
     */
    protected ApplicationComponent getAppComponent() {
        return AndroidApplication.getApplicationComponent();
    }
    /**
     * 绑定activity
     * @return
     */
    public ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }
}
