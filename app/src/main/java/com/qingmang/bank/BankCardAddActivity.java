package com.qingmang.bank;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonView;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;

public class BankCardAddActivity extends BaseMvpActivity<BankCardAddPresenter,CommonView> implements CommonView<String> {

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

    }
}
