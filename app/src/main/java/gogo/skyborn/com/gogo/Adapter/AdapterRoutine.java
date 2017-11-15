package gogo.skyborn.com.gogo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Holder.GGRoutineHolder;
import gogo.skyborn.com.gogo.Models.GGRoutine;

/**
 * Created by Sandy on 11/13/2017.
 */

public class AdapterRoutine extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<GGRoutine> mRoutineList;

    public AdapterRoutine(ArrayList<GGRoutine> mRoutineList) {
        this.mRoutineList = mRoutineList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GGRoutineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gg_item_routine,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof GGRoutineHolder) {
            ((GGRoutineHolder)holder).setInfo(mRoutineList.get(position));
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