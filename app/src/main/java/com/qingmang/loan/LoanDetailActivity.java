package com.qingmang.loan;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.loan.entity.LoanDetailEntity;

import butterknife.BindView;

/**
 * Created by jiangpw
 * on 2018/4/11 11:46
 */
public class LoanDetailActivity extends BaseMvpActivity<LoanDetailPresenter, LoanDetailView> implements LoanDetailView<LoanDetailEntity> {
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
    @BindView(R.id.iv_loan_detail_icon)
    ImageView ivLoanDetailIcon;
    @BindView(R.id.tv_loan_detail_name)
    TextView tvLoanDetailName;
    @BindView(R.id.tv_loan_detail_nature1)
    TextView tvLoanDetailNature1;
    @BindView(R.id.tv_loan_detail_nature2)
    TextView tvLoanDetailNature2;
    @BindView(R.id.tv_man_detail_amount)
    TextView tvManDetailAmount;
    @BindView(R.id.tv_man_detail_amount_apply)
    TextView tvManDetailAmountApply;
    @BindView(R.id.tv_detail_highest)
    TextView tvDetailHighest;
    @BindView(R.id.tv_detail_highest_value)
    TextView tvDetailHighestValue;
    @BindView(R.id.tv_detail_highest_value_unit)
    TextView tvDetailHighestValueUnit;
    @BindView(R.id.tv_loan_detail_introduct)
    TextView tvLoanDetailIntroduct;
    @BindView(R.id.et_loan_detail_repayment_value)
    EditText etLoanDetailRepaymentValue;
    @BindView(R.id.et_loan_detail_lending_month)
    EditText etLoanDetailLendingMonth;
    @BindView(R.id.tv_loan_detail_range_value)
    TextView tvLoanDetailRangeValue;
    @BindView(R.id.tv_loan_detail_range_value_unit)
    TextView tvLoanDetailRangeValueUnit;
    @BindView(R.id.tv_loan_detail_repayment_value)
    TextView tvLoanDetailRepaymentValue;
    @BindView(R.id.tv_loan_detail_repayment_value_unit)
    TextView tvLoanDetailRepaymentValueUnit;
    @BindView(R.id.tv_loan_detail_lending_time)
    TextView tvLoanDetailLendingTime;
    @BindView(R.id.tv_loan_detail_lending_time_unit)
    TextView tvLoanDetailLendingTimeUnit;
    @BindView(R.id.rv_loan_detail_apply_process)
    RecyclerView rvLoanDetailApplyProcess;
    @BindView(R.id.rv_loan_detail_apply_condition)
    RecyclerView rvLoanDetailApplyCondition;
    @BindView(R.id.tv_loan_detail_apply_lending)
    TextView tvLoanDetailApplyLending;

    @Override
    public String setTitleName() {
        return "贷款详情";
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_loan_detail;
    }

    @Override
    public void onSuccess(LoanDetailEntity loanDetailEntity) {

    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new LoanDetailPresenter();
            }
        });
    }
}
