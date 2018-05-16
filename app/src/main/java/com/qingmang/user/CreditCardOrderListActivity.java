package com.qingmang.user;

import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.R;
import com.qingmang.adapter.CreditCardOrderAdapter;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;
import com.qingmang.moudle.entity.Loan;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CreditCardOrderListActivity extends BaseMvpActivity<CreitCardOrderListPresenter, CreditCardOrderListView>
        implements CreditCardOrderListView<Loan> ,OnRefreshListener,OnLoadmoreListener {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    private int page = 1;
   private CreditCardOrderAdapter creditCardOrderAdapter;
   private List<Loan.ContentBean> contentBeans = new ArrayList<>();
    @Override
    public String setTitleName() {
        return "订单状态";
    }

    @Override
    public View getRootView() {
        return srl;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_credit_card_order_list;
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public CreitCardOrderListPresenter crate() {
                return new CreitCardOrderListPresenter();
            }
        });
    }

    @Override
    public void onLoadFinished(Loader<CreitCardOrderListPresenter> loader, CreitCardOrderListPresenter data) {
        super.onLoadFinished(loader, data);
        srl.setOnRefreshListener(this);
        srl.setOnLoadmoreListener(this);
        srl.setEnableLoadmore(true);
        loadViewHelper.showLoading("");
        creditCardOrderAdapter = new CreditCardOrderAdapter(contentBeans);
        presenter.creditCardOrderList(page,false);
        rv.setLayoutManager(new LinearLayoutManager(mContext));
        rv.setAdapter(creditCardOrderAdapter);
        creditCardOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                startProgressDialog();
                presenter.deleteCreditCardOrder(contentBeans.get(position).getId());
            }
        });
    }

    @Override
    public void onDataSuccess(Loan creditCardOrder) {
        stopProgressDialog();
        loadViewHelper.restore();
        if(null==creditCardOrder.getContent()||creditCardOrder.getContent().size()==0){
            loadViewHelper.showEmpty();
        }else {
            creditCardOrderAdapter.replaceData(creditCardOrder.getContent());
        }


    }

    @Override
    public void onLoadMoreSuccess(Loan creditCardOrder) {
        srl.finishLoadmore();
        creditCardOrderAdapter.addData(creditCardOrder.getContent());
    }

    @Override
    public void onLoadRefreshSuccess(Loan creditCardOrder) {
        page = 1;
        onDataSuccess(creditCardOrder);
        srl.finishRefresh();
    }

    @Override
    public void onLoadMoreError() {
        srl.finishLoadmore();
        page--;
    }

    @Override
    public void onDeleteSuccess() {
        presenter.creditCardOrderList(1,false);
    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        page++;
        presenter.creditCardOrderListLoadMore(page);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        presenter.creditCardOrderList(page,true);
    }

    @Override
    public void onError(String msg) {
        super.onError(msg);
        srl.finishRefresh();
        srl.finishLoadmore();
        loadViewHelper.restore();
    }
}
