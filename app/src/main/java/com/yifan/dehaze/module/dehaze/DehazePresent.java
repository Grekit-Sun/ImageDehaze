package com.yifan.dehaze.module.dehaze;

import android.graphics.Bitmap;

import com.yifan.dehaze.base.IPresenter;
import com.yifan.dehaze.constant.DehazeConstants;
import com.yifan.dehaze.module.AuthBean;
import com.yifan.dehaze.module.DehazeBean;
import com.yifan.dehaze.util.BitmapUtil;
import com.yifan.dehaze.util.SimpleHttpSubscriber;

import java.util.HashMap;

public class DehazePresent implements IPresenter {

    private IDehazeView mView;
    private DehazeModel mDehazeModel;

    public DehazePresent(IDehazeView view) {
        mView = view;
        mDehazeModel = new DehazeModel();
    }

    /**
     * 开始进行图像去雾处理
     */
    public void startDehaze(Bitmap oriImage) {
        if (oriImage == null) {
            return;
        }
        //获取token
        HashMap<String, String> map_token = new HashMap<>();
        HashMap<String, String> map_dehaze = new HashMap<>();
        map_token.put("grant_type", DehazeConstants.GRANT_TYPE);
        map_token.put("client_id", DehazeConstants.CLIENT_ID);
        map_token.put("client_secret", DehazeConstants.CLIENT_SECRET);
        mDehazeModel.getAccessToken(map_token, new SimpleHttpSubscriber<AuthBean>() {
            @Override
            public void onResponse(AuthBean authBean) {
                if (authBean != null) {
                    map_dehaze.put("image", BitmapUtil.bitmaoToBase64(oriImage));
                    map_dehaze.put("access_token", authBean.access_token);
                    mDehazeModel.getDehazeImg(map_dehaze, new SimpleHttpSubscriber<DehazeBean>() {
                        @Override
                        public void onResponse(DehazeBean dehazeBean) {
                            if (dehazeBean.image != null) {
                                mView.onReceiveDehazeImg(BitmapUtil.base64ToBitmap(dehazeBean.image));
                            }
                        }
                    });
                }
            }
        });
    }

}
