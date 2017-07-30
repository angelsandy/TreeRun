package treerun.skyborn.com.treerun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Sandy on 7/5/2017.
 */

public class Launcher extends Activity {
    private static long SPLASH_SCREEN_DELAY = 3000;
    private final static String TREE_RUN_PREFERENCE = "TreeRunData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.tr_launcher);
        SharedPreferences treeRunData = getSharedPreferences(TREE_RUN_PREFERENCE, Context.MODE_PRIVATE);
        if(treeRunData != null) {
            if(treeRunData.getBoolean("isFirstTime",true)){
                SPLASH_SCREEN_DELAY = 5000;
                treeRunData.edit().putBoolean("isFirstTime",false).commit();
            }
        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent mainIntent = new Intent().setClass(
                        Launcher.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
