package com.yifan.dehaze.toast;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.yifan.dehaze.util.StringUtil;


/**
 * 自定义Toast
 *
 * @author Lei Jiang
 * @version [1.0.0]
 * @date 2019-11-04
 * @since [1.0.0]
 */
public class WeToast {

    private static WeToast sToast;

    private ToastCompat mToast;

    public static WeToast getInstance() {
        if (sToast == null) {
            sToast = new WeToast();
        }
        return sToast;
    }

    public void showToast(Context context, CharSequence text) {
        show(context, text);
    }

    public void showToast(Context context, int resId) {
        if (context != null) {
            show(context, context.getString(resId));
        }
    }

    private void show(Context context, CharSequence text) {
        if (context != null && !StringUtil.isEmpty(text)) {
            if (null == mToast) {
                mToast = ToastCompat.makeText(context.getApplicationContext(), text, Toast.LENGTH_SHORT);
                mToast.setGravity(Gravity.CENTER, 0, 0);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }
}
