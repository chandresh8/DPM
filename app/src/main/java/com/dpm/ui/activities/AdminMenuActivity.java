package com.dpm.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dpm.R;
import com.dpm.databinding.ActivityAdminMenuBinding;
import com.dpm.interfaces.IClickEvent;
import com.dpm.utils.Utils;

public class AdminMenuActivity extends AppCompatActivity implements IClickEvent {

    private ActivityAdminMenuBinding adminMenuBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adminMenuBinding = ActivityAdminMenuBinding.inflate(getLayoutInflater());
        setContentView(adminMenuBinding.getRoot());

        init();
    }

    private void init() {
        Utils.setupOutSideTouchHideKeyboard(adminMenuBinding.lnParentAdminMenu);
        Utils.hideKeyboard(adminMenuBinding.lnParentAdminMenu);

        setLabel();
    }

    private void setLabel() {
        adminMenuBinding.layoutHeader.tvHeaderTitle.setText(getResources().getString(R.string.lblAdminMenu));
        adminMenuBinding.layoutHeader.ivHeaderBack.setVisibility(View.INVISIBLE);

        adminMenuBinding.layoutManageStudents.ivMenu.setImageResource(R.drawable.ic_student);
        adminMenuBinding.layoutManageStudents.tvMenuTitle.setText(getResources().getString(R.string.lblManageStudents));

        adminMenuBinding.layoutManageModules.ivMenu.setImageResource(R.drawable.ic_module);
        adminMenuBinding.layoutManageModules.tvMenuTitle.setText(getResources().getString(R.string.lblManageModules));

        adminMenuBinding.layoutAboutUs.ivMenu.setImageResource(R.drawable.ic_about_us);
        adminMenuBinding.layoutAboutUs.tvMenuTitle.setText(getResources().getString(R.string.lblAboutUs));

        adminMenuBinding.layoutSignOut.ivMenu.setImageResource(R.drawable.ic_sign_out);
        adminMenuBinding.layoutSignOut.tvMenuTitle.setText(getResources().getString(R.string.lblSignOut));
    }

    @Override
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.layoutManageStudents:
                startActivity(new Intent(AdminMenuActivity.this, StudentListActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.layoutManageModules:
                startActivity(new Intent(AdminMenuActivity.this, ModuleListActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case R.id.layoutAboutUs:
                break;

            case R.id.layoutSignOut:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}