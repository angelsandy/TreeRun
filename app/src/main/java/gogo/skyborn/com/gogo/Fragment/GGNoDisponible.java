package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fittree.skyborn.com.gogo.R;

/**
 * Created by Sandy on 11/12/2017.
 */

public class GGNoDisponible extends GGBase {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_fragment_timewake, container, false);
        return v;
    }
}
