package com.weihui.openweibo.util;

import android.util.Log;

import com.weihui.openweibo.webapi.Constants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weihui on 2018/4/9.
 */

public class RetrofitHelper {

    private static Retrofit retrofit;

    private static final String TAG = "RetrofitHelper";

    static {

        OkHttpClient client = new OkHttpClient.Builder().
                addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request oldRequest = chain.request();

                        Log.d(TAG, "access_token:" + Constants.token.getToken());
                        HttpUrl newUrl = oldRequest.url().newBuilder().addQueryParameter("access_token", Constants.token.getToken()).build();

                        Request request = chain.request().newBuilder().url(newUrl).addHeader("access_token",Constants.token.getToken()).build();

                        return chain.proceed(request);
                    }
                }).
                build();

        Retrofit.Builder builder = new Retrofit.Builder();
        retrofit = builder.addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.weibo.com/2/")
                .client(client)
                .build();
    }

    public static Retrofit client() {

        return retrofit;
    }
}
