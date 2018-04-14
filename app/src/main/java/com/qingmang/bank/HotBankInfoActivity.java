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
import com.qingmang.moudle.entity.BankInfo;

public class HotBankInfoActivity extends BaseMvpActivity<HotBankkInfoPresenter,CommonView> implements CommonView<BankInfo>{

    @Override
    public String setTitleName() {
        return "银行详情";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_hot_bank_info;
    }

    @Override
    public void onLoadFinished(Loader<HotBankkInfoPresenter> loader, HotBankkInfoPresenter data) {
        super.onLoadFinished(loader, data);
        presenter.loadData(getIntent().getIntExtra("id",0));
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new HotBankkInfoPresenter();
            }
        });
    }

    @Override
    public void onDataSuccess(BankInfo bankInfo) {

    }
}
