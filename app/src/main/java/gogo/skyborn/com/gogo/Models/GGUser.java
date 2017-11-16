package gogo.skyborn.com.gogo.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Sandy on 11/12/2017.
 */

public class GGUser implements Parcelable {
    private String mName;
    private String mPhoto;
    private String mEmail;
    private String mPassword;

    public GGUser(){

    }

    protected GGUser(Parcel in) {
        mName = in.readString();
        mPhoto = in.readString();
        mEmail = in.readString();
        mPassword = in.readString();
    }

    public static final Creator<GGUser> CREATOR = new Creator<GGUser>() {
        @Override
        public GGUser createFromParcel(Parcel in) {
            return new GGUser(in);
        }

        @Override
        public GGUser[] newArray(int size) {
            return new GGUser[size];
        }
    };

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(String mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mPhoto);
        parcel.writeString(mEmail);
        parcel.writeString(mPassword);
    }
}
