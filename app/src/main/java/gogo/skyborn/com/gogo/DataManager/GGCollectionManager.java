package gogo.skyborn.com.gogo.DataManager;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Models.GGBoard;
import gogo.skyborn.com.gogo.Models.GGCollectionNews;
import gogo.skyborn.com.gogo.Models.GGCollectionOutfit;
import gogo.skyborn.com.gogo.Models.GGCollectionRoutine;
import gogo.skyborn.com.gogo.Models.GGMenu;
import gogo.skyborn.com.gogo.Models.GGNews;
import gogo.skyborn.com.gogo.Models.GGRoutine;

/**
 * Created by Sandy on 10/15/2017.
 */

public class GGCollectionManager {
    private static HashMap<String, Object> mCollection = new HashMap<>();

    public static void setmCollection(String id, Object object) {
        mCollection.put(id,object);
    }

    public static void findCollectionWithUrl(String id,String url, final GGOnDownloadResponse ggOnDownloadListener) {
        switch (id) {
            case "SBOutfits":
                GGCollectionOutfit ggCollectionOutfit = null;
                if(mCollection.containsKey(url)) {
                    ggCollectionOutfit = (GGCollectionOutfit) mCollection.get(url);
                } else {
                    ggCollectionOutfit = new GGCollectionOutfit(url);
                    mCollection.put(id,ggCollectionOutfit);
                }
                ggCollectionOutfit.updateBoard(new GGBoard.updateBoardListener() {
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
                break;
            case "SBConsejos":
                GGCollectionNews ggCollectionNews = null;
                if(mCollection.containsKey(url)) {
                    ggCollectionNews = (GGCollectionNews) mCollection.get(url);
                } else {
                    ggCollectionNews = new GGCollectionNews(url);
                    mCollection.put(id,ggCollectionNews);
                }
                ggCollectionNews.updateBoard(new GGNews.onNoticeListener() {
                    @Override
                    public void onNoticeSuccess(Object notice) {
                        if(ggOnDownloadListener != null) {
                            ggOnDownloadListener.onDownloadResponse(notice);
                        }
                    }
                    @Override
                    public void onNoticeFail() {
                        Log.e("Error Noticia","Fallo al descargar el board");
                    }
                });
                break;
            case "SBRutina":
                GGCollectionRoutine ggRoutine = null;
                if(mCollection.containsKey(url)) {
                    ggRoutine = (GGCollectionRoutine) mCollection.get(url);
                } else {
                    ggRoutine = new GGCollectionRoutine(url);
                    mCollection.put(id,ggRoutine);
                }
                ggRoutine.updateBoard(new GGBoard.updateBoardListener() {
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
                break;
            default:
                GGBoard ggBoard = null;
                if(mCollection.containsKey(url)) {
                    ggBoard = (GGBoard) mCollection.get(url);
                } else {
                    ggBoard = new GGBoard(url);
                    mCollection.put(id,ggBoard);
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
                break;
        }
    }

    public static void findArticleWithId() {

    }
}
