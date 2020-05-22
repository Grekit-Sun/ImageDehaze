package com.yifan.dehaze.base;

import io.reactivex.annotations.NonNull;

public interface IBaseView {

    /**
     * 显示加载框
     */
    void showDialog(String string);

    /**
     * 显示toast
     *
     * @param info
     */
    void showToast(@NonNull String info);
}
