package gogo.skyborn.com.gogo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Fragment.GGBase;
import gogo.skyborn.com.gogo.Fragment.GGLogin;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnPageComplete;
import gogo.skyborn.com.gogo.Utils.GGPagerSlider;

public class FirstTimeActivity extends AppCompatActivity implements GGOnChangeFragmentListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg_activity_firsttime);
        FacebookSdk.sdkInitialize(getApplicationContext());
        GGBase ggBase = new GGLogin();
        ((GGLogin) ggBase).setmOnChange(this);
        changeFragment(ggBase, "login");
    }

    @Override
    public void changeFragment(GGBase fragment, String id) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.container_fragment, fragment, id).addToBackStack(null).commit();
        }
    }
}