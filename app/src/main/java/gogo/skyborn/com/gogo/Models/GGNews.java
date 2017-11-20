package gogo.skyborn.com.gogo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

import gogo.skyborn.com.gogo.Fragment.GGDetailNews;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Utils.GGDownload;

/**
 * Created by Sandy on 11/19/2017.
 */

public class GGNews implements Parcelable {
    private String mImage;
    private String mTitle;
    private String mId;
    private String mAutor;
    private String mFecha;
    private String mConent;
    private onNoticeListener mNoticeListener;
    private JSONObject mJsonRaw;

    public GGNews(JSONObject mJsonRaw){
        this.mJsonRaw = mJsonRaw;
        setPropierties();
    }

    protected GGNews(Parcel in) {
        mImage = in.readString();
        mTitle = in.readString();
        mId = in.readString();
        mAutor = in.readString();
        mFecha = in.readString();
        mConent = in.readString();
    }

    public static final Creator<GGNews> CREATOR = new Creator<GGNews>() {
        @Override
        public GGNews createFromParcel(Parcel in) {
            return new GGNews(in);
        }

        @Override
        public GGNews[] newArray(int size) {
            return new GGNews[size];
        }
    };

    private void setPropierties() {
        if(this.mJsonRaw != null) {
            mTitle = this.mJsonRaw.optString("titulo","");
            mId = this.mJsonRaw.optString("identificador","");
            mAutor = this.mJsonRaw.optString("autor","");
            mFecha = this.mJsonRaw.optString("fecha","");
            mImage = this.mJsonRaw.optString("urlImagen","");
            mConent = this.mJsonRaw.optString("resumen","");
        }
    }



    public String getmTitle() {
        return mTitle;
    }

    public String getmImage() {
        return mImage;
    }

    public String getmId() {
        return mId;
    }

    public String getmAutor() {
        return mAutor;
    }

    public String getmFecha() {
        return mFecha;
    }

    public String getmConent() {
        return mConent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mImage);
        parcel.writeString(mTitle);
        parcel.writeString(mId);
        parcel.writeString(mAutor);
        parcel.writeString(mFecha);
        parcel.writeString(mConent);
    }

    public interface onNoticeListener {
        void onNoticeSuccess(Object notice);
        void onNoticeFail();
    }
}
