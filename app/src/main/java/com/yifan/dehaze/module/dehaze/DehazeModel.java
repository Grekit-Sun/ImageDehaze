package com.yifan.dehaze.module.dehaze;

import com.yifan.dehaze.helper.RxComposerHelper;
import com.yifan.dehaze.manager.RetrofitManager;
import com.yifan.dehaze.module.AuthBean;
import com.yifan.dehaze.module.DehazeBean;
import com.yifan.dehaze.module.api.DehazeService;
import com.yifan.dehaze.util.SimpleHttpSubscriber;

import java.util.HashMap;

import io.reactivex.observers.DisposableObserver;

public class DehazeModel {

    private DehazeService mDehazeService;

    public DehazeModel() {
        mDehazeService = RetrofitManager.getInstance().getDefaultRetrofit().create(DehazeService.class);
    }

    /**
     * 获取token
     *
     * @param map
     * @param subscriber
     */
    public void getAccessToken(HashMap<String, String> map, SimpleHttpSubscriber<AuthBean> subscriber) {
        mDehazeService.getToken(map)
                .compose(RxComposerHelper.observableIO2Main())
                .subscribe(subscriber);
    }

    /**
     * 获取去雾图像
     * @param map
     * @param subscriber
     */
    public void getDehazeImg(HashMap<String, String> map, SimpleHttpSubscriber<DehazeBean> subscriber) {
        mDehazeService.getDehazeImg(map)
                .compose(RxComposerHelper.observableIO2Main())
                .subscribe(subscriber);
    }
}
