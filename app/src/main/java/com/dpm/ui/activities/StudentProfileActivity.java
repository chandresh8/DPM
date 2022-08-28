package com.dpm.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.dpm.R;
import com.dpm.databinding.ActivityStudentProfileBinding;
import com.dpm.interfaces.IClickEvent;
import com.dpm.utils.Utils;

public class StudentProfileActivity extends AppCompatActivity implements IClickEvent {

    private ActivityStudentProfileBinding studentProfileBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentProfileBinding = ActivityStudentProfileBinding.inflate(getLayoutInflater());
        setContentView(studentProfileBinding.getRoot());

        init();
    }

    private void init() {
        Utils.setupOutSideTouchHideKeyboard(studentProfileBinding.lnParentStudentProfile);
        Utils.hideKeyboard(studentProfileBinding.lnParentStudentProfile);

        setLabel();
    }

    private void setLabel() {
        studentProfileBinding.layoutHeader.tvHeaderTitle.setText(getResources().getString(R.string.lblCreateProfile));

        studentProfileBinding.evStudentId.setHint(getResources().getString(R.string.lblId));
        studentProfileBinding.evName.setHint(getResources().getString(R.string.lblName));
        studentProfileBinding.evContactNo.setHint(getResources().getString(R.string.lblContactNo));
        studentProfileBinding.evAddress.setHint(getResources().getString(R.string.lblAddress));

        studentProfileBinding.tvCreateProfile.setText(getResources().getString(R.string.lblCreateProfile1));
    }

    @Override
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ivHeaderBack:
                onBackPressed();
                break;

            case R.id.tvCreateProfile:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}