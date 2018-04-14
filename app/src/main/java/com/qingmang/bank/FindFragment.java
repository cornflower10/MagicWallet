package com.qingmang.bank;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.R;
import com.qingmang.adapter.HotBanksAdapter;
import com.qingmang.adapter.HotCreditCardAdapter;
import com.qingmang.base.BaseMvpFragment;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.moudle.entity.Bank;
import com.qingmang.moudle.entity.CreditCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xiejingbao on 2017/9/14.
 */

public class FindFragment extends BaseMvpFragment<FindPresenter, FindView> implements FindView<List<CreditCard>> {


    @BindView(R.id.rv_bank)
    RecyclerView rvBank;
    @BindView(R.id.rv_hot_bankcard)
    RecyclerView rvHotBankcard;
    @BindView(R.id.ll_root)
    LinearLayout llRoot;


    private List<CreditCard> creditCards = new ArrayList<>();
    private List<Bank> banks = new ArrayList<>();
    private HotCreditCardAdapter hotCreditCardAdapter;
    private HotBanksAdapter hotBanksAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initView() {
        LogManager.i("FindFragment-----");
        loadViewHelper.showLoading("");
        hotCreditCardAdapter = new HotCreditCardAdapter(creditCards);
        hotCreditCardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        rvHotBankcard.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotBankcard.setAdapter(hotCreditCardAdapter);


        hotBanksAdapter = new HotBanksAdapter(banks);
        rvBank.setAdapter(hotBanksAdapter);
        hotBanksAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent  = new Intent(mContext,HotBankInfoActivity.class);
                intent.putExtra("id",banks.get(position).getId());
                startActivity(intent);
            }
        });
        rvBank.setLayoutManager(new GridLayoutManager(mContext, 4));
        mPresenter.loadData();
        mPresenter.hotBanks();

    }

    public static FindFragment newInstance() {

        Bundle args = new Bundle();

        FindFragment fragment = new FindFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {

        LogManager.i("FindFragment-----" + isVisible);
    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter();
    }

    @Override
    protected View getRootView() {
        return llRoot;
    }

    @Override
    public void onError(String msg) {
        loadViewHelper.restore();
        showShortToast(msg);
    }

    @Override
    public void onDataSuccess(List<CreditCard> creditCards1) {
        loadViewHelper.restore();
        hotCreditCardAdapter.replaceData(creditCards1);
    }

    @Override
    public void onBankSuccess(List<Bank> banks) {
        loadViewHelper.restore();
        hotBanksAdapter.replaceData(banks);
    }


}
