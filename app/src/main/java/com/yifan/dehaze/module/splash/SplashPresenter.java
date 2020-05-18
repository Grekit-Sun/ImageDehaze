package com.yifan.dehaze.module.splash;


import com.yifan.dehaze.base.IPresenter;

public class SplashPresenter implements IPresenter {

    private ISplashView mView;

    public SplashPresenter(ISplashView view) {
        mView = view;
        mView.startToMainNow();
    }
}
