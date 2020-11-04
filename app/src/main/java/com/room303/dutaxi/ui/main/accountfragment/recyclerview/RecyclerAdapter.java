package com.room303.dutaxi.ui.main.accountfragment.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.room303.dutaxi.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<HistoryItem> HistoryItems;

    public RecyclerAdapter(Context context, ArrayList<HistoryItem> HistoryItems) {
        this.HistoryItems = HistoryItems;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.bottom_sheet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem currentHistoryItem = HistoryItems.get(position);
        holder.departureTextView.setText(currentHistoryItem.getDeparture());
        holder.destinationTextView.setText(currentHistoryItem.getDestination());
        holder.dateTextView.setText(currentHistoryItem.getDate());
        String[] names = currentHistoryItem.getGroup();
        holder.groupTextView.setText(names[0] + " " + names[1] + " " + names[2]);
    }

    @Override
    public int getItemCount() {
        return HistoryItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView departureTextView;
        TextView destinationTextView;
        TextView dateTextView;
        TextView groupTextView;

        ViewHolder(View view) {
            super(view);
            departureTextView = view.findViewById(R.id.history_item_departure);
            destinationTextView = view.findViewById(R.id.history_item_destination);
            dateTextView = view.findViewById(R.id.history_item_date);
            groupTextView = view.findViewById(R.id.history_item_group);
        }
    }
}