package com.qingmang.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qingmang.App;
import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.moudle.entity.CreditCardInfo;
import com.qingmang.user.LoginActivity;
import com.qingmang.utils.imageload.ImageLoaderUtil;

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
    private CreditCardInfo creditCardInfo;

    @Override
    public String setTitleName() {
        return "信用卡详情";
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
        tvName.setText(creditCardInfo.getName());
        tvCoinType.setText(coinType(creditCardInfo.getCurrency()));
        tvType.setText(levelType(creditCardInfo.getLevel()));
        tvCardType.setText(creditCardInfo.getTags());
        tvDes.setText(creditCardInfo.getIntroduct());
        this.creditCardInfo = creditCardInfo;

    }

    private String  coinType(String code){
        if("rmb".equals(code)){
            return "人民币";
        }
       else if("dollar".equals(code)){
            return "外汇";
        }
        else if("rmbdollar".equals(code)){
            return "人民币+外汇";
        }
        return "";
    }

    private String  levelType(String code){
        if("ordinary".equals(code)){
            return "普通卡";
        }
        else if("silver".equals(code)){
            return "白金卡";
        }
        else if("golden".equals(code)){
            return "金卡";
        }
        return "";
    }
    @Override
    public void onApplySuccess(String banks) {

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
        if(App.getInstance().isLogin()){
            Intent intent = new Intent(mContext,CreditCardInfoNextActivity.class);
            intent.putExtra("creditCardInfo",creditCardInfo);
            startActivity(intent);
        }else {
            startActivity(LoginActivity.class);
        }

    }
}
