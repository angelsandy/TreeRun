package gogo.skyborn.com.gogo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnPageComplete;
import gogo.skyborn.com.gogo.MainActivity;
import gogo.skyborn.com.gogo.Utils.GGPagerDisableSwap;
import gogo.skyborn.com.gogo.Utils.GGPagerSlider;

/**
 * Created by sandrapatriciacerdaguerra on 15/11/17.
 */

public class GGPagerFirstTime extends GGBase implements GGOnPageComplete, View.OnClickListener, GGOnChangeFragmentListener, ViewPager.OnPageChangeListener  {
    private GGPagerDisableSwap mViewPager;
    private PagerAdapter mPagerAdapter;
    private TextView mNext, mBefore;
    private int mCurrentPosition = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_pager_firsttime,container,false);
        mNext = (TextView) v.findViewById(R.id.txt_final);
        mBefore = (TextView) v.findViewById(R.id.txt_back);
        mBefore.setVisibility(View.GONE);
        mNext.setOnClickListener(this);
        mBefore.setOnClickListener(this);
        mViewPager = (GGPagerDisableSwap) v.findViewById(R.id.pager_firstTime);
        mPagerAdapter = new GGPagerSlider(getFragmentManager(), this);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        return v;
    }

    @Override
    public void pageComplete() {
    }

    @Override
    public void onClick(View view) {
        if(view == mNext) {
            if(mCurrentPosition == mViewPager.getAdapter().getCount() - 1) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }else {
                mViewPager.setCurrentItem(mCurrentPosition + 1);
            }
        }
        if(view == mBefore) {
            if(mCurrentPosition != 0) {
                mViewPager.setCurrentItem(mCurrentPosition - 1);
            }
        }
    }

    @Override
    public void changeFragment(GGBase fragment, String id) {
        if (mViewPager != null) {
            if (id.equals("register")) {
                mViewPager.setCurrentItem(3);
            } else if (id.equals("timeWake")) {
                mViewPager.setCurrentItem(1);
            } else if (id.equals("routine")) {
                mViewPager.setCurrentItem(2);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPosition = position;
        if(position > 0) {
            mBefore.setVisibility(View.VISIBLE);
        }else{
            mBefore.setVisibility(View.GONE);
        }
        if(mViewPager.getAdapter().getCount() == position + 1){
            mNext.setText("Finalizar");
        } else{
            mNext.setText("Siguiente");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
