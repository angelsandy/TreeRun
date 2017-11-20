package gogo.skyborn.com.gogo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import gogo.skyborn.com.gogo.Enums.GGBackground;
import gogo.skyborn.com.gogo.Enums.GGIconTypeBackground;

/**
 * Created by Sandy on 11/17/2017.
 */

public class GGOutfit implements Parcelable {
    private String mId;
    private String mImage;
    private JSONObject mJsonRaw;

    public GGOutfit(JSONObject jsonObject){
        mJsonRaw = jsonObject;
        setProperties();
    }

    protected GGOutfit(Parcel in) {
        mId = in.readString();
        mImage = in.readString();
    }

    public static final Creator<GGOutfit> CREATOR = new Creator<GGOutfit>() {
        @Override
        public GGOutfit createFromParcel(Parcel in) {
            return new GGOutfit(in);
        }

        @Override
        public GGOutfit[] newArray(int size) {
            return new GGOutfit[size];
        }
    };

    private void setProperties() {
        if(mJsonRaw != null) {
            this.mId = mJsonRaw.optString("identificador","");
            this.mImage = mJsonRaw.optString("urlImagen","");
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mImage);
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }
}
