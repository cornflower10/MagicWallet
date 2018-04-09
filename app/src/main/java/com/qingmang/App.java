package com.qingmang;

import android.app.Application;
import android.content.Context;

import com.qingmang.baselibrary.utils.AppUtils;
import com.qingmang.baselibrary.utils.ForegroundCallbacks;
import com.qlzgzg.network.RetrofitServiceManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * Created by xiejingbao on 2017/9/14.
 */

public class App extends Application {

    private  RetrofitServiceManager retrofitServiceManager;

    private ForegroundCallbacks foregroundCallbacks;

    private static App singleton;
    // Returns the application instance
    public static App getInstance() {
        return singleton;
    }

    public ForegroundCallbacks getForegroundCallbacks() {
        return foregroundCallbacks;
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context);
            }
        });
    }
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        retrofitServiceManager  = RetrofitServiceManager.getInstance(BuildConfig.URL);
        foregroundCallbacks = ForegroundCallbacks.init(this);

//        /**
//         * 初始化log,获取是否为debug模式
//          */
        AppUtils.syncIsDebug(getApplicationContext());
        AppUtils.isDebug();

    }

    public RetrofitServiceManager getRetrofitServiceManager() {
        return retrofitServiceManager;
    }
}
