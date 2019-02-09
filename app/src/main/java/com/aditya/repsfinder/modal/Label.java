package com.aditya.repsfinder.modal;

import android.os.Parcel;
import android.os.Parcelable;

public class Label implements Parcelable, Comparable<Label> {

    private Long id;
    private String node_id;
    private String url;
    private String name;
    private String color;
    private boolean isdefault;

    protected Label(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        node_id = in.readString();
        url = in.readString();
        name = in.readString();
        color = in.readString();
        isdefault = in.readByte() != 0;
    }

    public static final Creator<Label> CREATOR = new Creator<Label>() {
        @Override
        public Label createFromParcel(Parcel in) {
            return new Label(in);
        }

        @Override
        public Label[] newArray(int size) {
            return new Label[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(node_id);
        dest.writeString(url);
        dest.writeString(name);
        dest.writeString(color);
        dest.writeByte((byte) (isdefault ? 1 : 0));
    }

    @Override
    public int compareTo(Label o) {
        return this.id.compareTo(o.id);
    }
}
