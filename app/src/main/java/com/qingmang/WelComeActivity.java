package com.qingmang;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.qingmang.base.BaseActivity;

public class WelComeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel_come);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelComeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }

    @Override
    public void onBackPressed() {
    }
}
