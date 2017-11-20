package gogo.skyborn.com.gogo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Holder.GGHolderArticle;
import gogo.skyborn.com.gogo.Holder.GGRoutineHolder;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.MainActivity;

/**
 * Created by Sandy on 11/19/2017.
 */

public class AdapterNews extends BaseAdapterRecycler {
    private GGOnSelectedMenuItem onSelectedMenuItem;

    public AdapterNews(ArrayList<Object> mRoutineList) {
        super(mRoutineList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GGHolderArticle(LayoutInflater.from(parent.getContext()).inflate(R.layout.gg_item_object_articles,parent,false),onSelectedMenuItem);
    }

    public void setOnSelectedMenuItem(GGOnSelectedMenuItem onSelectedMenuItem) {
        this.onSelectedMenuItem = onSelectedMenuItem;
    }
}
