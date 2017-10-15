package gogo.skyborn.com.gogo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import fittree.skyborn.com.gogo.R;


public class FirstTimeActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg_pager_firsttime);
        mViewPager = (ViewPager)findViewById(R.id.pager_firstTime);

    }

}
