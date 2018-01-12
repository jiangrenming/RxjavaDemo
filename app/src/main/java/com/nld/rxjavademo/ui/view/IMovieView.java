package com.nld.rxjavademo.ui.view;

import com.nld.rxjavademo.base.IBase.IBaseView;

/**
 *
 * @author jiangrenming
 * @date 2018/1/8
 */

public interface IMovieView<T> extends IBaseView {


    /**
     * 加载数据
     * @param data
     */
    void loadData(T data);

    /**
     * 加载更多数据
     * @param data
     */
    void loadMoreData(T data);

    /**
     * 无数据加载
     */
    void loadNoData();


}
