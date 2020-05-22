package com.yifan.dehaze.module.api;

import com.yifan.dehaze.module.AuthBean;
import com.yifan.dehaze.module.DehazeBean;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface DehazeService {

    @POST("oauth/2.0/token")
    Observable<AuthBean> getToken(@QueryMap HashMap<String, String> map);

    @FormUrlEncoded
    @POST("rest/2.0/image-process/v1/dehaze")
    Observable<DehazeBean> getDehazeImg(@FieldMap HashMap<String, String> map);


}
