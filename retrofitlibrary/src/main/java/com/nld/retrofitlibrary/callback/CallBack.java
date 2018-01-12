package com.nld.retrofitlibrary.callback;

/**
 *
 * @author jiangrenming
 * @date 2018/1/9
 */

public interface CallBack {

    /**
     * 初始化，数据操作
     */
     void onInit();

    /**
     * 获取数据成功
     * @param data
     * @param <T>
     */
     <T>void onSucess(T data);

    /**
     * 获取数据失败
     * @param t
     */
     void onFailure(Throwable t);

    /**
     * 完成获取数据
     */
     void onCompleted();

}
