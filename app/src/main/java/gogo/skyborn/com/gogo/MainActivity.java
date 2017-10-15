package gogo.skyborn.com.gogo;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import fittree.skyborn.com.gogo.R;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGogo);
        setSupportActionBar(toolbar);
        mRecycler = (RecyclerView)findViewById(R.id.recyclerMenu);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView  navigationView = (NavigationView) findViewById(R.id.navigationView);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }
}