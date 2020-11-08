package com.room303.dutaxi.ui.main.tripsfragment.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.room303.dutaxi.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "RecyclerAdapter debug";

    private LayoutInflater layoutInflater;
    private ArrayList<RequestItem> requestItems;
    private int currentMinute;
    private int currentHour;

    public RecyclerAdapter(Context context, ArrayList<RequestItem> requestItems) {
        this.requestItems = requestItems;
        this.layoutInflater = LayoutInflater.from(context);
        Date date = new Date();
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
        currentMinute = Integer.parseInt(minuteFormat.format(date));
        currentHour = Integer.parseInt(hourFormat.format(date));
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
        setTimeRemainState(holder, currentRequestItem);
        holder.freeSeatsTextView.setText(currentRequestItem.getSeats());
    }

    private void setTimeRemainState(ViewHolder holder, RequestItem currentRequestItem) {
        String timeString = currentRequestItem.getTime();
        int tripHour = Integer.parseInt(timeString.substring(0, timeString.indexOf(':')));
        int tripMinute = Integer.parseInt(timeString.substring(timeString.indexOf(':') + 1));
        Log.d(TAG, "setTimeRemainState: " + currentHour + ":" + currentMinute + " // " + tripHour + ":" + tripMinute);
        if (tripHour == currentHour && tripMinute > currentMinute) {
            holder.timeRemainTextView.setText((tripMinute - currentMinute) + "");
        } else if (tripHour == currentHour + 1 && (60 - currentMinute + tripMinute) < 60) {
            holder.timeRemainTextView.setText((60 - currentMinute + tripMinute) + "");
        } else {
            holder.timeRemainLayout.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return requestItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView departureTextView;
        TextView destinationTextView;
        TextView timeTextView;
        LinearLayout timeRemainLayout;
        TextView timeRemainTextView;
        CircleImageView memberHostImage;
        CircleImageView member1Image;
        CircleImageView member2Image;
        CircleImageView member3Image;
        TextView freeSeatsTextView;

        ViewHolder(View view) {
            super(view);
            departureTextView = view.findViewById(R.id.trip_item_departure);
            destinationTextView = view.findViewById(R.id.trip_item_destination);
            timeTextView = view.findViewById(R.id.trip_item_time);
            timeRemainLayout = view.findViewById(R.id.trip_item_time_remain_layout);
            timeRemainTextView = view.findViewById(R.id.trip_item_time_remain);
            memberHostImage = view.findViewById(R.id.trip_item_member_host);
            member1Image = view.findViewById(R.id.trip_item_member_1);
            member2Image = view.findViewById(R.id.trip_item_member_2);
            member3Image = view.findViewById(R.id.trip_item_member_3);
            freeSeatsTextView = view.findViewById(R.id.trip_item_seats_counter);
        }
    }
}