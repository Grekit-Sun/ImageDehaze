package com.yifan.dehaze.module.dehaze;

import android.graphics.Bitmap;

import com.yifan.dehaze.base.IBaseView;

public interface IDehazeView extends IBaseView {

    /**
     * 返回无雾图片
     * @param bitmap
     */
    void onReceiveDehazeImg(Bitmap bitmap);
}
