package com.qingmang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.qingmang.App;
import com.qingmang.BaseFragment;
import com.qingmang.user.LoginActivity;


/**
 * Created by xiejingbao on 2018/3/14.
 */

public abstract class BaseMvpFragment<P extends Presenter<V>,V extends BaseView>  extends BaseFragment {
    protected P mPresenter;
    private Toast toast;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);

    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }


    public void jumpLoign(String msg){

        if("-99".equals(msg)){
            App.getInstance().clearCache();
            startActivity(LoginActivity.class);
        }else {
            showToast(msg);
        }
    }


    public void showToast(String msg){
        if(null!=toast){
            toast.setText(msg);
            toast.setDuration(Toast.LENGTH_SHORT);
        }else
        {
            toast = Toast.makeText(mContext,msg,Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}
