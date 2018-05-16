package com.qingmang.bank;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonView;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.ImageCaptureManager;
import top.zibin.luban.Luban;

/**
 * Created by jiangpw
 * on 2018/4/20 13:57
 */
public class DebitAddActivity extends BaseMvpActivity<DebitAddPresenter, CommonView> implements CommonView<String> {
    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title_rightIv)
    ImageView titleRightIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_card_no)
    EditText etCardNo;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_debit_front)
    ImageView ivDebitFront;
    @BindView(R.id.iv_debit_back)
    ImageView ivDebitBack;
    @BindView(R.id.iv_debit_front_c)
    ImageView ivDebitFrontC;
    @BindView(R.id.iv_debit_front_cs)
    ImageView ivDebitFrontCs;
    @BindView(R.id.bt_sure)
    Button btSure;
    @BindView(R.id.bt_cancle)
    Button btCancle;

    private static final int RES_F = 110;
    private static final int RES_B = 111;
    private static final int RES_FC = 112;
    private static final int RES_FSC = 113;

    private File ff;
    private File fb;
    private File ffc;
    private File ffsc;

    private List<File> files = new ArrayList<>();

    @OnClick(R.id.iv_debit_front)
    void ivDebitFrontOnclick() {
        openCamera(RES_F);
    }

    @OnClick(R.id.iv_debit_back)
    void ivDebitBackOnclick() {
        openCamera(RES_B);
    }

    @OnClick(R.id.iv_debit_front_c)
    void ivDebitFrontCOnclick() {
        openCamera(RES_FC);
    }

    @OnClick(R.id.iv_debit_front_cs)
    void ivDebitFrontCsOnclick() {
        openCamera(RES_FSC);
    }

    @OnClick(R.id.bt_sure)
    void btSureOnclick() {
        addDebit();
    }

    @OnClick(R.id.bt_cancle)
    void btCancleOnclick() {
        finish();
    }

    @Override
    public String setTitleName() {
        return "添加银行卡";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_debit_card_add;
    }

    @Override
    public void onDataSuccess(String s) {
        stopProgressDialog();
        showToast("添加成功");
        finish();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new DebitAddPresenter();
            }
        });
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
    ImageCaptureManager captureManager;

    private void openCamera(int resCode) {
        captureManager = new ImageCaptureManager(mContext);
        try {
            Intent intent = captureManager.dispatchTakePictureIntent();
            startActivityForResult(intent, resCode);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ActivityNotFoundException e) {
            Log.e("PhotoPickerFragment", "No Activity Found to handle Intent", e);
        }
    }

    private void chooseImages(int resCode) {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(DebitAddActivity.this, resCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
//            ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
//            String imageUrl = photos.get(0);
            String imageUrl = captureManager.getCurrentPhotoPath();
            compressWithRx(imageUrl, requestCode);
        }
    }

    @SuppressLint("CheckResult")
    private void compressWithRx(final String photo, final int requestCode) {
        startProgressDialog();
        Flowable.just(photo)
                .observeOn(Schedulers.io())
                .map(new Function<String, File>() {
                    @Override
                    public File apply(String s) throws Exception {
                        return Luban.with(DebitAddActivity.this).get(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(@NonNull File file) throws Exception {
                        stopProgressDialog();
                        if (requestCode == RES_F) {
                            ff = file;
                            ImageLoaderUtil.getInstance().loadLocalImage(file, ivDebitFront, R.drawable.add_credit_front);
                        } else if (requestCode == RES_B) {
                            fb = file;
                            ImageLoaderUtil.getInstance().loadLocalImage(file, ivDebitBack, R.drawable.add_credit_front);
                        } else if (requestCode == RES_FC) {
                            ffc = file;
                            ImageLoaderUtil.getInstance().loadLocalImage(file, ivDebitFrontC, R.drawable.add_credit_front);
                        } else if (requestCode == RES_FSC) {
                            ffsc = file;
                            ImageLoaderUtil.getInstance().loadLocalImage(file, ivDebitFrontCs, R.drawable.add_credit_front);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        stopProgressDialog();
                        LogManager.e("压缩失败..", throwable);
                    }
                });
    }

    private void addDebit() {
        if (TextUtils.isEmpty(etName.getText().toString())) {
            showToast("持卡人姓名不能为空！");
            return;
        }
        if (TextUtils.isEmpty(etCardNo.getText().toString())) {
            showToast("银行卡不能为空！");
            return;
        }
        if (TextUtils.isEmpty(etBankName.getText().toString())) {
            showToast("银行名称不能为空！");
            return;
        }
        if (TextUtils.isEmpty(etPhone.getText().toString())) {
            showToast("绑卡手机号不能为空！");
            return;
        }
        if (null == ff) {
            showToast("请选择银行卡正面照片");
            return;
        }
        if (null == fb) {
            showToast("请选择银行卡反面照片");
            return;
        }
        if (null == ffc) {
            showToast("请选择手持银行卡正面照片");
            return;
        }
        if (null == ffsc) {
            showToast("请选择手持银行卡身份证正面照片");
            return;
        }

        files.add(ff);
        files.add(fb);
        files.add(ffc);
        files.add(ffsc);
        startProgressDialog();

        presenter.apply(etBankName.getText().toString().trim(),
                etCardNo.getText().toString().trim(),
                etName.getText().toString().trim(),
                etPhone.getText().toString().trim(),
                files
        );
    }
}
