package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Adapter.AdapterNews;
import gogo.skyborn.com.gogo.Adapter.AdapterOutfit;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.Models.*;
import gogo.skyborn.com.gogo.Utils.GGDragListener;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;

/**
 * Created by Sandy on 11/19/2017.
 */

public class GGNews extends GGBase implements GGOnDownloadResponse{
    private AdapterNews mAdapterRoutine;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_fragment_articles, container, false);
        mRecyclerThings = (RecyclerView) v.findViewById(R.id.recyclerNews);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ggMenu != null) {
            GGCollectionManager.findCollectionWithUrl(ggMenu.getmIdentifier(), ggMenu.getmUrl(), this);
        }
    }

    @Override
    public void onDownloadResponse(Object object) {
        if (object instanceof GGCollectionNews) {
            if (mRecyclerThings != null) {
                mAdapterRoutine = new AdapterNews(((GGCollectionNews) object).getmList());
                mAdapterRoutine.setOnSelectedMenuItem(onSelectedMenuItem);
                mRecyclerThings.setAdapter(mAdapterRoutine);
                mRecyclerThings.setLayoutManager(new LinearLayoutManager(mRecyclerThings.getContext(), LinearLayoutManager.VERTICAL, false));
            }
        }
    }
}