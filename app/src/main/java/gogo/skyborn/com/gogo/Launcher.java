package gogo.skyborn.com.gogo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;

public class Launcher extends Activity {
    private static long SPLASH_SCREEN_DELAY = 3000;
    private boolean mIsFirstTime;
    private final static String GO_GO_PREFERENCE = "GOGOData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.gg_launcher);
        SharedPreferences gogoData = getSharedPreferences(GO_GO_PREFERENCE, Context.MODE_PRIVATE);
        if (gogoData != null) {
            mIsFirstTime = gogoData.getBoolean("isFirstTime", true);
            if (mIsFirstTime) {
                SPLASH_SCREEN_DELAY = 1000;
                gogoData.edit().putBoolean("isFirstTime", false).commit();
            }
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = null;
               /* if (mIsFirstTime) {
                    mainIntent = new Intent().setClass(Launcher.this, FirstTimeActivity.class);
                } else {*/
                 mainIntent = new Intent().setClass(Launcher.this, FirstTimeActivity.class);
                   // mainIntent = new Intent().setClass(Launcher.this, MainActivity.class);
               // }
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}