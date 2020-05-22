package com.yifan.dehaze.manager;

import com.yifan.dehaze.constant.RetrofitConstants;
import com.yifan.dehaze.util.LogInterceptor;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static volatile RetrofitManager mRetrofitManager;

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (mRetrofitManager == null) {
                    mRetrofitManager = new RetrofitManager();
                }
            }
        }
        return mRetrofitManager;
    }

    public Retrofit getDefaultRetrofit() {
        return new Retrofit.Builder()
                .client(initOkHttp())
                .baseUrl(RetrofitConstants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getSpecialUrlRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .client(initOkHttp())
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    /**
     * 初始化必要对象和参数
     */
    public OkHttpClient initOkHttp() {
        return new OkHttpClient().newBuilder()
                .readTimeout(RetrofitConstants.DEFAULT_TIME, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(RetrofitConstants.DEFAULT_TIME, TimeUnit.SECONDS)//设置请求超时时间
                .writeTimeout(RetrofitConstants.DEFAULT_TIME, TimeUnit.SECONDS)//设置写入超时时间
                .addInterceptor(new LogInterceptor())//添加打印拦截器
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
    }
}
