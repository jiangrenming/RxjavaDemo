package com.nld.rxjavademo.presenter;

import android.util.Log;

import com.nld.retrofitlibrary.callback.CallBack;
import com.nld.retrofitlibrary.callback.SubScribeCallBack;
import com.nld.rxjavademo.api.ApiMananger;
import com.nld.rxjavademo.api.bean.Subjects;
import com.nld.rxjavademo.base.IBase.IBasePresenter;
import com.nld.rxjavademo.ui.view.IMovieView;
import com.nld.rxjavademo.view.EmptyLayout;
import java.util.List;

import javax.inject.Inject;

/**
 *
 * @author jiangrenming
 * @date 2018/1/8
 */

public class MoviesPresenter implements IBasePresenter{

    private static final String TAG = "MoviesPresenter";

    private  IMovieView mView;
    private  int mPage = 0;
    private  int mCount = 10;

    public  MoviesPresenter(IMovieView view){
        this.mView = view;
    }

    @Override
    public void getData() {

        ApiMananger.getTopMovie(mPage,mCount).subscribe(new SubScribeCallBack<List<Subjects>>(new CallBack() {
            @Override
            public void onInit() {
                mView.showLoading();
            }

            @Override
            public <T> void onSucess(T data) {
                List<Subjects> subjectses = (List<Subjects>) data;
                if (subjectses != null &&subjectses.size() >0 ){
                    mView.loadData(subjectses);
                    mPage ++;
                }else {
                    mView.loadNoData();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                Log.i(TAG,t.toString());
                mView.showNetError(new EmptyLayout.OnRetryListener() {
                    @Override
                    public void onRetry() {
                        getData();
                    }
                });
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }
        }));
    }

    @Override
    public void getMoreData() {

        ApiMananger.getTopMovie(mPage,mCount).subscribe(new SubScribeCallBack<List<Subjects>>(new CallBack() {
            @Override
            public void onInit() {
                mView.showLoading();
            }

            @Override
            public <T> void onSucess(T data) {
                List<Subjects>  subjectses = (List<Subjects>) data;
                if (subjectses != null && subjectses.size() >0){
                    mView.loadMoreData(subjectses);
                    mPage++;
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.i(TAG,t.toString());
                mView.hideLoading();
                mView.loadNoData();
            }

            @Override
            public void onCompleted() {
                mView.hideLoading();
            }
        }));
    }
}
