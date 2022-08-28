package com.dpm;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class MyApplication extends Application {

    private static MyApplication appInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static synchronized MyApplication getInstance() {
        return appInstance;
    }
}
