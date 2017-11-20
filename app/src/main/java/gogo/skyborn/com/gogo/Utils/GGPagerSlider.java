package gogo.skyborn.com.gogo.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import gogo.skyborn.com.gogo.Fragment.GGLogin;
import gogo.skyborn.com.gogo.Fragment.GGNoDisponible;
import gogo.skyborn.com.gogo.Fragment.GGRegister;
import gogo.skyborn.com.gogo.Fragment.GGRoutineMorning;
import gogo.skyborn.com.gogo.Fragment.GGTimeWake;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;

/**
 * Created by Sandy on 11/5/2017.
 */

public class GGPagerSlider extends FragmentStatePagerAdapter {
    private GGOnChangeFragmentListener mOnChangeFragmentListener;

    public GGPagerSlider(FragmentManager fm, GGOnChangeFragmentListener mOnChangeFragmentListener) {
        super(fm);
        this.mOnChangeFragmentListener = mOnChangeFragmentListener;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new GGRegister();
                ((GGRegister) fragment).setmOnChangePage(mOnChangeFragmentListener);
                break;

            case 1:
                fragment = new GGTimeWake();
                ((GGTimeWake) fragment).setmOnChange(mOnChangeFragmentListener);
                break;

            case 2:
                fragment = new GGRoutineMorning();
                break;
            default:
                fragment = new GGNoDisponible();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}