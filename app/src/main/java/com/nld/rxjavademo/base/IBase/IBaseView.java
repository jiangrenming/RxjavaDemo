package com.nld.rxjavademo.base.IBase;

import com.nld.rxjavademo.view.EmptyLayout;

/**
 *
 * @author jiangrenming
 * @date 2018/1/8
 */

public interface IBaseView {
    /**
     * 显示加载动画
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示网络错误
     * @param onRetryListener 点击监听
     */
    void showNetError(EmptyLayout.OnRetryListener onRetryListener);
}
