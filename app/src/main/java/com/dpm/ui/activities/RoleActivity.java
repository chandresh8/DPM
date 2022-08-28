package com.dpm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dpm.R;
import com.dpm.databinding.ActivityRoleBinding;
import com.dpm.interfaces.IAlertClick;
import com.dpm.interfaces.IClickEvent;
import com.dpm.ui.dialog.AdminPasswordDialog;
import com.dpm.ui.dialog.DisclaimerDialog;
import com.dpm.utils.PrefHelper;
import com.dpm.utils.Utils;

public class RoleActivity extends AppCompatActivity implements IClickEvent {

    private ActivityRoleBinding roleBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roleBinding = ActivityRoleBinding.inflate(getLayoutInflater());
        setContentView(roleBinding.getRoot());

        init();
    }

    private void init() {
        Utils.setupOutSideTouchHideKeyboard(roleBinding.lnParentRole);
        Utils.hideKeyboard(roleBinding.lnParentRole);

        setLabel();

        if (!PrefHelper.getInstance().getBoolean(PrefHelper.KEY_IS_DISCLAIMER_OPEN, false)) {
            PrefHelper.getInstance().setBoolean(PrefHelper.KEY_IS_DISCLAIMER_OPEN, true);
            new DisclaimerDialog(RoleActivity.this).show();
        }
    }

    private void setLabel() {
        roleBinding.tvWelcome.setText(getResources().getString(R.string.lblWelcome));
        roleBinding.tvYouAre.setText(getResources().getString(R.string.lblSelectRole));
        roleBinding.tvAdmin.setText(getResources().getString(R.string.lblAdmin));
        roleBinding.tvOr.setText(getResources().getString(R.string.lblOr));
        roleBinding.tvStudent.setText(getResources().getString(R.string.lblStudent));
    }

    @Override
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.rltAdmin:
                new AdminPasswordDialog(RoleActivity.this, new IAlertClick() {
                    @Override
                    public void onYes() {
                        startActivity(new Intent(RoleActivity.this, AdminMenuActivity.class));
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    }

                    @Override
                    public void onNo() {

                    }
                }).show();
                break;

            case R.id.rltStudent:
                startActivity(new Intent(RoleActivity.this, StudentProfileActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }
}