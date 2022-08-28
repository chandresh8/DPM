package com.dpm.ui.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dpm.R;
import com.dpm.databinding.ActivityModuleBinding;
import com.dpm.interfaces.IClickEvent;
import com.dpm.interfaces.IRecyclerView;
import com.dpm.ui.adapter.ModuleListAdapter;
import com.dpm.ui.adapter.StudentListAdapter;
import com.dpm.utils.Utils;

public class StudentListActivity extends AppCompatActivity implements IClickEvent {

    private ActivityModuleBinding studentBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        studentBinding = ActivityModuleBinding.inflate(getLayoutInflater());
        setContentView(studentBinding.getRoot());

        init();
    }

    private void init() {
        Utils.setupOutSideTouchHideKeyboard(studentBinding.lnParentModule);
        Utils.hideKeyboard(studentBinding.lnParentModule);

        setLabel();
        setLayoutManager();
        setEventListener();
        setModuleData();
    }

    private void setLabel() {
        studentBinding.layoutHeader.tvHeaderTitle.setText(getResources().getString(R.string.lblManageStudents));
        studentBinding.layoutHeader.ivHeaderAdd.setVisibility(View.VISIBLE);
    }

    private void setLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        studentBinding.recyclerModules.setLayoutManager(linearLayoutManager);
    }

    private void setEventListener() {
        studentBinding.swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                studentBinding.swipeToRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ivHeaderBack:
                onBackPressed();
                break;

            case R.id.ivHeaderAdd:
                break;
        }
    }

    private void setModuleData() {
        StudentListAdapter studentListAdapter = (StudentListAdapter) studentBinding.recyclerModules.getAdapter();

        if (studentListAdapter == null) {
            studentListAdapter = new StudentListAdapter(this, new IRecyclerView() {
                @Override
                public void onClick(int position, int type) {
                    if (position != -1) {
                        switch (type) {
                            case 1:
                                //View student details
                                break;

                            case 2:
                                //Edit student details
                                break;

                            case 3:
                                //Delete student details
                                break;
                        }
                    }
                }
            });
            studentBinding.recyclerModules.setAdapter(studentListAdapter);
        } else {
            studentListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}