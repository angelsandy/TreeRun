package gogo.skyborn.com.gogo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Fragment.GGBase;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnPageComplete;
import gogo.skyborn.com.gogo.Utils.GGPagerSlider;

public class FirstTimeActivity extends AppCompatActivity  implements GGOnPageComplete,View.OnClickListener,GGOnChangeFragmentListener,ViewPager.OnPageChangeListener{
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private TextView mNext, mBefore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg_pager_firsttime);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mNext = (TextView) findViewById(R.id.txt_final);
        mBefore = (TextView) findViewById(R.id.txt_back);
        mNext.setOnClickListener(this);
        mBefore.setOnClickListener(this);
        mViewPager = (ViewPager)findViewById(R.id.pager_firstTime);
        mPagerAdapter = new GGPagerSlider(getSupportFragmentManager(),this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void pageComplete() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void changeFragment(GGBase fragment, String id) {
        if(mViewPager != null) {
            if (id.equals("register")) {
                mViewPager.setCurrentItem(3);
            } else if (id.equals("timeWake")) {
                mViewPager.setCurrentItem(1);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}