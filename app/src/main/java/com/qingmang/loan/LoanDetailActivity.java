package com.qingmang.loan;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @BindView(R.id.rv_loan_detail_apply_need_data)
    RecyclerView rvLoanDetailApplyNeedData;
    @BindView(R.id.tv_loan_detail_repayment_value_range)
    TextView tvLoanDetailRepaymentValueRange;
    @BindView(R.id.tv_loan_detail_lending_month_range)
    TextView tvLoanDetailLendingMonthRange;

    @OnClick(R.id.tv_loan_detail_apply_lending)
    void tvLoanDetailApplyLendingOnclick() {
        if (TextUtils.isEmpty(etLoanDetailRepaymentValue.getText().toString().trim())) {
            showToast("请输入贷款额度");
        } else if (TextUtils.isEmpty(etLoanDetailLendingMonth.getText().toString().trim())) {
            showToast("请输入还款期限");
        } else {
            presenter.apply(getIntent().getIntExtra("ID", 0),
                    Double.parseDouble(etLoanDetailRepaymentValue.getText().toString().trim()),
                    Integer.parseInt(etLoanDetailLendingMonth.getText().toString().trim()));
        }
    }

    private LoanDetailCondsAdapter loanDetailCondsAdapter;
    private LoanDetailProcessAdapter loanDetailProcessAdapter;
    private LoanDetailNeedDataAdapter loanDetailNeedDataAdapter;
    private LinearLayoutManager linearLayoutManagerCons;
    private LinearLayoutManager linearLayoutManagerProcess;
    private LinearLayoutManager linearLayoutManagerNeedData;
    private List<String> cons = new ArrayList<>();
    private List<String> needData = new ArrayList<>();

    private double lowLoan;
    private double upLoan;
    private double lowTerm;
    private double upTerm;

    @Override
    public String setTitleName() {
        return "贷款详情";
    }

    @Override
    public View getRootView() {
        return rvLoanDetailApplyProcess;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_loan_detail;
    }

    @Override
    public void onSuccess(LoanDetailEntity loanDetailEntity) {

        loadViewHelper.restore();
        initAdapter(loanDetailEntity);

        tvLoanDetailName.setText(loanDetailEntity.getName());
        ImageLoaderUtil.getInstance().showImage(loanDetailEntity.getLogo(), ivLoanDetailIcon, R.mipmap.ic_launcher_round);
        tvDetailHighestValue.setText(loanDetailEntity.getLoanUpper() / 10000 + "");
        if (TextUtils.isEmpty(loanDetailEntity.getNumber())) {
            tvManDetailAmount.setText("0人");
        } else {
            tvManDetailAmount.setText(loanDetailEntity.getNumber() + "人");
        }

        lowLoan = loanDetailEntity.getLoanLower();
        upLoan = loanDetailEntity.getLoanUpper();
        lowTerm = loanDetailEntity.getTermLower();
        upTerm = loanDetailEntity.getTermUpper();

        tvLoanDetailIntroduct.setText(loanDetailEntity.getIntroduct());
        tvLoanDetailRangeValue.setText(loanDetailEntity.getRateLower() + "");

        tvLoanDetailRepaymentValueRange.setText("额度范围" + loanDetailEntity.getLoanLower() + "元-" + loanDetailEntity.getLoanUpper() + "元");
        tvLoanDetailLendingMonthRange.setText("期限范围" + loanDetailEntity.getTermLower() + "月-" + loanDetailEntity.getTermUpper() + "月");

    }

    @Override
    public void applySuccess(String msg) {
        showToast(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2 * 1000);
    }

    private void initAdapter(LoanDetailEntity loanDetailEntity) {
        cons.clear();
        cons = Arrays.asList(loanDetailEntity.getConds().split(","));
        loanDetailCondsAdapter = new LoanDetailCondsAdapter(R.layout.item_loan_detail_cons, cons);
        linearLayoutManagerCons = new LinearLayoutManager(this);

        loanDetailProcessAdapter = new LoanDetailProcessAdapter(R.layout.item_loan_detail_process, loanDetailEntity.getProsList());
        linearLayoutManagerProcess = new LinearLayoutManager(this);
        linearLayoutManagerProcess.setOrientation(LinearLayoutManager.HORIZONTAL);

        needData.clear();
        needData.add("手机号");
        needData.add("银行卡");
        needData.add("个人征信");
        loanDetailNeedDataAdapter = new LoanDetailNeedDataAdapter(R.layout.item_loan_detail_cons, needData);
        linearLayoutManagerNeedData = new LinearLayoutManager(this);

        rvLoanDetailApplyCondition.setLayoutManager(linearLayoutManagerCons);
        rvLoanDetailApplyCondition.setAdapter(loanDetailCondsAdapter);

        rvLoanDetailApplyProcess.setLayoutManager(linearLayoutManagerProcess);
        rvLoanDetailApplyProcess.setAdapter(loanDetailProcessAdapter);

        rvLoanDetailApplyNeedData.setAdapter(loanDetailNeedDataAdapter);
        rvLoanDetailApplyNeedData.setLayoutManager(linearLayoutManagerNeedData);
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

    @Override
    public void onLoadFinished(Loader<LoanDetailPresenter> loader, LoanDetailPresenter data) {
        super.onLoadFinished(loader, data);
        loadViewHelper.showLoading("");
        presenter.load(getIntent().getIntExtra("ID", 0));

        etLoanDetailRepaymentValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    Pattern p = Pattern.compile("^(100|[1-9]\\d|\\d*00)$");
                    Matcher m = p.matcher(s.toString());
                    if (Integer.parseInt(s.toString()) < lowLoan) {
                        showToast("最低贷款额是" + lowLoan);
                    } else if (Integer.parseInt(s.toString()) > upLoan) {
                        showToast("最高贷款额是" + upLoan);
                        etLoanDetailRepaymentValue.setText((int) upLoan + "");
                    } else if (m.find() || ("").equals(s.toString())) {
                        System.out.print("OK!");
                    } else {
                        System.out.print("False!");
                        showToast("只能输入100的倍数");
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(etLoanDetailLendingMonth.getText().toString())) {
                    setMonthReplyValue();
                }
            }
        });

        etLoanDetailLendingMonth.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    if (Integer.parseInt(s.toString()) < lowTerm) {
                        showToast("最低还款期限是" + lowTerm);
                    } else if (Integer.parseInt(s.toString()) > upTerm) {
                        showToast("最高还款期限是" + upTerm);
                        etLoanDetailLendingMonth.setText((int) upTerm + "");
                    } else {
                        if (!TextUtils.isEmpty(etLoanDetailRepaymentValue.getText().toString())) {
                            setMonthReplyValue();
                        }
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onError(String msg) {
        loadViewHelper.restore();
        loadViewHelper.showError(msg, getResources().getString(R.string.click_reload), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.load(getIntent().getIntExtra("ID", 0));
            }
        });
    }

    /**
     * 计算每月还款额度
     */
    private void setMonthReplyValue() {
        double replayMonth = (Double.parseDouble(etLoanDetailRepaymentValue.getText().toString()) + (Double.parseDouble(tvLoanDetailRangeValue.getText().toString()) *
                Double.parseDouble(etLoanDetailRepaymentValue.getText().toString()))) / Double.parseDouble(etLoanDetailLendingMonth.getText().toString());
        BigDecimal bigDecimal = new BigDecimal(replayMonth);
        tvLoanDetailRepaymentValue.setText(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue() + "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
