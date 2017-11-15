package gogo.skyborn.com.gogo.Models;

import org.json.JSONException;
import org.json.JSONObject;

import gogo.skyborn.com.gogo.Enums.GGBackground;
import gogo.skyborn.com.gogo.Enums.GGIconType;
import gogo.skyborn.com.gogo.Enums.GGIconTypeBackground;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Utils.GGDownload;

/**
 * Created by Sandy on 11/13/2017.
 */

public class GGRoutine {
    private String mRoutineName,mUrl,mId;
    private GGIconTypeBackground mIconType;
    private GGBackground mBackground;
    private JSONObject mJsonObject;
    private GGBoard.updateBoardListener updateBoardListener;

    public GGRoutine(JSONObject jsonObject){
        mJsonObject = jsonObject;
        setProperties();
    }

    public String getmRoutineName() {
        return mRoutineName;
    }

    private void setProperties() {
        if(mJsonObject != null) {
            this.mRoutineName = mJsonObject.optString("titulo","");
            this.mId = mJsonObject.optString("identificador","");
            this.mIconType = GGIconTypeBackground.values()[mJsonObject.optInt("icon")];
            this.mBackground = GGBackground.values()[mJsonObject.optInt("image")];
        }
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public GGIconTypeBackground getmIconType() {
        return mIconType;
    }

    public GGBackground getmBackground() {
        return mBackground;
    }
}
