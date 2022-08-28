package com.dpm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dpm.databinding.ItemStudentBinding;
import com.dpm.interfaces.IRecyclerView;


public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    private Context context;
    private IRecyclerView callback;

    public StudentListAdapter(Context context, IRecyclerView callback) {
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
        ItemStudentBinding itemStudentBinding = ItemStudentBinding.inflate(inflater);
        return new ViewHolder(itemStudentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        clickEvent(holder);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemStudentBinding itemStudentBinding;

        ViewHolder(ItemStudentBinding binding) {
            super(binding.getRoot());
            this.itemStudentBinding = binding;
        }
    }

    private void clickEvent(final ViewHolder holder) {
        holder.itemStudentBinding.rlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemStudentBinding.swipeLayout.isRightOpen()) {
                    holder.itemStudentBinding.swipeLayout.close(true);
                    //callback.onClick(holder.getAdapterPosition(), 2);
                }
            }
        });

        holder.itemStudentBinding.rlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.itemStudentBinding.swipeLayout.isRightOpen()) {
                    holder.itemStudentBinding.swipeLayout.close(true);
                    //callback.onClick(holder.getAdapterPosition(), 3);
                }
            }
        });
    }
}
