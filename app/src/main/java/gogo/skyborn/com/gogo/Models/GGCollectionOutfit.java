package gogo.skyborn.com.gogo.Models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Utils.GGDownload;

/**
 * Created by Sandy on 11/15/2017.
 */

public class GGCollectionOutfit implements GGOnDownloadListener {
    private ArrayList<Object>mList;
    private boolean valid;
    private String mUrl;
    private GGBoard.updateBoardListener updateBoardListener;

    public GGCollectionOutfit(String url){
        this.mUrl = url;
    }

    public void updateBoard(final GGBoard.updateBoardListener updateBoardListener) {
        this.updateBoardListener = updateBoardListener;
        if(valid) {
            if(updateBoardListener != null) {
                updateBoardListener.onBooardSuccess(this);
            }
        } else {
            GGDownload ggDownload = new GGDownload(this);
            ggDownload.execute(mUrl);
        }
    }

    public ArrayList<Object> getmList() {
        return mList;
    }

    @Override
    public void onDownloadSuccess(String o) {
        if(o != null) {
            JSONArray mJsonObject = null;
            try {
                mJsonObject = new JSONArray(o);
                if (mList == null) {
                    mList = new ArrayList<>();
                }
                if (mJsonObject != null) {
                    for (int i = 0; i < mJsonObject.length(); i++) {
                        try {
                            mList.add(new GGOutfit(mJsonObject.getJSONObject(i)));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (this.updateBoardListener != null) {
                this.updateBoardListener.onBooardSuccess(this);
            }
        }else{
            if (this.updateBoardListener != null) {
                this.updateBoardListener.onBoardFail();
            }
        }
     }

    @Override
    public void onDownloadError() {
        if(this.updateBoardListener != null){
            this.updateBoardListener.onBoardFail();
        }
    }
}