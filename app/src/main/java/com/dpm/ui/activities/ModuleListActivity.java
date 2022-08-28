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
import com.dpm.utils.Utils;

public class ModuleListActivity extends AppCompatActivity implements IClickEvent {

    private ActivityModuleBinding moduleBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moduleBinding = ActivityModuleBinding.inflate(getLayoutInflater());
        setContentView(moduleBinding.getRoot());

        init();
    }

    private void init() {
        Utils.setupOutSideTouchHideKeyboard(moduleBinding.lnParentModule);
        Utils.hideKeyboard(moduleBinding.lnParentModule);

        setLabel();
        setLayoutManager();
        setEventListener();
        setModuleData();
    }

    private void setLabel() {
        moduleBinding.layoutHeader.tvHeaderTitle.setText(getResources().getString(R.string.lblManageModules));
        moduleBinding.layoutHeader.ivHeaderAdd.setVisibility(View.VISIBLE);
    }

    private void setLayoutManager() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        moduleBinding.recyclerModules.setLayoutManager(linearLayoutManager);
    }

    private void setEventListener() {
        moduleBinding.swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                moduleBinding.swipeToRefresh.setRefreshing(false);
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
        ModuleListAdapter moduleListAdapter = (ModuleListAdapter) moduleBinding.recyclerModules.getAdapter();

        if (moduleListAdapter == null) {
            moduleListAdapter = new ModuleListAdapter(this, new IRecyclerView() {
                @Override
                public void onClick(int position, int type) {
                    if (position != -1) {
                        switch (type) {
                            case 1:
                                //View module details
                                break;

                            case 2:
                                //Edit module details
                                break;

                            case 3:
                                //Delete module details
                                break;
                        }
                    }
                }
            });
            moduleBinding.recyclerModules.setAdapter(moduleListAdapter);
        } else {
            moduleListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}