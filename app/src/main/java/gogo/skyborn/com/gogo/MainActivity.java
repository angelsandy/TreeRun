package gogo.skyborn.com.gogo;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.FacebookSdk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Adapter.AdapterMenu;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Enums.GGBoardType;
import gogo.skyborn.com.gogo.Fragment.GGBase;
import gogo.skyborn.com.gogo.Fragment.GGCountDown;
import gogo.skyborn.com.gogo.Fragment.GGHome;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.Models.GGMenu;


public class MainActivity extends AppCompatActivity implements GGOnDownloadListener,AdapterView.OnItemClickListener,GGOnSelectedMenuItem,GGOnChangeFragmentListener {
    private ListView mListMenu;
    private ArrayList<GGMenu> mMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gg_activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGogo);
        setSupportActionBar(toolbar);
        GGMenu.getMenuDownload(this);
        mListMenu = (ListView) findViewById(R.id.listViewMenu);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action, menu);
        return true;
    }

    @Override
    public void onDownloadSuccess(String o) {
        if (mMenuList == null) {
            mMenuList = new ArrayList<GGMenu>();
        }
        if (o != null) {
            try {
                JSONObject gogo = new JSONObject(o);
                JSONArray jsonArray = gogo.optJSONArray("menu");
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        GGMenu menu = new GGMenu(jsonArray.optJSONObject(i));
                        mMenuList.add(menu);
                        GGCollectionManager.setmCollection(menu.getmIdentifier(),menu);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdapterMenu adapterMenu = new AdapterMenu(mMenuList,this);
            mListMenu.setAdapter(adapterMenu);
            changeFragment(new GGHome(),"home");
        }
    }

    @Override
    public void changeFragment(GGBase fragment, String id){
        fragment.setOnSelectedMenuItem(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framContainer,fragment,id).addToBackStack(null).commit();
    }

    @Override
    public void onDownloadError() {
        Log.e("Error Menú", "Descarga del menú fallida");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if(view != null) {
            onSelectedMenuItem(view.getTag());
        }
    }

    @Override
    public void onSelectedMenuItem(Object menuItem) {
        final GGMenu menu = (GGMenu) menuItem;
        final MainActivity mainActivity = this;
        if(menu.getmBoardType() == GGBoardType.GGCountDown) {
            changeFragment(new GGCountDown(),menu.getmIdentifier());
        } else {
            GGCollectionManager.findCollectionWithUrl(((GGMenu)menuItem).getmIdentifier(),menu.getmUrl(), new GGOnDownloadResponse() {
                @Override
                public void onDownloadResponse(Object object) {
                    GGBase fragment = null;
                    switch (menu.getmBoardType()) {
                        case GGHome:
                            fragment = new GGHome();
                            break;
                        case GGRoutine:
                            break;
                        case GGList:
                            break;
                        case GGOutfit:
                            break;
                    }
                    fragment.setOnSelectedMenuItem(mainActivity);
                    changeFragment(fragment, menu.getmIdentifier());
                }
            });
        }
    }
}