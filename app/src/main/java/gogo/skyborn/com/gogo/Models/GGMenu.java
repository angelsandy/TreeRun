package gogo.skyborn.com.gogo.Models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import gogo.skyborn.com.gogo.Enums.GGBoardType;
import gogo.skyborn.com.gogo.Enums.GGIconType;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Utils.GGDownload;

public class GGMenu implements Parcelable{
    public static String URL = "http://192.168.0.82/Gogo/version1_1/menuGogo.php";
    private String mTitle, mIdentifier, mUrl;
    private GGIconType mIcon;
    private GGBoardType mBoardType;
    private GGOnDownloadListener mOnDownloadListener;
    private JSONObject mJsonRaw;

    public GGMenu(JSONObject mJsonRaw) {
        this.mJsonRaw = mJsonRaw;
        setPropiertiesJson();
    }

    protected GGMenu(Parcel in) {
        mTitle = in.readString();
        mIdentifier = in.readString();
        mUrl = in.readString();
    }

    public static final Creator<GGMenu> CREATOR = new Creator<GGMenu>() {
        @Override
        public GGMenu createFromParcel(Parcel in) {
            return new GGMenu(in);
        }

        @Override
        public GGMenu[] newArray(int size) {
            return new GGMenu[size];
        }
    };

    private void setPropiertiesJson() {
        mTitle = mJsonRaw.optString("titulo", "");
        mIdentifier = mJsonRaw.optString("identificador", "");
        mUrl = mJsonRaw.optString("url", "");
        mIcon = GGIconType.values()[mJsonRaw.optInt("tipoIcono", -1)];
        mBoardType = GGBoardType.values()[mJsonRaw.optInt("tipoContenido", -1)];
    }

    public static void getMenuDownload(GGOnDownloadListener mOnDownloadListener) {
        GGDownload ggDownload = new GGDownload(mOnDownloadListener);
        ggDownload.execute(URL);
    }

    public GGBoardType getmBoardType() {
        return mBoardType;
    }

    private void setmBoardType(int mBoardType) {
        switch (mBoardType) {
            case 3:
                this.mBoardType = GGBoardType.GGOutfit;
                break;
            default:
            case 0:
                this.mBoardType = GGBoardType.GGList;
                break;
        }
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmIdentifier() {
        return mIdentifier;
    }

    public String getmUrl() {
        return mUrl;
    }

    public GGIconType getmIcon() {
        return mIcon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mIdentifier);
        parcel.writeString(mUrl);
    }
}