package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Adapter.AdapterOutfit;
import gogo.skyborn.com.gogo.Adapter.AdapterRoutine;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Models.GGCollectionOutfit;
import gogo.skyborn.com.gogo.Models.GGMenu;
import gogo.skyborn.com.gogo.Models.GGRoutine;
import gogo.skyborn.com.gogo.Utils.GGDragListener;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGOutfit extends GGBase implements View.OnClickListener, GGOnDownloadResponse {
    private AdapterOutfit mAdapterRoutine;
    private LinearLayout mLinearDrop;
    private FloatingActionButton mCheck;
    private ArrayList<gogo.skyborn.com.gogo.Models.GGOutfit> mList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_fragment_drop_things, container, false);
        mRecyclerThings = (RecyclerView) v.findViewById(R.id.img_recycler);
        mLinearDrop = (LinearLayout) v.findViewById(R.id.containerDrop);
        mCheck = (FloatingActionButton) v.findViewById(R.id.btnChecked);
        mLinearDrop.setOnDragListener(new GGDragListener());
        mCheck.setOnClickListener(this);
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
    public void onClick(View view) {
        if (view == mCheck) {
            GGSqlInfo ggSqlInfo = new GGSqlInfo(getContext());
            if (mList == null) {
                mList = new ArrayList<>();
            }
            if (mLinearDrop != null && mLinearDrop.getChildCount() > 0) {
                for (int i = 0; i < mLinearDrop.getChildCount(); i++) {
                    if (mLinearDrop.getChildAt(i) != null && mLinearDrop.getChildAt(i) instanceof CardView) {
                        mList.add((gogo.skyborn.com.gogo.Models.GGOutfit) mLinearDrop.getChildAt(i).getTag());
                    }
                }
            }
            if (mList != null) {
                for (int i = 0; i < mList.size(); i++) {
                    ggSqlInfo.addOutfit(mList.get(i));
                }
                if (onSelectedMenuItem != null) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("identificador", "SBHome");
                        jsonObject.put("titulo", "Portada");
                        jsonObject.put("url", "1");
                        jsonObject.put("tipoIcono", "0");
                        jsonObject.put("tipoContenido", "0");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    GGMenu menu = new GGMenu(jsonObject);
                    onSelectedMenuItem.onSelectedMenuItem(menu);
                }
            }
        }
    }

    @Override
    public void onDownloadResponse(Object object) {
        if (object instanceof GGCollectionOutfit) {
            if (mRecyclerThings != null) {
                mAdapterRoutine = new AdapterOutfit(((GGCollectionOutfit) object).getmList());
                mRecyclerThings.setAdapter(mAdapterRoutine);
                mRecyclerThings.setLayoutManager(new LinearLayoutManager(mRecyclerThings.getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }
    }
}