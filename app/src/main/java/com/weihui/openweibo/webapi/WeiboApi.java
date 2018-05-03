package com.weihui.openweibo.webapi;

import com.weihui.openweibo.entity.db.Status;
import com.weihui.openweibo.entity.db.TimeLine;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by weihui on 2018/4/9.
 */

public interface WeiboApi {


    @GET("statuses/friends_timeline.json")
    Observable<TimeLine> fetchHomeTimeLine(@Query("since_id") long since_id);

    @GET("statuses/friends_timeline.json")
    Observable<TimeLine> loadHomeTimeLine(@Query("max_id") long max_id);


    @GET("statuses/user_timeline.json")
    Observable<TimeLine> fetchUserTimeLine();

    @GET("statuses/user_timeline.json")
    Observable<TimeLine> fetchUserTimeLine(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("favorites/create.json")
    Observable<Status> createFav(@Field("id") long id, @Field("access_token") String access_token);

    @FormUrlEncoded
    @POST("favorites/destroy.json")
    Observable<Status> destroyFav(@Field("id") long id, @Field("access_token") String access_token);


}
