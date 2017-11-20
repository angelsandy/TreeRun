package gogo.skyborn.com.gogo.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.TwoStatePreference;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGCountDown extends GGBase {
    private TextView mTxtCountDown, mTxtRoutine;
    private ImageView mDone,mArrow;
    private String time;
    private int i = 0;
    private CountDownTimer timerFirst;
    private ArrayList<String> arrayList;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_countdown, container, false);
        mTxtCountDown = (TextView) view.findViewById(R.id.txtCountDown);
        mTxtRoutine = (TextView) view.findViewById(R.id.txtRoutine);
        mDone = (ImageView) view.findViewById(R.id.btnContinue);
        mArrow = (ImageView)view.findViewById(R.id.imgArrow);
        setTime();
        mDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerFirst != null) {
                    timerFirst.onFinish();
                }
            }
        });
        return view;
    }

    private void setTime() {
        GGSqlInfo ggSqlInfo = new GGSqlInfo(getContext());
        arrayList = ggSqlInfo.findRoutine();
        if (mTxtRoutine != null) {
            mTxtRoutine.setText(arrayList.get(i));
        }
        timerFirst = new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                Date date = new Date(millisUntilFinished);
                SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
                String myTime = formatter.format(date);
                mTxtCountDown.setText(myTime);
            }

            public void onFinish() {
                i = i + 1;
                timerFirst.cancel();
                mTxtCountDown.setText("00:00");
                if (mTxtRoutine != null && arrayList.size() > i) {
                    mTxtRoutine.setText(arrayList.get(i));
                    timerFirst.start();
                }else{
                    mDone.setVisibility(View.GONE);
                    mArrow.setVisibility(View.GONE);
                }
            }
        }.start();
    }
}