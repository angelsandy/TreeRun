package gogo.skyborn.com.gogo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Holder.GGRoutineHolder;
import gogo.skyborn.com.gogo.Models.GGRoutine;

public class AdapterRoutine extends BaseAdapterRecycler  {

    public AdapterRoutine(ArrayList<Object> mRoutineList) {
        super(mRoutineList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GGRoutineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gg_item_routine,parent,false));
    }

}