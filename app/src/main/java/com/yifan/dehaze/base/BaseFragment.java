package com.yifan.dehaze.base;

import android.app.Fragment;
import android.os.Bundle;

import com.yifan.dehaze.toast.WeToast;

import java.lang.reflect.Constructor;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public abstract class BaseFragment<T extends IPresenter, K> extends Fragment {

    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    /**
     * 返回逻辑处理的具体类型.
     */
    protected abstract Class<T> getPresenterClass();

    /**
     * 返回View层的接口类.
     */
    protected abstract Class<K> getViewClass();


    /**
     * 初始化Presenter
     */
    protected void initPresenter() {
        try {
            Constructor constructor = getPresenterClass().getConstructor(getViewClass());
            mPresenter = (T) constructor.newInstance(this);
        } catch (Exception e) {
//            Logger.e("Init presenter throw an error : [" + e.getMessage() + "]");
        }
    }

    /**
     * 显示Toast提示.
     */
    public void showToast(@NonNull String info) {
        if (isAdded() && getActivity() != null && !isHidden()) {
            WeToast.getInstance().showToast(getActivity(), info);
        }
    }
}