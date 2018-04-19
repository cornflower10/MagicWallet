package com.qingmang.bank;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.TextView;

import com.qingmang.App;
import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.customview.LevelView;
import com.qingmang.moudle.entity.CreditCardInfo;

import butterknife.BindView;
import butterknife.OnClick;

public class CreditCardInfoNextActivity extends BaseMvpActivity<CreditCardInfoPresenter, CreditCardInfoView>
        implements CreditCardInfoView<CreditCardInfo> {


    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.tv_ori)
    TextView tvOri;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_piont_rule)
    TextView tvPiontRule;
    @BindView(R.id.tv_year_fee)
    TextView tvYearFee;
    @BindView(R.id.tv_qxbl)
    TextView tvQxbl;
    @BindView(R.id.tv_qxfy)
    TextView tvQxfy;
    @BindView(R.id.tv_low)
    TextView tvLow;
    @BindView(R.id.tv_new_people)
    TextView tvNewPeople;
    @BindView(R.id.lv_ed)
    LevelView lvEd;
    @BindView(R.id.tv_ed)
    TextView tvEd;
    @BindView(R.id.lv_gl)
    LevelView lvGl;
    @BindView(R.id.tv_gl)
    TextView tvGl;
    @BindView(R.id.lv_yd)
    LevelView lvYd;
    @BindView(R.id.tv_yd)
    TextView tvYd;
    private CreditCardInfo creditCardInfo;

    @Override
    public String setTitleName() {
        return "信用卡详情";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_credit_card_info_next;
    }


    @Override
    public void onDataSuccess(CreditCardInfo creditCardInfo) {


    }

    private String coinType(String code) {
        if ("rmb".equals(code)) {
            return "人民币";
        } else if ("dollar".equals(code)) {
            return "外汇";
        } else if ("rmbdollar".equals(code)) {
            return "人民币+外汇";
        }
        return "";
    }

    private String levelType(String code) {
        if ("ordinary".equals(code)) {
            return "普通卡";
        } else if ("silver".equals(code)) {
            return "白金卡";
        } else if ("golden".equals(code)) {
            return "金卡";
        }
        return "";
    }

    @Override
    public void onApplySuccess(String s) {
        stopProgressDialog();
        showToast("申请成功！");
        App.getInstance().getForegroundCallbacks().finishActivity(CreditCardInfoActivity.class);
        finish();
    }

    @Override
    public void onLoadFinished(Loader<CreditCardInfoPresenter> loader, CreditCardInfoPresenter data) {
        super.onLoadFinished(loader, data);
        creditCardInfo = getIntent().getParcelableExtra("creditCardInfo");
        tvLevel.setText(levelType(creditCardInfo.getLevel()));
        tvCoinType.setText(coinType(creditCardInfo.getCurrency()));
        tvOri.setText(creditCardInfo.getOrganization());
        tvDate.setText(creditCardInfo.getFreePeriod());
        tvPiontRule.setText(creditCardInfo.getSpecification());
        tvYearFee.setText(creditCardInfo.getAnnualFee());
        tvQxbl.setText(creditCardInfo.getProportion());
        tvQxfy.setText(creditCardInfo.getWithdrawal());
        tvLow.setText(creditCardInfo.getRepayment());
        tvNewPeople.setText(creditCardInfo.getUserCond());
        tvEd.setText(creditCardInfo.getAmount());
        lvEd.setLevel(creditCardInfo.getAmountLevel());
        tvGl.setText(creditCardInfo.getBatch());
        lvGl.setLevel(creditCardInfo.getBatchLevel());
        lvYd.setLevel(creditCardInfo.getDifficultLevel());
        tvYd.setText(creditCardInfo.getDifficult());
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
        stopProgressDialog();
        super.onError(msg);
    }

    @OnClick(R.id.bt_sure)
    public void onViewClicked() {
        startProgressDialog();
        presenter.applayCreditCard(creditCardInfo.getId());
    }

}
