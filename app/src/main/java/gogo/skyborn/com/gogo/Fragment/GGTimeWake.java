package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TimePicker;

import fittree.skyborn.com.gogo.R;


public class GGTimeWake extends Fragment implements View.OnClickListener {
    private TimePicker mTimePicker;
    private ImageView mSet;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_fragment_timewake, container, false);
        mTimePicker = (TimePicker) v.findViewById(R.id.pickerHour);
        mSet = (ImageView)v.findViewById(R.id.btnContinue);
        mSet.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {

    }
}