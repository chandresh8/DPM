package com.dpm.utils;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;

import com.dpm.MyApplication;

import java.lang.reflect.Method;

public class PrefHelper {

    private static final String APP_PREF = "appPreference";
    private static final Method sApplyMethod = findApplyMethod();
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static PrefHelper instance;

    public static final String KEY_IS_DISCLAIMER_OPEN = "isDisclaimerOpen";

    private PrefHelper() {
    }

    @SuppressLint("CommitPrefEdits")
    public static PrefHelper getInstance() {
        int PRIVATE_MODE = 0;
        preferences = MyApplication.getInstance().getSharedPreferences(APP_PREF, PRIVATE_MODE);
        editor = preferences.edit();
        if (instance == null) {
            instance = new PrefHelper();
        }
        return instance;
    }

    private static Method findApplyMethod() {
        try {
            final Class<SharedPreferences.Editor> cls = SharedPreferences.Editor.class;
            return cls.getMethod("apply");
        } catch (final Exception unused) {
            unused.printStackTrace();
        }
        return null;
    }

    private static void apply(final SharedPreferences.Editor editor) {
        if (sApplyMethod != null) {
            try {
                sApplyMethod.invoke(editor);
                return;
            } catch (final Exception unused) {
                unused.printStackTrace();
            }
        }
        editor.commit();
    }

    public int getInt(final String key, final int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public void setInt(final String key, final int value) {
        editor.putInt(key, value);
        apply(editor);
    }

    public String getString(final String key, final String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public void setString(final String key, final String value) {
        editor.putString(key, value);
        apply(editor);
    }

    public boolean getBoolean(final String key, final boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public void setBoolean(final String key, final boolean value) {
        editor.putBoolean(key, value);
        apply(editor);
    }
}