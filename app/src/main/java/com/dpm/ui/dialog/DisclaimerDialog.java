package com.dpm.ui.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.dpm.R;
import com.dpm.databinding.DialogDisclaimerBinding;

public class DisclaimerDialog extends Dialog {

    private AppCompatActivity context;
    private DialogDisclaimerBinding disclaimerBinding;

    public DisclaimerDialog(AppCompatActivity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        disclaimerBinding = DialogDisclaimerBinding.inflate(getLayoutInflater());
        setContentView(disclaimerBinding.getRoot());

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setWindowAnimations(R.style.dialogAnimation);
        setCanceledOnTouchOutside(true);
        setCancelable(true);

        disclaimerBinding.tvDisclaimerTitle.setText(context.getResources().getString(R.string.lblDisclaimer));
        disclaimerBinding.tvDisclaimerMsg.setText(context.getResources().getString(R.string.msgDisclaimer));
        disclaimerBinding.tvOk.setText(context.getResources().getString(R.string.lblOk));

        clickEvent();
    }

    private void clickEvent() {
        disclaimerBinding.tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
