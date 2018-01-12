package com.nld.rxjavademo.api;

import com.nld.rxjavademo.api.bean.Movie;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 *
 * @author jiangrenming
 * @date 2018/1/3
 * 所有接口放置的地方
 */

public interface ApiService {

    /*******************************get方式请求静态url地址***********************************************/
    @GET("top250")
    Flowable<Movie> getTopMovie(@Query("start") int start,@Query("count") int count);

    @GET("top500")
    Flowable<Movie> getMovies(@QueryMap Map<String,String> movies);


/*****************************post方式请求静态url地址**************************************/
    @POST("users/stven0king/repos")
    Flowable<Movie> postMovies();

    @FormUrlEncoded
    @POST("users/stven0king/repos")
    Flowable<Movie> postMovies(@Field("start") int start,@Field("count") int count);

    @FormUrlEncoded
    @POST("users/stven0king/repos")
    Flowable<Movie> postMapMovies(@FieldMap Map<String,String> movies);

/***************************半动态url路径的使用***********************************/
    @GET("users/{user}/stven0king/repos")
    Flowable<Movie> pathMovies(@Path("user") String user);

/****************************动态的url地址请求*******************************************/
    @GET
    Flowable<Movie> urlMovies(@Url String user);

}
