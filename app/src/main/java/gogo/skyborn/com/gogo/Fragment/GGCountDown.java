package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fittree.skyborn.com.gogo.R;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGCountDown extends GGBase {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_countdown,container,false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
