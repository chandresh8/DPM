package com.dpm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.databinding.ItemModuleBinding;
import com.dpm.interfaces.IRecyclerView;


public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ViewHolder> {

    private Context context;
    private IRecyclerView callback;

    public ModuleListAdapter(Context context, IRecyclerView callback) {
        this.context = context;
        this.callback = callback;
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int arg1) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        ItemModuleBinding itemModuleBinding = ItemModuleBinding.inflate(inflater);
        return new ViewHolder(itemModuleBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        clickEvent(holder);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemModuleBinding itemModuleBinding;

        ViewHolder(ItemModuleBinding binding) {
            super(binding.getRoot());
            this.itemModuleBinding = binding;
        }
    }

    private void clickEvent(final ViewHolder holder) {
        holder.itemModuleBinding.rlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemModuleBinding.swipeLayout.isRightOpen()) {
                    holder.itemModuleBinding.swipeLayout.close(true);
                    //callback.onClick(holder.getAdapterPosition(), 2);
                }
            }
        });

        holder.itemModuleBinding.rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemModuleBinding.swipeLayout.isRightOpen()) {
                    holder.itemModuleBinding.swipeLayout.close(true);
                    //callback.onClick(holder.getAdapterPosition(), 3);
                }
            }
        });
    }
}
