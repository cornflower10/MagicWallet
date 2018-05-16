package com.qingmang.user;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.qingmang.R;
import com.qingmang.base.BaseMvpActivity;
import com.qingmang.base.CommonPresenter;
import com.qingmang.base.CommonView;
import com.qingmang.base.PresenterFactory;
import com.qingmang.base.PresenterLoder;

import butterknife.BindView;

public class InviteFriendsActivity extends BaseMvpActivity<CommonPresenter,CommonView> implements CommonView {

    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public String setTitleName() {
        return null;
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public int setContentView() {
        return R.layout.activity_invite_friends;
    }

    @Override
    public void onLoadFinished(Loader<CommonPresenter> loader, CommonPresenter data) {
        super.onLoadFinished(loader, data);
        toolbar.setBackgroundColor(ContextCompat.getColor(this,R.color.liji_c_blue));
        titleName.setText("邀请好友");
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new PresenterLoder(mContext, new PresenterFactory() {
            @Override
            public CommonPresenter crate() {
                return new CommonPresenter();
            }
        });
    }
    @Override
    public void onDataSuccess(Object o) {

    }
}
