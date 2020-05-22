package com.yifan.dehaze.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.yifan.dehaze.R;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.yifan.dehaze.DehazeApplication.appCtx;

public abstract class SimpleHttpSubscriber<T> implements Observer<T> {
    private boolean mShowDialog;
    private ProgressDialog dialog;
    private Disposable d;
    private Context context;


    public SimpleHttpSubscriber(Context context) {
        this.context = context;
        mShowDialog = true;
    }

    @Override
    public void onSubscribe(Disposable d) {

        this.d = d;
            if (!isConnected()) {
                Toast.makeText(context, "未连接网络...", Toast.LENGTH_SHORT).show();
                if (d.isDisposed()) {
                    d.dispose();
                }
            } else {
                if (dialog == null && mShowDialog == true) {
                    dialog = new ProgressDialog(context);
                    dialog.setMessage(context.getString(R.string.is_dehaze));
                    dialog.show();
                }
            }

    }

    @Override
    public void onNext(T t) {
        onResponse(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.e("error", e.toString());
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
    }

    @Override
    public void onComplete() {
        if (d.isDisposed()) {
            d.dispose();
        }
        hidDialog();
    }


    /**
     * 取消订阅
     */
    public void cancleRequest() {
        if (d != null && d.isDisposed()) {
            d.dispose();
            hidDialog();
        }
    }

    /**
     * 是否有网络连接，不管是wifi还是数据流量
     *
     * @return
     */
    public static boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) appCtx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return false;
        }
        boolean available = info.isAvailable();
        return available;
    }

    public void hidDialog() {
        if (dialog != null && mShowDialog == true)
            dialog.dismiss();
        dialog = null;
    }

    public abstract void onResponse(T t);
}


