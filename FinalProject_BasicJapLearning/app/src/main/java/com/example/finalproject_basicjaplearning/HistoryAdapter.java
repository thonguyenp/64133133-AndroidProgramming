package com.example.finalproject_basicjaplearning;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final List<Map.Entry<String, KanaStats>> historyList;

    public HistoryAdapter(Map<String, KanaStats> kanaStatsMap) {
        this.historyList = new ArrayList<>(kanaStatsMap.entrySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map.Entry<String, KanaStats> entry = historyList.get(position);
        holder.tvKanaChar.setText(entry.getKey());
        holder.tvCorrectRate.setText(String.format("%.2f%%", entry.getValue().getCorrectRate()));
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvKanaChar, tvCorrectRate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKanaChar = itemView.findViewById(R.id.tvKanaChar);
            tvCorrectRate = itemView.findViewById(R.id.tvCorrectRate);
        }
    }
}
