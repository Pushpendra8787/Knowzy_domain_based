package com.example.knowzy_domain_based.ui.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knowzy_domain_based.R;
import com.example.knowzy_domain_based.ui.home.model.RecentCase;

import java.util.List;

public class RecentAdapter extends RecyclerView.Adapter<RecentAdapter.ViewHolder> {

    private final List<RecentCase> caseList;

    public RecentAdapter(List<RecentCase> caseList) {
        this.caseList = caseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent_case, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RecentCase item = caseList.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvStatus.setText(item.getStatus());
        holder.tvTime.setText(item.getTime());

        // 🔥 Optional click (future: open result screen)
        holder.itemView.setOnClickListener(v -> {
            // TODO: Open Result Screen
        });
    }

    @Override
    public int getItemCount() {
        return caseList != null ? caseList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvStatus, tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }
}