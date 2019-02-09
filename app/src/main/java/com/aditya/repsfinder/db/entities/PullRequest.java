package com.aditya.repsfinder.db.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class PullRequest implements Parcelable, Comparable<PullRequest> {

    @ColumnInfo(name = "url")
    private String url;
    @ColumnInfo(name = "html_url")
    private String html_url;
    @ColumnInfo(name = "diff_url")
    private String diff_url;
    @ColumnInfo(name = "patch_url")
    private String patch_url;

    public PullRequest(){

    }

    protected PullRequest(Parcel in) {
        url = in.readString();
        html_url = in.readString();
        diff_url = in.readString();
        patch_url = in.readString();
    }

    public static final Creator<PullRequest> CREATOR = new Creator<PullRequest>() {
        @Override
        public PullRequest createFromParcel(Parcel in) {
            return new PullRequest(in);
        }

        @Override
        public PullRequest[] newArray(int size) {
            return new PullRequest[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public String getPatch_url() {
        return patch_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(html_url);
        dest.writeString(diff_url);
        dest.writeString(patch_url);
    }

    @Override
    public int compareTo(PullRequest o) {
        return 0;
    }
}
