package com.qingmang.user;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.baselibrary.utils.ValUtils;
import com.qingmang.customview.CheckCodeCountDown;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPasswdActivity extends BaseMvpActivity<ForgetPasswdPresenter, ForgetPasswdView> implements ForgetPasswdView<String> {

    @BindView(R.id.rl_error)
    RelativeLayout rlError;
    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @BindView(R.id.et_val)
    AppCompatEditText etVal;
    @BindView(R.id.cd_val)
    CheckCodeCountDown cdVal;
    @BindView(R.id.et_passwd)
    AppCompatEditText etPasswd;
    @BindView(R.id.et_passwd_sure)
    AppCompatEditText etPasswdSure;
    private boolean forUpdate;

    @Override
    public String setTitleName() {
        return forUpdate?"修改密码":"找回密码";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_forget_passwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        forUpdate = getIntent().getBooleanExtra(SettingActivity.FROM_UPDATE,false);
        super.onCreate(savedInstanceState);
        initListener();
    }

    @Override
    public void onDataSuccess(String s) {
        stopProgressDialog();

    }

    @Override
    public void onUpdatePassWd() {
        stopProgressDialog();
        finish();
    }

    @OnClick(R.id.bt_sure)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etPhone.getText().toString())){
            showToast("请填写手机号码！");
            return;
        }
        if(TextUtils.isEmpty(etPasswd.getText().toString())){
            showToast("请填写密码！");
            return;
        }
        if(TextUtils.isEmpty(etPasswdSure.getText().toString())){
            showToast("请填写确认密码！");
            return;
        }
        if(TextUtils.isEmpty(etVal.getText().toString())){
            showToast("请填写验证码！");
            return;
        }
        if(!ValUtils.isMobileNO(etPhone.getText().toString())){
            showToast("手机号码格式不正确！");
            return;
        }
        if(!ValUtils.isPassword(etPasswd.getText().toString())){
            showToast("密码不正确！");
            return;
        }
        startProgressDialog();

        presenter.updatePasswd(etPhone.getText().toString(),
                etPasswd.getText().toString(),
                etVal.getText().toString());
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public ForgetPasswdPresenter crate() {
                return new ForgetPasswdPresenter();
            }
        });
    }

    private void initListener() {
        cdVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 必须调用 , 输入框中输入的是手机号 true , 否则 false ,这么做是为了防止不是手机号也进入倒计时
                if(TextUtils.isEmpty(etPhone.getText().toString())){
                    showToast("请填写手机号码！");
                    return;
                }

                boolean phoneNumber = ValUtils.isMobileNO(etPhone.getText().toString());
                cdVal.performOnClick(phoneNumber);
            }
        });
        cdVal.setOnSendCheckCodeListener(new CheckCodeCountDown.OnSendCheckCodeListener() {
            @Override
            public void sendCheckCode() {
//                TODO 向手机发送验证码的逻辑
                startProgressDialog();
                presenter.sendSms(etPhone.getText().toString());
            }
        });
        cdVal.setOnFinishListener(new CheckCodeCountDown.OnFinishListener() {
            @Override
            public void OnFinish() {
//              TODO 计时结束后的操作
                cdVal.setText("重新发送");
            }
        });
    }


}
