package com.qingmang.bank;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.moudle.entity.Bank;
import com.qingmang.moudle.entity.CreditCardInfo;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CreditCardInfoActivity extends BaseMvpActivity<CreditCardInfoPresenter, CreditCardInfoView>
        implements CreditCardInfoView<CreditCardInfo> {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_des)
    TextView tvDes;
    @BindView(R.id.iv_card_bg)
    ImageView ivCardBg;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @Override
    public String setTitleName() {
        return null;
    }

    @Override
    public View getRootView() {
        return llRoot;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_credit_card_info;
    }


    @Override
    public void onDataSuccess(CreditCardInfo creditCardInfo) {
        loadViewHelper.restore();
        ImageLoaderUtil.getInstance().showImage(creditCardInfo.getLogo(),ivCardBg,0);
        tvName.setText(creditCardInfo.getBankName());

    }

    @Override
    public void onApplySuccess(List<Bank> banks) {

    }

    @Override
    public void onLoadFinished(Loader<CreditCardInfoPresenter> loader, CreditCardInfoPresenter data) {
        super.onLoadFinished(loader, data);
        loadViewHelper.showLoading("");
        presenter.loadData(getIntent().getIntExtra("id",0));
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new CreditCardInfoPresenter();
            }
        });
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        loadViewHelper.restore();
    }

    @OnClick(R.id.bt_sure)
    public void onViewClicked() {
    }
}
