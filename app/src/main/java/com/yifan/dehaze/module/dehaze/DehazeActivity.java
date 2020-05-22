package com.yifan.dehaze.module.dehaze;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yifan.dehaze.R;
import com.yifan.dehaze.base.BaseActivity;
import com.yifan.dehaze.util.BitmapUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DehazeActivity extends BaseActivity<DehazePresent, IDehazeView> implements IDehazeView {

    @BindView(R.id.tv_img_type)
    TextView mImgType;
    @BindView(R.id.img)
    ImageView mImage;

    private Bitmap oriImage;
    private Bitmap dehazeImage;

    private boolean isOri = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        imageDehaze();
    }

    /**
     * 图像去雾
     */
    private void imageDehaze() {
        mPresenter.startDehaze(oriImage, DehazeActivity.this);
    }

    private void initView() {
        ButterKnife.bind(this);
        oriImage = BitmapUtil.getBitmapFromFile("oriImage");
        if (oriImage != null) {
            oriImage = mPresenter.dealImg(oriImage);
            mImage.setImageBitmap(oriImage);
            mImgType.setText("原图");
        }
    }

    @OnClick(R.id.img)
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.img:
                if (isOri) {
                    if (dehazeImage != null) {
                        mImgType.setText("去雾图像");
                        mImage.setImageBitmap(dehazeImage);
                        isOri = false;
                    }
                } else {
                    if (oriImage != null) {
                        mImgType.setText("原图");
                        mImage.setImageBitmap(oriImage);
                        isOri = true;
                    }
                }
                break;
        }
    }

    @Override
    public void onReceiveDehazeImg(Bitmap bitmap) {
        dehazeImage = bitmap;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_image_layout;
    }

    @Override
    protected Class<IDehazeView> getViewClass() {
        return IDehazeView.class;
    }

    @Override
    protected Class<DehazePresent> getPresenterClass() {
        return DehazePresent.class;
    }

}
