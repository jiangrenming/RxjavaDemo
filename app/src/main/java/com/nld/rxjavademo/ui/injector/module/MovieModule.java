package com.nld.rxjavademo.ui.injector.module;

import com.nld.rxjavademo.presenter.MoviesPresenter;
import com.nld.rxjavademo.ui.activity.MainActivity;
import com.nld.rxjavademo.ui.injector.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 *
 * @author jiangrenming
 * @date 2018/1/10
 */
@PerActivity
@Module
public class MovieModule {


    private MainActivity mActivity;

    public  MovieModule(MainActivity activity){
        this.mActivity = activity;
    }


    @PerActivity
    @Provides
    MoviesPresenter providePresenter(){
        return  new MoviesPresenter(mActivity);
    }


}
