package gogo.skyborn.com.gogo.Models;


import org.json.JSONException;
import org.json.JSONObject;

import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Utils.GGDownload;

public class GGBoard {
    private String mTitle,mResume, mUrlThumnail,mDate,mAutor,mId,mUrl;
    private JSONObject jsonRaw;
    private boolean valid;
    private updateBoardListener updateBoardListener;

   public GGBoard (JSONObject jsonObject) {
        jsonRaw = jsonObject;
        setProperties();
    }

    public GGBoard(String mUrl) {
        this.mUrl = mUrl;
    }

    private void setProperties() {
        if(jsonRaw != null) {
           mId =  jsonRaw.optString("identificador","");
           mTitle = jsonRaw.optString("titulo","");
           mResume = jsonRaw.optString("resumen","");
           mUrlThumnail = jsonRaw.optString("urlImagen","");
           mDate = jsonRaw.optString("fecha","");
           mAutor = jsonRaw.optString("autor","");
           setValid();
        }
    }

    public void setValid(){

    }

    public void updateBoard(final updateBoardListener updateBoardListener) {
        if(valid) {
            if(updateBoardListener != null) {
                updateBoardListener.onBooardSuccess(this);
            }
        } else {
            GGDownload ggDownload = new GGDownload(new GGOnDownloadListener() {
                @Override
                public void onDownloadSuccess(String o) {
                    try {
                        jsonRaw = new JSONObject(o);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    setProperties();
                    if(updateBoardListener != null) {
                        updateBoardListener.onBooardSuccess(this);
                    }
                }

                @Override
                public void onDownloadError() {
                    updateBoardListener.onBoardFail();
                }
            },mUrl);
            ggDownload.execute();
        }
    }

    public interface updateBoardListener {
        void onBooardSuccess(Object board);
        void onBoardFail();
    }
}