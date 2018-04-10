package com.qingmang.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.qingmang.MainActivity;
import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.baselibrary.utils.ValUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseMvpActivity<LoginPresenter,LoginView> implements LoginView<String>{

    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @BindView(R.id.et_passwd)
    AppCompatEditText etPasswd;

    @Override
    public String setTitleName() {
        return "登录";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id.bt_login, R.id.tv_forget_passwd, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login:
                if(!ValUtils.isMobileNO(etPhone.getText().toString())){
                    showToast("手机号格式不正确！");
                    return;
                }
                if(!ValUtils.isPassword(etPasswd.getText().toString())){
                    showToast("密码格式不正确！");
                    return;
                }
                startProgressDialog();
                presenter.login(etPhone.getText().toString(),etPasswd.getText().toString());
                break;
            case R.id.tv_forget_passwd:
//                startActivity(ForgetPasswdActivity.class);
                break;
            case R.id.tv_register:
                startActivity(RegisterActivity.class);
                break;

        }
    }

    @Override
    public void onDataSuccess(String o) {
            stopProgressDialog();
        SharedPreferences sharedPreferences = getSharedPreferences("token",MODE_PRIVATE);
        sharedPreferences.edit().putString("token",o).commit();

            startActivity(MainActivity.class);
            finish();
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public LoginPresenter crate() {
                return new LoginPresenter();
            }
        });
    }
}
