package com.yifan.dehaze.module.splash;

public interface ISplashView {

    /**
     * 跳转主页的最大延迟
     */
    int FLAG_MAIN_DELAY = 2000;

    /**
     * 跳转至首页
     */
    void startToMainNow();
}
