package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Adapter.AdapterMenu;
import gogo.skyborn.com.gogo.Adapter.AdapterRoutine;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Models.GGCollectionRoutine;
import gogo.skyborn.com.gogo.Models.GGMenu;
import gogo.skyborn.com.gogo.Models.GGRoutine;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGRoutineMorning extends GGBase implements GGOnDownloadResponse {
    private RecyclerView mRoutineList;
    private EditText mNewItem;
    private AdapterRoutine mAdapter;
    private ArrayList<GGRoutine> mRoutine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GGCollectionManager.findCollectionWithUrl("SBRutina","http://192.168.0.82/Gogo/version1_1/rutina.php",this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_know_routine, container,false);
        mRoutineList = (RecyclerView)view.findViewById(R.id.recycler_routine);
        mNewItem = (EditText)view.findViewById(R.id.edit_search);
        return view;
    }

    @Override
    public void onDownloadResponse(Object object) {
        if(object != null) {
            if (object instanceof GGCollectionRoutine) {
                if(mRoutine == null) {
                    mRoutine = new ArrayList<>();
                }
                mRoutine = ((GGCollectionRoutine)object).getmList();
                mAdapter = new AdapterRoutine(mRoutine);
                if(mRoutineList != null) {
                    mRoutineList.setLayoutManager(new LinearLayoutManager(mRoutineList.getContext()));
                    mRoutineList.setAdapter(mAdapter);
                }
            }
        }
    }
}
