package com.dpm.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.dpm.MyApplication;
import com.dpm.R;
import com.google.android.material.snackbar.Snackbar;

public class Utils {

    @SuppressLint("ClickableViewAccessibility")
    public static void setupOutSideTouchHideKeyboard(final View view) {
        if (!(view instanceof AppCompatEditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(view);
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupOutSideTouchHideKeyboard(innerView);
            }
        }
    }

    public static void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) MyApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) MyApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(v.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
    }

    public static void showSnackBar(View view, String message) {
        if (view.getParent() != null) {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            View subView = snackbar.getView();
            TextView textView = subView.findViewById(com.google.android.material.R.id.snackbar_text);
            textView.setPadding(5, 5, 5, 5);
            Typeface font = ResourcesCompat.getFont(MyApplication.getInstance(), R.font.rubik_regular);
            textView.setTypeface(font);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) subView.getLayoutParams();
            params.width = FrameLayout.LayoutParams.MATCH_PARENT;
            snackbar.show();
        }
    }

    public static void slideToAbove(Context context, View viewFooter, View viewPhotos) {
        Animation bottomUp = AnimationUtils.loadAnimation(context, R.anim.bottom_up);
        viewFooter.startAnimation(bottomUp);
        viewFooter.setVisibility(View.VISIBLE);
        viewPhotos.setVisibility(View.VISIBLE);
    }

    public static void slideToDown(Context context, final View viewFooter, View viewPhotos) {
        viewPhotos.setVisibility(View.GONE);
        Animation bottomDown = AnimationUtils.loadAnimation(context, R.anim.bottom_down);
        viewFooter.startAnimation(bottomDown);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                viewFooter.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public static Drawable changeDrawableColor(View view, String colorCode) {
        Drawable drawable = view.getBackground();
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(colorCode));
        return wrappedDrawable;
    }

    public static Drawable changeDrawableColor(AppCompatImageView view, String colorCode) {
        Drawable drawable = view.getDrawable();
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor(colorCode));
        return wrappedDrawable;
    }
}