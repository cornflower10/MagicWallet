package com.qingmang.loan;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingmang.R;
import com.qingmang.base.BaseMvpFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by jiangpw
 * on 2018/4/10 10:48
 */
public class LoanFragment extends BaseMvpFragment<LoanPresenter, LoanView> implements LoanView<Loan> {

    @BindView(R.id.rv_loan)
    RecyclerView rvLoan;
    @BindView(R.id.srf_loan)
    SmartRefreshLayout srfLoan;
    Unbinder unbinder;

    @Override
    protected LoanPresenter createPresenter() {
        return new LoanPresenter();
    }

    @Override
    protected View getRootView() {
        return srfLoan;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_loan;
    }

    @Override
    protected void initView() {
        loadViewHelper.showLoading("加载中...");
        mPresenter.loadData(1,20,null);
    }

    @Override
    public void onDataSuccess(Loan loan) {

    }

    @Override
    public void onError(String msg) {

    }

    public static LoanFragment newInstance() {

        Bundle args = new Bundle();

        LoanFragment fragment = new LoanFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
