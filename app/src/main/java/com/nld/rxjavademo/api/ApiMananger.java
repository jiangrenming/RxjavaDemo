package com.nld.rxjavademo.api;

import com.nld.rxjavademo.AndroidApplication;
import com.nld.rxjavademo.api.bean.Movie;
import com.nld.rxjavademo.api.bean.Subjects;
import org.reactivestreams.Publisher;
import java.util.List;
import javax.xml.transform.Transformer;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * @author jiangrenming
 * @date 2018/1/4
 * 负责处理所有的转换
 */

public class ApiMananger {


    /**
     * 获取电影信息
     * @param start
     * @param count
     * @return
     */
    public static Flowable<List<Subjects>> getTopMovie(int start,int count){
        return apiSubscribe(AndroidApplication.apiService.getTopMovie(start,count))
                .flatMap(getMovies());
    }

    /***************************************数据转换器******************************************/

    /**
     * 电影信息数据转换
     * @return
     */
    private static  Function<Movie,Flowable<List<Subjects>>> getMovies(){
        return new Function<Movie, Flowable<List<Subjects>>>() {
            @Override
            public Flowable<List<Subjects>> apply(Movie movie) throws Exception {
                return Flowable.fromArray(movie.getSubjects());
            }
        };
    }


/************************************线程转换公共部分******************************************************/

    private static  Flowable apiSubscribe(Flowable observable){
       return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
