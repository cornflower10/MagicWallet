package com.qingmang.uilibrary;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.qingmang.baselibrary.utils.SizeUtils;


/**
 * Created by xiejingbao on 2018/1/15.
 */

public class ProgressWebView extends WebView{

    private ProgressBar progressBar;

    public ProgressWebView(Context context) {
        this(context,null);
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ProgressWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    private void init(Context context) {
        progressBar = new ProgressBar(context,null,android.R.attr.progressBarStyleHorizontal);
        progressBar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, SizeUtils.dp2px(2,context),0,0));
        progressBar.setProgressDrawable(ContextCompat.getDrawable(context, R.drawable.progress_status));
        addView(progressBar);

        setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100){
                    progressBar.setVisibility(View.GONE);

                }else
                {
                    if (progressBar.getVisibility() == GONE)
                        progressBar.setVisibility(VISIBLE);
                    progressBar.setProgress(newProgress);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

//        getSettings().setSupportZoom(true);
//        getSettings().setBuiltInZoomControls(true);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {

        if(progressBar!=null){
            LayoutParams lp = (LayoutParams) progressBar.getLayoutParams();
            lp.x = l;
            lp.y = t;
            progressBar.setLayoutParams(lp);
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
