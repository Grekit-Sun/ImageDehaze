package com.yifan.dehaze.util;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class SimpleHttpSubscriber<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e("error", e.toString());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onResponse(T t);
}


