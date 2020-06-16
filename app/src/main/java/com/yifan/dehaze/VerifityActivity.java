package com.yifan.dehaze;

import android.os.Bundle;
import android.widget.Toast;

import com.luozm.captcha.Captcha;
import com.yifan.dehaze.base.BaseActivity;

/**
 * @Author sun
 * @Date 2020-05-25
 * Description:
 */
public class VerifityActivity extends BaseActivity<VerifityPresnter, IVerifityView> implements IVerifityView {

    Captcha captcha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        captcha = (Captcha) findViewById(R.id.captCha);
        captcha.setBitmap(R.drawable.ujs);
        captcha.setCaptchaListener(new Captcha.CaptchaListener() {
            @Override
            public String onAccess(long time) {
                Toast.makeText(VerifityActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                return "验证通过,耗时" + time + "毫秒";
            }

            @Override
            public String onFailed(int failedCount) {
                Toast.makeText(VerifityActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                return "验证失败,已失败" + failedCount + "次";
            }

            @Override
            public String onMaxFailed() {
                Toast.makeText(VerifityActivity.this, "验证超过次数，你的帐号被封锁", Toast.LENGTH_SHORT).show();
                return "验证失败,帐号已封锁";
            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.layout_yanzheng;
    }

    @Override
    protected Class<IVerifityView> getViewClass() {
        return IVerifityView.class;
    }

    @Override
    protected Class<VerifityPresnter> getPresenterClass() {
        return VerifityPresnter.class;
    }

}
