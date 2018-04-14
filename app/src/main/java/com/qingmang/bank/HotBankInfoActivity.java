package com.qingmang.bank;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonView;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.moudle.entity.BankInfo;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class HotBankInfoActivity extends BaseMvpActivity<HotBankkInfoPresenter, CommonView> implements CommonView<BankInfo> {

    @BindView(R.id.iv_card_logo)
    ImageView ivCardLogo;
    @BindView(R.id.tv_card_name)
    TextView tvCardName;
    @BindView(R.id.tv_bank_des)
    TextView tvBankDes;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_v)
    TextView tvV;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.tv_puka)
    TextView tvPuka;
    @BindView(R.id.tv_baijin)
    TextView tvBaijin;
    @BindView(R.id.tv_app_des)
    TextView tvAppDes;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;
    @BindView(R.id.tv_activity)
    TextView tvActivity;
    @BindView(R.id.iv_ap)
    ImageView ivAp;

    @Override
    public String setTitleName() {
        return "银行详情";
    }

    @Override
    public View getRootView() {
        return llRoot;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_hot_bank_info;
    }

    @Override
    public void onLoadFinished(Loader<HotBankkInfoPresenter> loader, HotBankkInfoPresenter data) {
        super.onLoadFinished(loader, data);
        loadViewHelper.showLoading("");
        presenter.loadData(getIntent().getIntExtra("id", 0));
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
        loadViewHelper.restore();
        ImageLoaderUtil.getInstance().showImage(bankInfo.getLogo(), ivCardLogo, 0);
        tvCardName.setText(bankInfo.getName());
        tvBankDes.setText(bankInfo.getIntroduct());
        tvRate.setText(String.valueOf(bankInfo.getPassUpper())+"%");
        tvV.setText(bankInfo.getBatchLower() + "-" + bankInfo.getBatchUpper() + "天批卡");
        tvPuka.setText("最高"+String.valueOf(((double)(bankInfo.getScopeUpper()))/10000)+"万元");
        tvBaijin.setText(String.valueOf(((double)(bankInfo.getScopeLower()))/10000)+"万元及以上");
        tvAppDes.setText(bankInfo.getIntroduct());
        tvActivity.setText(bankInfo.getActive());
        ImageLoaderUtil.getInstance().showImage(bankInfo.getAppLogo(), ivAp, 0);

    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        loadViewHelper.restore();
    }

    @OnClick(R.id.tv_place)
    public void onViewClicked() {
    }


}
