package gogo.skyborn.com.gogo.Utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by sandrapatriciacerdaguerra on 15/11/17.
 */

public class GGPagerDisableSwap extends ViewPager {

    public GGPagerDisableSwap(Context context) {
        super(context);
    }

    public GGPagerDisableSwap(Context context,AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
