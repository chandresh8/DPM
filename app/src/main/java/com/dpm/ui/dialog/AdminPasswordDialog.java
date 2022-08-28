package com.dpm.ui.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.dpm.R;
import com.dpm.databinding.DialogPasswordBinding;
import com.dpm.interfaces.IAlertClick;
import com.dpm.utils.Utils;

public class AdminPasswordDialog extends Dialog {

    private AppCompatActivity context;
    private DialogPasswordBinding dialogPasswordBinding;
    private IAlertClick callback;
    private boolean isShowPassword = false;

    public AdminPasswordDialog(AppCompatActivity context, IAlertClick callback) {
        super(context);
        this.context = context;
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPasswordBinding = DialogPasswordBinding.inflate(getLayoutInflater());
        setContentView(dialogPasswordBinding.getRoot());

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setWindowAnimations(R.style.dialogAnimation);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        dialogPasswordBinding.tvAdminPassword.setText(context.getResources().getString(R.string.lblAdminPassword));
        dialogPasswordBinding.evPassword.setHint(context.getResources().getString(R.string.lblEnterPassword));
        dialogPasswordBinding.tvCancel.setText(context.getResources().getString(R.string.lblCancel));
        dialogPasswordBinding.tvLogin.setText(context.getResources().getString(R.string.lblLogin));

        clickEvent();
    }

    private void clickEvent() {

        dialogPasswordBinding.ivPasswordVisibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Typeface font = ResourcesCompat.getFont(context, R.font.rubik_regular);
                if (isShowPassword) {
                    isShowPassword = false;
                    dialogPasswordBinding.evPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    dialogPasswordBinding.evPassword.setSelection(dialogPasswordBinding.evPassword.length());
                    dialogPasswordBinding.ivPasswordVisibility.setImageResource(R.drawable.ic_password_visible);
                } else {
                    isShowPassword = true;
                    dialogPasswordBinding.evPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    dialogPasswordBinding.evPassword.setSelection(dialogPasswordBinding.evPassword.length());
                    dialogPasswordBinding.ivPasswordVisibility.setImageResource(R.drawable.ic_password_invisible);
                }

                dialogPasswordBinding.evPassword.setTypeface(font);
            }
        });

        dialogPasswordBinding.tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        dialogPasswordBinding.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String adminPassword = dialogPasswordBinding.evPassword.getText().toString().trim();
                if (TextUtils.isEmpty(adminPassword)) {
                    Utils.showSnackBar(dialogPasswordBinding.rltParentCouponCode,
                            context.getResources().getString(R.string.errorMsgEnterAdminPassword));
                } else if (!adminPassword.equals(context.getResources().getString(R.string.adminPassword))) {
                    Utils.showSnackBar(dialogPasswordBinding.rltParentCouponCode,
                            context.getResources().getString(R.string.errorMsgValidAdminPassword));
                } else {
                    Utils.hideKeyboard(dialogPasswordBinding.rltParentCouponCode);
                    dismiss();
                    if (callback != null) {
                        callback.onYes();
                    }
                }
            }
        });
    }
}
