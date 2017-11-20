package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import gogo.skyborn.com.gogo.Adapter.AdapterOutfit;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.Models.GGCollectionOutfit;
import gogo.skyborn.com.gogo.Models.GGMenu;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGBase extends Fragment {
    protected RecyclerView mRecyclerThings;
    protected GGOnSelectedMenuItem onSelectedMenuItem;
    protected GGMenu ggMenu;

    public void setOnSelectedMenuItem(GGOnSelectedMenuItem onSelectedMenuItem) {
        this.onSelectedMenuItem = onSelectedMenuItem;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                ggMenu = bundle.getParcelable("item");
            }
        }
    }
}