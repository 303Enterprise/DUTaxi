package com.room303.dutaxi.ui.main.tripsfragment.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.room303.dutaxi.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<RequestItem> requestItems;

    RecyclerAdapter(Context context, ArrayList<RequestItem> requestItems) {
        this.requestItems = requestItems;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = layoutInflater.inflate(R.layout.trip_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RequestItem currentRequestItem = requestItems.get(position);
        holder.departureTextView.setText(currentRequestItem.getDeparture());
        holder.destinationTextView.setText(currentRequestItem.getDestination());
        holder.timeTextView.setText(currentRequestItem.getTime());
        holder.freeSeatsTextView.setText(currentRequestItem.getSeats());
    }

    @Override
    public int getItemCount() {
        return requestItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView departureTextView;
        TextView destinationTextView;
        TextView timeTextView;
        TextView freeSeatsTextView;

        ViewHolder(View view) {
            super(view);
            departureTextView = view.findViewById(R.id.trip_item_departure);
            destinationTextView = view.findViewById(R.id.trip_item_destination);
            timeTextView = view.findViewById(R.id.trip_item_time);
            freeSeatsTextView = view.findViewById(R.id.trip_item_seats_counter);
        }
    }
}