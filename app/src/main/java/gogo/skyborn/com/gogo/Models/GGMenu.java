package gogo.skyborn.com.gogo.Models;

import org.json.JSONObject;

import gogo.skyborn.com.gogo.Enums.GGBoardType;
import gogo.skyborn.com.gogo.Enums.GGIconType;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Utils.GGDownload;

public class GGMenu {
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

    private void setPropiertiesJson() {
        mTitle = mJsonRaw.optString("titulo", "");
        mIdentifier = mJsonRaw.optString("identifier", "");
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
}