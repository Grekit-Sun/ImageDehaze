package com.yifan.dehaze.util;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpUtil {

    /**
     * post请求
     *
     * @param map
     * @param url
     * @return
     */
    public static Call post(Map<String, String> map, String url) {
        Call call = null;
        if (map == null) {
            return call;
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Request request = new Request.Builder()
                .post(builder.build())
                .url(url)
                .build();
        //调用okhttpClient对象实现callbasck方法
        return client.newCall(request);
    }
}
