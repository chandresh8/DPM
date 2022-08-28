package com.dpm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.dpm.R;
import com.dpm.databinding.ActivitySplashBinding;
import com.dpm.utils.Utils;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding splashBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        splashBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(splashBinding.getRoot());

        init();
    }

    private void init() {
        Utils.setupOutSideTouchHideKeyboard(splashBinding.rltParentSplash);
        Utils.hideKeyboard(splashBinding.rltParentSplash);

        setLabel();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, RoleActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 700);
    }

    private void setLabel() {
        splashBinding.tvAppTitle.setText(getResources().getString(R.string.lblAppName));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}