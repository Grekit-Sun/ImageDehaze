package com.yifan.dehaze.module;


import com.yifan.dehaze.base.IPresenter;

public class SelectImagePresenter implements IPresenter {

    private ISelectImageView mView;

    public SelectImagePresenter(ISelectImageView view) {
        mView = view;
    }

}
