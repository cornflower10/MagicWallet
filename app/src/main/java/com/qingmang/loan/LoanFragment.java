package com.qingmang.loan;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qingmang.R;
import com.qingmang.base.BaseMvpFragment;
import com.qingmang.loan.entity.LoanListEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by jiangpw
 * on 2018/4/10 10:48
 */
public class LoanFragment extends BaseMvpFragment<LoanPresenter, LoanView> implements LoanView<LoanListEntity> {

    @BindView(R.id.rv_loan)
    RecyclerView rvLoan;
    @BindView(R.id.srf_loan)
    SmartRefreshLayout srfLoan;
    Unbinder unbinder;

    private LoanAdapter loanAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int pageNumber = 1;
    private int pageSize = 20;

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
        initRefresh();

        getData(1, 20, null);
    }

    /**
     * 加载刷新
     */
    private void initRefresh() {
        srfLoan.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData(1, 20, null);
                srfLoan.finishRefresh(2000, true);
            }
        });
        srfLoan.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                getData(2, 20, null);
                srfLoan.finishLoadmore(2000, true);
            }
        });
    }

    @Override
    public void onDataSuccess(LoanListEntity loanListEntity) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        loanAdapter = new LoanAdapter(R.layout.item_loan, loanListEntity.getContent());
        rvLoan.setAdapter(loanAdapter);
        rvLoan.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onError(String msg) {
        loadViewHelper.showError(msg, getResources().getString(R.string.click_reload), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(1, 20, null);
            }
        });
    }

    /**
     * 获取数据
     *
     * @param pageNumber
     * @param pageSize
     * @param orderCond
     */
    private void getData(int pageNumber, int pageSize, String orderCond) {
        mPresenter.loadData(pageNumber, pageSize, orderCond);
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
