package com.qingmang.bank;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.qingmang.R;
import com.qingmang.adapter.BankCardAdapter;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.Presenter;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.moudle.entity.BankCard;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DebitCardListActivity extends BaseMvpActivity<BankCardPresenter, BankCardView> implements BankCardView<List<BankCard>> {

    @BindView(R.id.title_rightIv)
    ImageView titleRightIv;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.ll_root)
    NestedScrollView llRoot;

    private BankCardAdapter bankCardAdapter;
    private List<BankCard> bankCardList = new ArrayList<>();

    @Override
    public String setTitleName() {
        return "储蓄卡";
    }

    @Override
    public View getRootView() {
        return llRoot;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_bank_card_list;
    }


    @Override
    public void onError(String msg) {
        super.onError(msg);
        loadViewHelper.restore();
    }

    @Override
    public void onDataSuccess(List<BankCard> bankCards) {
        loadViewHelper.restore();
        bankCardAdapter.replaceData(bankCards);
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public Presenter crate() {
                return new BankCardPresenter();
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<BankCardPresenter> loader, BankCardPresenter data) {
        super.onLoadFinished(loader, data);
        titleRightIv.setVisibility(View.VISIBLE);
        titleRightIv.setBackgroundResource(R.drawable.ic_add_black_24dp);
        loadViewHelper.showLoading("");
        presenter.loadDepositCards();
        bankCardAdapter =new BankCardAdapter(bankCardList);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(bankCardAdapter);
        rv.setNestedScrollingEnabled(false);
    }

    @OnClick({R.id.title_rightIv, R.id.iv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_rightIv:
                startActivity(BankCardAddActivity.class);
                break;
            case R.id.iv_add:
                startActivity(BankCardAddActivity.class);
                break;
        }
    }
}
