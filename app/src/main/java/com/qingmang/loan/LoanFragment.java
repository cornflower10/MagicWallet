package com.qingmang.loan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.R;
import com.qingmang.base.BaseMvpFragment;
import com.qingmang.loan.entity.LoanListEntity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * Created by jiangpw
 * on 2018/4/10 10:48
 */
public class LoanFragment extends BaseMvpFragment<LoanPresenter, LoanView> implements LoanView<LoanListEntity> {

    @BindView(R.id.rv_loan)
    RecyclerView rvLoan;
    @BindView(R.id.srf_loan)
    SmartRefreshLayout srfLoan;
    @BindView(R.id.tb_loan)
    TabLayout tbLoan;

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
        loadViewHelper.showLoading("");
        initTab();
        initRefresh();

        getData(1, 20, null, false);
    }

    /**
     * 初始化Tab
     */
    private void initTab() {
        tbLoan.addTab(tbLoan.newTab().setText(R.string.loan_default_sorting));
        tbLoan.addTab(tbLoan.newTab().setText(R.string.loan_lowest_rate));
        tbLoan.addTab(tbLoan.newTab().setText(R.string.loan_highest_quota));

        tbLoan.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getData(1, 20, null, false);
                        break;
                    case 1:
                        getData(1, 20, "rate", false);
                        break;
                    case 2:
                        getData(1, 20, "loan", false);
                        break;
                    case 3:
                        getData(1, 20, null, false);
                        break;
                    default:
                        getData(1, 20, null, false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 加载刷新
     */
    private void initRefresh() {
        srfLoan.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                srfLoan.finishRefresh(2000, true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(1, 20, null, false);
                    }
                }, 2000);
            }
        });
        srfLoan.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                srfLoan.finishLoadmore(2000, true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData(1, 20, null, true);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onDataSuccess(final LoanListEntity loanListEntity) {
        loadViewHelper.restore();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        loanAdapter = new LoanAdapter(R.layout.item_loan, loanListEntity.getContent());
        rvLoan.setAdapter(loanAdapter);
        rvLoan.setLayoutManager(linearLayoutManager);

        loanAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), LoanDetailActivity.class);
                intent.putExtra("ID", loanListEntity.getContent().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoadMore(LoanListEntity loanListEntity) {
        loanAdapter.addData(loanListEntity.getContent());
    }

    @Override
    public void onError(String msg) {
        loadViewHelper.restore();
        loadViewHelper.showError(msg, getResources().getString(R.string.click_reload), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(1, 20, null, false);
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
    private void getData(int pageNumber, int pageSize, String orderCond, boolean isLoadMore) {
        mPresenter.loadData(pageNumber, pageSize, orderCond, isLoadMore);
    }

    public static LoanFragment newInstance() {

        Bundle args = new Bundle();

        LoanFragment fragment = new LoanFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
