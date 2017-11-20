package gogo.skyborn.com.gogo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Holder.GGOutfitHolder;
import gogo.skyborn.com.gogo.Models.GGOutfit;
import gogo.skyborn.com.gogo.Models.GGRoutine;

/**
 * Created by Sandy on 11/17/2017.
 */

public class AdapterOutfit extends BaseAdapterRecycler {

    public AdapterOutfit(ArrayList<Object> mRoutineList) {
        super(mRoutineList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GGOutfitHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gg_item_object_purse,parent,false));
    }
}
