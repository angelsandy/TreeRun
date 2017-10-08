package gogo.skyborn.com.gogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import fittree.skyborn.com.gogo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg_activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}