package com.qingmang.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qingmang.App;
import com.qingmang.R;
import com.qingmang.bank.BankCardListActivity;
import com.qingmang.bank.DebitCardListActivity;
import com.qingmang.base.BaseMvpFragment;
import com.qingmang.baselibrary.utils.LogManager;
import com.qingmang.moudle.entity.CustomerInfo;
import com.qingmang.moudle.entity.UtilBox;
import com.qingmang.nocardcashout.NocardCashoutActivity;
import com.qingmang.user.adapter.UtilBoxAdapter;
import com.qingmang.utils.imageload.ImageLoaderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by xiejingbao on 2017/9/14.
 */

public class MyFragment extends BaseMvpFragment<MyPresenter, MyView> implements MyView<CustomerInfo> {
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.tv_yhj)
    TextView tvYhj;
    @BindView(R.id.tv_dzf)
    TextView tvDzf;
    private UtilBoxAdapter utilBoxAdapter;
    private List<UtilBox> utilBoxes = new ArrayList<>();
    private CustomerInfo customerInfo;

    @Override
    protected View getRootView() {
        return null;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        LogManager.i("MyFragment-----");

        utilBoxes.add(new UtilBox(1, "征信", R.drawable.icon_my_zx));
        utilBoxes.add(new UtilBox(2, "公积金", R.drawable.icon_my_gjj));
        utilBoxes.add(new UtilBox(3, "无卡套现", R.drawable.icon_my_wktx));
        utilBoxes.add(new UtilBox(4, "绑定储蓄卡", R.drawable.icon_my_wktx));
        utilBoxes.add(new UtilBox(5, "绑定信用卡", R.drawable.icon_my_wktx));
        utilBoxes.add(new UtilBox(6, "信用卡自动还款", R.drawable.icon_my_zdhk));
        utilBoxes.add(new UtilBox(7, "信用卡自动查询", R.drawable.icon_my_jdcx));

        utilBoxAdapter = new UtilBoxAdapter(utilBoxes);
        rv.setAdapter(utilBoxAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 4);
        rv.setLayoutManager(layoutManager);
        utilBoxAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                showToast("正在维护中...");
            }
        });

        utilBoxAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        showToast("该功能暂未投入使用！");
                        break;
                    case 1:
                        showToast("该功能暂未投入使用！");
                        break;
                    case 2:
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), NocardCashoutActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        if(App.getInstance().isLogin())
                        startActivity(DebitCardListActivity.class);
                        else {
                            startActivity(LoginActivity.class);
                        }
                        break;
                    case 4:
                        if(App.getInstance().isLogin())
                        startActivity(BankCardListActivity.class);
                        else {
                            startActivity(LoginActivity.class);
                        }
                        break;
                    case 5:
                        showToast("该功能暂未投入使用！");
                        break;
                    case 6:
                        showToast("该功能暂未投入使用！");
                        break;
                    case 7:
                        showToast("该功能暂未投入使用！");
                        break;
//                    case 8:
//                        showToast("该功能暂未投入使用！");
//                        break;

                }
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        if(App.getInstance().isLogin()){
            mPresenter.loadData();
        }else {
            startActivity(LoginActivity.class);
        }

    }

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        LogManager.i("MyFragment-----" + isVisible);
    }

    @Override
    public void onError(String msg) {
       jumpLoign(msg);
    }

    @Override
    public void onDataSuccess(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
        tvName.setText(customerInfo.getName());
        tvAmount.setText(String.valueOf(customerInfo.getLoanbalance()));
        tvYhj.setText(String.valueOf(customerInfo.getCreditnum()));
        tvDzf.setText(String.valueOf(customerInfo.getSmallchange()));
        ImageLoaderUtil.getInstance().loadCircleImage(customerInfo.getAvatar(), ivHeader, 0);
    }

    @Override
    protected MyPresenter createPresenter() {
        return new MyPresenter();
    }


    @OnClick({R.id.iv_setting, R.id.ll_rzqy, R.id.rl_dzf, R.id.rl_yhj,R.id.ll_yq})
    public void onViewClicked(View view) {
//        if(!App.getInstance().isLogin()){
//            startActivity(LoginActivity.class);
//            return;
//        }
        switch (view.getId()) {
            case R.id.iv_setting:
                if(App.getInstance().isLogin()){
                    if(null==customerInfo){
                        return;
                    }
                    Intent intent = new Intent(mContext, SettingActivity.class);
                    intent.putExtra("customerInfo", customerInfo);
                    startActivity(intent);
                }else {
                    startActivity(LoginActivity.class);
                }

                break;
            case R.id.ll_rzqy:
                if(App.getInstance().isLogin())
                    startActivity(CreditCardOrderListActivity.class);
                else {
                    startActivity(LoginActivity.class);
                }

                break;
            case R.id.rl_dzf:
//                ((MainActivity) mContext).chooseTab(3);
                break;
            case R.id.rl_yhj:
//                startActivity(BankCardListActivity.class);
                break;
            case R.id.ll_yq:
//                startActivity(BankCardListActivity.class);
                if(App.getInstance().isLogin())
                    startActivity(InviteFriendsActivity.class);
                else {
                    startActivity(LoginActivity.class);
                }

                break;
        }


    }
}
