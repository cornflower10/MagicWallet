package com.qingmang.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.moudle.entity.CustomerInfo;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.iwf.photopicker.PhotoPicker;
import top.zibin.luban.Luban;

public class SettingActivity extends BaseMvpActivity<SettingPresenter, SettingView> implements SettingView<String> {

    @BindView(R.id.et_user_name)
    TextView etUserName;
    @BindView(R.id.tv_name)
    AppCompatEditText tvName;
    @BindView(R.id.tv_always_address)
    TextView tvAlwaysAddress;
    @BindView(R.id.tv_place_detail)
    AppCompatEditText tvPlaceDetail;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_real_name)
    AppCompatEditText etRealName;
    @BindView(R.id.et_email)
    AppCompatEditText etEmail;
    @BindView(R.id.et_id_card)
    AppCompatEditText etIdCard;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_work)
    TextView tvWork;
    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_commpany_name)
    TextView tvCommpanyName;
    private CustomerInfo customerInfo;

    public static final String FROM_UPDATE = "SettingActivity";
    private File imageFile;

    @Override
    public String setTitleName() {
        return "基础信息认证";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCityPickerView.init(mContext);
        customerInfo = (CustomerInfo) getIntent().getSerializableExtra("customerInfo");
        if (null != customerInfo) {
            ImageLoaderUtil.getInstance().loadCircleImage(customerInfo.getAvatar(), ivHeader, 0);
            etUserName.setText(customerInfo.getName());
            tvName.setText(customerInfo.getName());
            tvPhone.setText(customerInfo.getPhone());
            if (!TextUtils.isEmpty(customerInfo.getProvince()))
                tvAlwaysAddress.setText(customerInfo.getProvince() + "," + customerInfo.getCity());
            tvPlaceDetail.setText(customerInfo.getAddress());
            etIdCard.setText(customerInfo.getIdCard());
            etRealName.setText(customerInfo.getRealname());
            etEmail.setText(customerInfo.getEmail());
            tvIncome.setText(customerInfo.getIncome());
            tvWork.setText(customerInfo.getWorkstate());
            tvCommpanyName.setText(customerInfo.getUnitname());
        }
    }

    @Override
    public void onDataSuccess(String s) {
        showToast("修改成功");
        finish();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new SettingPresenter();
            }
        });
    }

//    @OnClick({R.id.tv_always_address,R.id.bt_commit})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.tv_always_address:
////                startActivity(AddressListActivity.class);
//                break;
////            case R.id.tv_place:
////                initWheel();
////                break;
////            case R.id.tv_update_passwd:
////                Intent intent =new Intent(mContext,ForgetPasswdActivity.class);
////                intent.putExtra(FROM_UPDATE,true);
////                startActivity(intent);
////                break;
//            case R.id.bt_commit:
//
//                String idCard = etIdCard.getText().toString();
//                if(!TextUtils.isEmpty(idCard)){
//                    if(!ValUtils.isIdNo(idCard)){
//                        showToast("身份证格式有误！");
//                        return;
//                    }
//                }
//                String email = etEmail.getText().toString();
//                if(!TextUtils.isEmpty(email)){
//                    if(!ValUtils.isMailbox(email)){
//                        showToast("邮箱格式有误！");
//                        return;
//                    }
//                }
//                String place = tvPlace.getText().toString();
//                String province = null, city=null ,areas=null;
//                if(!TextUtils.isEmpty(place)){
//                    String [] strs=   place.split(",");
//                    province = strs[0];
//                    city = strs[1];
//                    areas = strs[2];
//                }
//
//                startProgressDialog();
//                presenter.updateInfo(tvName.getText().toString(),
//                        imageFile,province,city,areas,tvPlaceDetail.getText().toString(),
//                        etRealName.getText().toString(),idCard,etEmail.getText().toString());
//                break;
//        }
//    }
//     CityPickerView  mCityPickerView = new CityPickerView();
//
//    /**
//     * 弹出选择器
//     */
//    private void initWheel() {
//        CityConfig cityConfig = null;
//        String place = tvPlace.getText().toString();
//        String province = null, city=null ,areas=null;
//        if(!TextUtils.isEmpty(place)){
//            String [] strs=   place.split(",");
//            province = strs[0];
//            city = strs[1];
//            areas = strs[2];
//        }
//        if (!TextUtils.isEmpty(province)&&!TextUtils.isEmpty(city)
//                &&!TextUtils.isEmpty(areas)) {
//            cityConfig = new CityConfig.Builder().title("选择城市")
//                    .province(province)
//                    .city(city)
//                    .district(city)
//                    .build();
//        } else {
//            cityConfig = new CityConfig.Builder().title("选择城市")//标题
//                    .build();
//        }
//
//
//        mCityPickerView.setConfig(cityConfig);
//        mCityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
//            @Override
//            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
//                StringBuilder sb = new StringBuilder();
//                if (province != null) {
//                    sb.append(province.getName() + ",");
//                }
//
//                if (city != null) {
//                    sb.append(city.getName() + ",");
//                }
//
//                if (district != null) {
//                    sb.append(district.getName());
//                }
//
//                tvPlace.setText("" + sb.toString());
//
//            }
//
//            @Override
//            public void onCancel() {
////                ToastUtils.showLongToast(CitypickerWheelActivity.this, "已取消");
//            }
//        });
//        mCityPickerView.showCityPicker();

//    }

    private void ChooseImages() {
//        Intent toGallery = new Intent(Intent.ACTION_PICK);
//        toGallery.setType("image/*");
////        toGallery.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(toGallery, REQUEST_GALLERY);
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(false)
                .setPreviewEnabled(false)
                .start(SettingActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @OnClick(R.id.iv_header)
    public void onViewClicked() {
        ChooseImages();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PhotoPicker.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);

                String imageUrl = photos.get(0);
                compressWithRx(imageUrl);

            }

        }

    }

    private void compressWithRx(final String photo) {
        startProgressDialog();
        Flowable.just(photo)
                .observeOn(Schedulers.io())
                .map(new Function<String, File>() {
                    @Override
                    public File apply(String s) throws Exception {
                        return Luban.with(SettingActivity.this).get(s);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(@NonNull File file) throws Exception {
                        stopProgressDialog();
                        imageFile = file;
                        ImageLoaderUtil.getInstance().loadLocalCircleImage(file, ivHeader, 0);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        stopProgressDialog();
                        LogManager.e("压缩失败..", throwable);
                    }
                });
    }


}
