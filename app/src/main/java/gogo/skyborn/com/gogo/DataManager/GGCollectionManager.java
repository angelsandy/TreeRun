package gogo.skyborn.com.gogo.DataManager;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Models.GGBoard;
import gogo.skyborn.com.gogo.Models.GGMenu;

/**
 * Created by Sandy on 10/15/2017.
 */

public class GGCollectionManager {
    private static HashMap<String, Object> mCollection = new HashMap<>();

    public static void findMenuWithId(String id, GGOnDownloadResponse ggOnDownloadResponse){
        GGMenu menuItem = null;
        if(mCollection.containsKey(id)) {
            menuItem = (GGMenu) mCollection.get(id);
            if(ggOnDownloadResponse != null) {
                ggOnDownloadResponse.onDownloadResponse(menuItem);
            }
        }
    }

    public static void setmCollection(String id, Object object) {
        mCollection.put(id,object);
    }

    public static void findCollectionWithUrl(String url, final GGOnDownloadResponse ggOnDownloadListener) {
        GGBoard ggBoard = null;
        if(mCollection.containsKey(url)) {
            ggBoard = (GGBoard) mCollection.get(url);
        } else {
            ggBoard = new GGBoard(url);
            mCollection.put(url,ggBoard);
        }
        ggBoard.updateBoard(new GGBoard.updateBoardListener() {
            @Override
            public void onBooardSuccess(Object board) {
                if(ggOnDownloadListener != null) {
                    ggOnDownloadListener.onDownloadResponse(board);
                }
            }

            @Override
            public void onBoardFail() {
                Log.e("Error Board","Fallo al descargar el board");
            }
        });
    }

    public static void findArticleWithId() {

    }
}
