package gogo.skyborn.com.gogo.Fragment;

import android.support.v4.app.Fragment;

import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGBase extends Fragment {
    protected GGOnSelectedMenuItem onSelectedMenuItem;

    public void setOnSelectedMenuItem(GGOnSelectedMenuItem onSelectedMenuItem) {
        this.onSelectedMenuItem = onSelectedMenuItem;
    }
}