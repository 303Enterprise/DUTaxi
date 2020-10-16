package com.room303.dutaxi.requestitem;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestItem implements Parcelable {
    private boolean isHost = false;
    private String phoneNumber = "-1";
    private String vkRef = "-1";
    private String departure = "-1";
    private String destination = "-1";
    private String date = "-1";
    private int freeSeats = -1;

    public RequestItem() {
    }

    protected RequestItem(Parcel in) {
        isHost = in.readByte() != 0;
        phoneNumber = in.readString();
        vkRef = in.readString();
        departure = in.readString();
        destination = in.readString();
        date = in.readString();
        freeSeats = in.readInt();
    }

    public static final Creator<RequestItem> CREATOR = new Creator<RequestItem>() {
        @Override
        public RequestItem createFromParcel(Parcel in) {
            return new RequestItem(in);
        }

        @Override
        public RequestItem[] newArray(int size) {
            return new RequestItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this);
    }

    public boolean isHost() {
        return isHost;
    }

    public void setHost(boolean host) {
        isHost = host;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVkRef() {
        return vkRef;
    }

    public void setVkRef(String vkRef) {
        this.vkRef = vkRef;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
