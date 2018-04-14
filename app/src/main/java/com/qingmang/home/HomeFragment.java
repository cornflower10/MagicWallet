package com.qingmang.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.R;
import com.qingmang.adapter.HotCreditCardAdapter;
import com.qingmang.bank.HotBankInfoActivity;
import com.qingmang.base.BaseMvpFragment;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.loan.LoanAdapter;
import com.qingmang.loan.LoanDetailActivity;
import com.qingmang.loan.entity.LoanListEntity;
import com.qingmang.moudle.entity.CreditCard;
import com.qingmang.uilibrary.BottomBar;
import com.yyydjk.library.BannerLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiejingbao on 2017/9/14.
 */

public class HomeFragment extends BaseMvpFragment<HomePresenter, HomeView> implements HomeView<LoanListEntity> {
    @BindView(R.id.home_banner)
    BannerLayout homeBanner;
    @BindView(R.id.tv_home_loan_more)
    TextView tvHomeLoanMore;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.rv_home_hot_card)
    RecyclerView rvHomeHotCard;
    @BindView(R.id.tv_home_loan_card_more)
    TextView tvHomeLoanCardMore;

    @OnClick(R.id.tv_home_loan_more)
    void tvHomeLoanMoreOnclick() {
        BottomBar bottomBar = (BottomBar) getActivity().findViewById(R.id.bottomBar);
        bottomBar.setCurrentItem(1);
    }

    @OnClick(R.id.tv_home_loan_card_more)
    void tvHomeLoanCardMoreOnclick() {
        BottomBar bottomBar = (BottomBar) getActivity().findViewById(R.id.bottomBar);
        bottomBar.setCurrentItem(2);
    }

    private LoanAdapter loanAdapter;
    private LinearLayoutManager linearLayoutManager;

    private HotCreditCardAdapter hotCreditCardAdapter;
    private List<CreditCard> creditCards = new ArrayList<>();

    @Override
    protected View getRootView() {
        return rvHome;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initView() {
        loadViewHelper.showLoading("");
        initBanner();
        getData(1, 20, null, false);
    }

    /**
     * 初始化Banner
     */
    private void initBanner() {
        List<String> urls = new ArrayList<>();
        urls.add("http://market.haiyunsmart.cn/img/home_banner.png");
        urls.add("http://market.haiyunsmart.cn/img/home_banner.png");
        urls.add("http://market.haiyunsmart.cn/img/home_banner.png");
        urls.add("http://market.haiyunsmart.cn/img/home_banner.png");
        urls.add("http://market.haiyunsmart.cn/img/home_banner.png");
        homeBanner.setViewUrls(urls);
        homeBanner.startAutoPlay();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        LogManager.i("HomeFragment-----" + isVisible);
    }

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    public void bannerSuccess(LoanListEntity loanListEntity) {

    }

    @Override
    public void loanSuccess(final LoanListEntity loanListEntity) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        loanAdapter = new LoanAdapter(R.layout.item_loan_hot, loanListEntity.getContent());
        rvHome.setAdapter(loanAdapter);
        rvHome.setLayoutManager(linearLayoutManager);

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
    public void messageSuccess() {

    }

    @Override
    public void onCardSuccess(final List<CreditCard> creditCards) {
        loadViewHelper.restore();
        hotCreditCardAdapter = new HotCreditCardAdapter(creditCards);
        rvHomeHotCard.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHomeHotCard.setAdapter(hotCreditCardAdapter);

        hotCreditCardAdapter.replaceData(creditCards);

        hotCreditCardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), HotBankInfoActivity.class);
                intent.putExtra("id", creditCards.get(position).getId());
                startActivity(intent);
            }
        });
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
        mPresenter.loadLoan(pageNumber, pageSize, orderCond, isLoadMore);
        mPresenter.hotBanks();
    }
}
