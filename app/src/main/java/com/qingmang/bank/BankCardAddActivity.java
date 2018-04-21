package com.qingmang.bank;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonView;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;
import top.zibin.luban.Luban;

public class BankCardAddActivity extends BaseMvpActivity<BankCardAddPresenter, CommonView> implements CommonView<String> {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_card_no)
    EditText etCardNo;
    @BindView(R.id.et_bank_name)
    EditText etBankName;
    @BindView(R.id.et_last_no)
    EditText etLastNo;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.iv_z)
    ImageView ivZ;
    @BindView(R.id.iv_f)
    ImageView ivF;
    private List<File> files = new ArrayList<>();
    private File fz;
    private File ff;
    private static final int RES_Z = 123;
    private static final int RES_F = 124;
    private TimePickerView timePickerView;


    @Override
    public String setTitleName() {
        return "添加信用卡";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_bank_card_add;
    }

    @Override
    public void onLoadFinished(Loader<BankCardAddPresenter> loader, BankCardAddPresenter data) {
        super.onLoadFinished(loader, data);
        initTimePicker();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new BankCardAddPresenter();
            }
        });
    }

    @Override
    public void onDataSuccess(String s) {
        stopProgressDialog();
        showToast("添加成功");
        finish();
    }


    @OnClick({R.id.tv_date, R.id.iv_z, R.id.iv_f, R.id.bt_sure, R.id.bt_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                timePickerView.show(tvDate);
                break;
            case R.id.iv_z:
                ChooseImages(RES_Z);
                break;
            case R.id.iv_f:
                ChooseImages(RES_F);
                break;
            case R.id.bt_sure:
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
                if (TextUtils.isEmpty(etLastNo.getText().toString())) {
                    showToast("签名区后三位不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(etPhone.getText().toString())) {
                    showToast("绑卡手机号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(tvDate.getText().toString())) {
                    showToast("请选择有效期");
                    return;
                }
                if(null==fz){
                    showToast("请选择信用卡正面照片");
                    return;
                }
                if(null==ff){
                    showToast("请选择信用卡反面照片");
                    return;
                }
                files.add(fz);
                files.add(ff);
                startProgressDialog();
                presenter.loadData(etBankName.getText().toString()
                        ,etCardNo.getText().toString()
                        ,etName.getText().toString()
                        ,etLastNo.getText().toString(),"",tvDate.getText().toString(),files
                        );
                break;
            case R.id.bt_cancle:
                finish();
                break;
        }
    }

    private void ChooseImages(int resCode) {
//        Intent toGallery = new Intent(Intent.ACTION_PICK);
//        toGallery.setType("image/*");
////        toGallery.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(toGallery, REQUEST_GALLERY);
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(BankCardAddActivity.this, resCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == RES_Z) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                String imageUrl = photos.get(0);
                compressWithRx(imageUrl, true);
            } else if (requestCode == RES_F) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                String imageUrl = photos.get(0);
                compressWithRx(imageUrl, false);
            }

        }

    }


    private void compressWithRx(final String photo, final boolean font) {
        startProgressDialog();
        Flowable.just(photo)
                .observeOn(Schedulers.io())
                .map(new Function<String, File>() {
                    @Override
                    public File apply(String s) throws Exception {
                        return Luban.with(BankCardAddActivity.this).get(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(@NonNull File file) throws Exception {
                        stopProgressDialog();
                        if (font){
                            fz = file;
                            ImageLoaderUtil.getInstance().loadLocalImage(file,ivZ , R.drawable.add_credit_front);

                        }
                        else{
                            ff = file;
                            ImageLoaderUtil.getInstance().loadLocalImage(file,ivF , R.drawable.add_credit_back);

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


    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("MM/yy");
        return format.format(date);
    }

    private void initTimePicker() {//Dialog 模式下，在底部弹出

        timePickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
//                Toast.makeText(mContext, getTime(date), Toast.LENGTH_SHORT).show();
                tvDate.setText(getTime(date));

            }
        })
                .setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
                    @Override
                    public void onTimeSelectChanged(Date date) {
                        Log.i("pvTime", "onTimeSelectChanged");
                    }
                })
                .setType(new boolean[]{true, true, false, false, false, false})
                .isDialog(true)
                .build();

        Dialog mDialog = timePickerView.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            timePickerView.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }


}
