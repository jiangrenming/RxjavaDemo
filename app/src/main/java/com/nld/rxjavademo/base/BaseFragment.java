package com.nld.rxjavademo.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.nld.rxjavademo.R;
import com.nld.rxjavademo.base.IBase.IBasePresenter;
import com.nld.rxjavademo.base.IBase.IBaseView;
import com.nld.rxjavademo.view.EmptyLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * @author jiangrenming
 * @date 2018/1/8
 */

public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {

    @Nullable
    @BindView(R.id.empty_layout)
    EmptyLayout mEmptyLayout;
    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;
    @Inject
    public   T mPresenter;

    @LayoutRes
    public  abstract  int attachLayoutRes();
    protected abstract void initViews();
    public  abstract  void upDateViews();
    public  abstract  void initDatas();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            initViews();
            initDatas();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            upDateViews();
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            upDateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }


    @Override
    public void showLoading() {
        if (mEmptyLayout != null) {
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
    public void showNetError(final EmptyLayout.OnRetryListener onRetryListener) {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
            mEmptyLayout.setRetryListener(onRetryListener);
        }
    }
}
