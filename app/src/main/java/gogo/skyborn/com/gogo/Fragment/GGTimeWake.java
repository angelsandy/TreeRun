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
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;


public class GGTimeWake extends Fragment implements View.OnClickListener {
    private TimePicker mTimePicker;
    private ImageView mSet;
    private GGOnChangeFragmentListener mOnChange;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_fragment_timewake, container, false);
        mTimePicker = (TimePicker) v.findViewById(R.id.pickerHour);
        mSet = (ImageView) v.findViewById(R.id.btnContinue);
        mSet.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view == mSet) {
            GGSqlInfo ggSqlInfo = new GGSqlInfo(getContext());
            if (mTimePicker != null) {
                int hour, min;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    hour = mTimePicker.getHour();
                } else {
                    hour = mTimePicker.getCurrentHour();
                }
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    min = mTimePicker.getMinute();
                } else {
                    min = mTimePicker.getCurrentMinute();
                }
                ggSqlInfo.addTime(String.valueOf(hour) + ":" + String.valueOf(min));
                if(mOnChange != null) {
                    mOnChange.changeFragment(null,"routine");
                }
            }
        }
    }

    public void setmOnChange(GGOnChangeFragmentListener mOnChange) {
        this.mOnChange = mOnChange;
    }
}