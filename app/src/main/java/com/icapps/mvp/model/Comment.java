package com.icapps.mvp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maartenvangiel on 20/09/16.
 */

public class Comment implements Parcelable {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.postId);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.body);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.postId = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.email = in.readString();
        this.body = in.readString();
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
