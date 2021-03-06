package com.qingmang;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.qingmang.bank.FindFragment;
import com.qingmang.base.BaseActivity;
import com.qingmang.home.HomeFragment;
import com.qingmang.loan.LoanFragment;
import com.qingmang.uilibrary.BottomBar;
import com.qingmang.uilibrary.BottomBarTab;
import com.qingmang.user.LoginActivity;
import com.qingmang.user.MyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qingmang.R.id.fl_container;

public class MainActivity extends BaseActivity {

    @BindView(fl_container)
    FrameLayout flContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private HomeFragment homeFragment;
    private LoanFragment loanFragment;
    private MyFragment myFragment;
    private FindFragment findFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment(savedInstanceState);
        initView();
    }


    private void initView() {
        mBottomBar = (BottomBar) findViewById(R.id.bottomBar);

        mBottomBar.addItem(new BottomBarTab(this, R.drawable.icon_tab_home,R.drawable.icon_tab_home_cur))
                .addItem(new BottomBarTab(this, R.drawable.icon_tab_loan,R.drawable.icon_tab_loan_cur))
                .addItem(new BottomBarTab(this, R.drawable.icon_tab_card,R.drawable.icon_tab_card_cur))
                .addItem(new BottomBarTab(this, R.drawable.icon_tab_my, R.drawable.icon_tab_my_cur));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (position) {
                    case 0:
                        if (null == homeFragment) {
                            homeFragment = HomeFragment.newInstance();
                            transaction.add(R.id.fl_container, homeFragment);
                        }
                        showHideFragment(homeFragment);
                        break;
                    case 1:
                        if (null == loanFragment) {
                            loanFragment = LoanFragment.newInstance();
                            transaction.add(R.id.fl_container, loanFragment);
                        }
                        showHideFragment(loanFragment);
                        break;
                    case 2:
                        if (null == findFragment) {
                            findFragment = FindFragment.newInstance();
                            transaction.add(R.id.fl_container, findFragment);
                        }
                        showHideFragment(findFragment);
                        break;
                    case 3:
                        if(App.getInstance().isLogin()){
                            if (null == myFragment) {
                                myFragment = MyFragment.newInstance();
                                transaction.add(R.id.fl_container, myFragment);
                            }
                            showHideFragment(myFragment);
                        }else {
                            startActivity(LoginActivity.class);
                            mBottomBar.setCurrentItem(0);
                        }

                        break;

                }
                transaction.commit();

            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
//                final Fragment currentFragment = mFragments.get(position);
//                int count = currentFragment.getChildFragmentManager().getBackStackEntryCount();
//
//                // 如果不在该类别Fragment的主页,则回到主页;
//                if (count > 1) {
//                    if (currentFragment instanceof ZhihuFirstFragment) {
//                        currentFragment.popToChild(FirstHomeFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuSecondFragment) {
//                        currentFragment.popToChild(ViewPagerFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuThirdFragment) {
//                        currentFragment.popToChild(ShopFragment.class, false);
//                    } else if (currentFragment instanceof ZhihuFourthFragment) {
//                        currentFragment.popToChild(MeFragment.class, false);
//                    }
//                    return;
//                }


                // 这里推荐使用EventBus来实现 -> 解耦
//                if (count == 1) {
//                    // 在FirstPagerFragment中接收, 因为是嵌套的孙子Fragment 所以用EventBus比较方便
//                    // 主要为了交互: 重选tab 如果列表不在顶部则移动到顶部,如果已经在顶部,则刷新
//                    EventBus.getDefault().post(new TabSelectedEvent(position));
//                }
            }
        });
        mBottomBar.setCurrentItem(0);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        FragmentManager manager = getSupportFragmentManager();
        if (null != homeFragment && homeFragment.isAdded()) {
            manager.putFragment(outState, "homeFragment", homeFragment);
        }
        if (null != loanFragment && loanFragment.isAdded()) {
            manager.putFragment(outState, "loanFragment", loanFragment);
        }
        if (null != myFragment && myFragment.isAdded()) {
            manager.putFragment(outState, "myFragment", myFragment);
        }
        if (null != findFragment && findFragment.isAdded()) {
            manager.putFragment(outState, "findFragment", findFragment);
        }

    }

    /**
     * 初始化fragment的记忆状态
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {

            homeFragment = (HomeFragment) getSupportFragmentManager().getFragment(savedInstanceState, "homeFragment");
            loanFragment = (LoanFragment) getSupportFragmentManager().getFragment(savedInstanceState, "loanFragment");
            myFragment = (MyFragment) getSupportFragmentManager().getFragment(savedInstanceState, "myFragment");
            findFragment = (FindFragment) getSupportFragmentManager().getFragment(savedInstanceState, "findFragment");

        } else {
            homeFragment = HomeFragment.newInstance();
//            myFragment = MyFragment.newInstance();
//            findFragment = FindFragment.newInstance();
        }
        if (null != homeFragment)
            mFragments.add(homeFragment);
        if (null != loanFragment)
            mFragments.add(loanFragment);
        if (null != findFragment)
            mFragments.add(findFragment);
        if (null != myFragment)
            mFragments.add(myFragment);

        if (null != homeFragment)
            transaction.add(R.id.fl_container, homeFragment);
        if (null != loanFragment)
            transaction.add(R.id.fl_container, loanFragment);
        if (null != findFragment)
            transaction.add(R.id.fl_container, findFragment);
        if (null != myFragment)
            transaction.add(R.id.fl_container, myFragment);

        if (null != loanFragment)
            transaction.hide(loanFragment);
        if (null != myFragment)
            transaction.hide(myFragment);
        if (null != findFragment)
            transaction.hide(findFragment);
        transaction.commit();
    }

    public void showHideFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (!mFragments.contains(fragment)) {
            mFragments.add(fragment);
        }
        for (Fragment f : mFragments) {
            if (fragment.equals(f)) {
                fragmentTransaction.show(fragment);
            } else
                fragmentTransaction.hide(f);

        }
        fragmentTransaction.commit();
    }


    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再点一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                App.getInstance().getForegroundCallbacks().appExit();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
