package gogo.skyborn.com.gogo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

import gogo.skyborn.com.gogo.Holder.GGRoutineHolder;
import gogo.skyborn.com.gogo.Holder.GGStandardHolder;
import gogo.skyborn.com.gogo.Models.GGRoutine;

/**
 * Created by Sandy on 11/17/2017.
 */

public class BaseAdapterRecycler extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Object> mRoutineList;

    public BaseAdapterRecycler(ArrayList<Object> mRoutineList){
        this.mRoutineList = mRoutineList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof GGStandardHolder) {
            ((GGStandardHolder)holder).setInfo(mRoutineList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (mRoutineList != null) {
            return mRoutineList.size();
        }
        return 0;
    }
}