package com.yifan.dehaze.module.dehaze_old;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.yifan.dehaze.constant.DehazeConstants;
import com.yifan.dehaze.module.AuthBean;
import com.yifan.dehaze.util.BitmapUtil;
import com.yifan.dehaze.util.OkHttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 单例
 */
public class ImageDehaze {


    private static ImageDehaze mImageDehaze = null;

    private ImageDehaze() {
    }

    public static ImageDehaze getInstance() {
        if (mImageDehaze == null) {
            synchronized (ImageDehaze.class) {
                if (mImageDehaze == null) {
                    mImageDehaze = new ImageDehaze();
                }
            }
        }
        return mImageDehaze;
    }

    /**
     * 获取去雾后的图片
     */
    public void getDehazeImage(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        //获取token
        HashMap<String, String> map_token = new HashMap<>();
        HashMap<String, String> map_dehaze = new HashMap<>();
        map_token.put("grant_type", DehazeConstants.GRANT_TYPE);
        map_token.put("client_id", DehazeConstants.CLIENT_ID);
        map_token.put("client_secret", DehazeConstants.CLIENT_SECRET);

        Call call = OkHttpUtil.post(map_token, DehazeConstants.URL_ACCESSTOKEN);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                AuthBean authBean = gson.fromJson(response.toString(), AuthBean.class);
                if (authBean != null) {
                    map_dehaze.put("image", BitmapUtil.bitmaoToBase64(bitmap));
                    map_dehaze.put("access_token", authBean.access_token);
                    Call call1 = OkHttpUtil.post(map_dehaze, DehazeConstants.URL_DEHAZE);
                    call1.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            BitmapUtil.base64ToBitmap(response.toString());
                        }
                    });
                }
            }
        });
    }
}
