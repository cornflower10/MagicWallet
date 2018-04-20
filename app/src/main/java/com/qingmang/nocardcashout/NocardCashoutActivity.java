package com.qingmang.nocardcashout;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.moudle.entity.BankCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jiangpw
 * on 2018/4/17 15:29
 */
public class NocardCashoutActivity extends BaseMvpActivity<NocardCashoutPresenter, NocardCashoutView> implements NocardCashoutView {

    @BindView(R.id.title_left)
    TextView titleLeft;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title_rightIv)
    ImageView titleRightIv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_no_cash_credit_card)
    TextView tvNoCashCreditCard;
    @BindView(R.id.ll_no_cash_credit)
    LinearLayout llNoCashCredit;
    @BindView(R.id.rt_no_cash_amount)
    EditText rtNoCashAmount;
    @BindView(R.id.tv_no_cash_debit_card)
    TextView tvNoCashDebitCard;
    @BindView(R.id.ll_no_cash_debit)
    LinearLayout llNoCashDebit;
    @BindView(R.id.bsl_no_card_cash_out)
    BottomSheetLayout bslNoCardCashOut;
    @BindView(R.id.tv_no_card_cashout_confirm)
    TextView tvNoCardCashoutConfirm;
    @BindView(R.id.tv_no_card_cashout_cancel)
    TextView tvNoCardCashoutCancel;

    @OnClick(R.id.tv_no_card_cashout_confirm)
    void tvNoCardCashoutConfirmOnclick() {
        presenter.apply();
    }

    @OnClick(R.id.tv_no_card_cashout_cancel)
    void tvNoCardCashoutCancelOnclick() {
        finish();
    }

    public static List<BankCard> creditCards = new ArrayList<>();
    public static List<BankCard> debitCards = new ArrayList<>();

    @OnClick(R.id.ll_no_cash_credit)
    void llNoCashCreditOnclick() {
        new SelectCardFragment(true).show(getSupportFragmentManager(), R.id.bsl_no_card_cash_out);
    }

    @OnClick(R.id.ll_no_cash_debit)
    void llNoCashDebitOnclick() {
        new SelectCardFragment(false).show(getSupportFragmentManager(), R.id.bsl_no_card_cash_out);
    }

    @Override
    public String setTitleName() {
        return "无卡套现";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_nocard_cashout;
    }

    @Override
    public void onSuccessDebitCard(List<BankCard> debitCards) {
        this.debitCards = debitCards;
    }

    @Override
    public void onSuccessCreditCard(List<BankCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public void applySuccess(String msg) {

    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        showToast(msg);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new NocardCashoutPresenter();
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<NocardCashoutPresenter> loader, NocardCashoutPresenter data) {
        super.onLoadFinished(loader, data);
        presenter.loadCreditCard();
        presenter.loadDebitCard();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
