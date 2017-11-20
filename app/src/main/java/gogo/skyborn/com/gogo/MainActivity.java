package gogo.skyborn.com.gogo;

import android.app.Notification;
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
import android.widget.TextView;

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
import gogo.skyborn.com.gogo.Fragment.GGDetailNews;
import gogo.skyborn.com.gogo.Fragment.GGHello;
import gogo.skyborn.com.gogo.Fragment.GGHome;
import gogo.skyborn.com.gogo.Fragment.GGNews;
import gogo.skyborn.com.gogo.Fragment.GGOutfit;
import gogo.skyborn.com.gogo.Interfaces.GGOnChangeFragmentListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadListener;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.Models.GGMenu;
import gogo.skyborn.com.gogo.Models.GGUser;


public class MainActivity extends AppCompatActivity implements GGOnDownloadListener, GGOnSelectedMenuItem, GGOnChangeFragmentListener {
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
        TextView username = (TextView) findViewById(R.id.username);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        GGUser user;
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                user = bundle.getParcelable("user");
                if (username != null) {
                    username.setText(user.getmName());
                }
            }
        }
        getSupportActionBar().hide();
        changeFragment(new GGHome(), "home");
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
                        GGCollectionManager.setmCollection(menu.getmIdentifier(), menu);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            AdapterMenu adapterMenu = new AdapterMenu(mMenuList, this, this);
            mListMenu.setAdapter(adapterMenu);
        }
    }

    @Override
    public void changeFragment(GGBase fragment, String id) {
        fragment.setOnSelectedMenuItem(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framContainer, fragment, id).addToBackStack(id).commit();
    }

    @Override
    public void onDownloadError() {
        Log.e("Error Menú", "Descarga del menú fallida");
    }

    @Override
    public void onSelectedMenuItem(final Object menuItem) {
        getSupportActionBar().show();
        if (menuItem instanceof GGMenu) {
            final GGMenu menu = (GGMenu) menuItem;
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                for (int i = 0; i < getFragmentManager().getBackStackEntryCount(); i++) {
                    if (getFragmentManager().getBackStackEntryAt(i).getName().equals(menu.getmIdentifier())) {
                        getFragmentManager().popBackStack();
                    }
                }
            } else if (menu != null) {
                if (menu.getmBoardType() == GGBoardType.GGCountDown) {
                    changeFragment(new GGCountDown(), menu.getmIdentifier());
                } else if (menu.getmBoardType() == GGBoardType.GGHome) {
                    changeFragment(new GGHello(), menu.getmIdentifier());
                } else {
                    getCollections(menu);
                }

            }
        } else if (menuItem instanceof gogo.skyborn.com.gogo.Models.GGNews) {
            GGBase fragment = new GGDetailNews();
            Bundle bundle = new Bundle();
            bundle.putParcelable("content", (gogo.skyborn.com.gogo.Models.GGNews)menuItem);
            fragment.setArguments(bundle);
            fragment.setOnSelectedMenuItem(this);
            changeFragment(fragment,((gogo.skyborn.com.gogo.Models.GGNews) menuItem).getmId());
        }
    }

    private void getCollections(final GGMenu menu) {
        final MainActivity mainActivity = this;
        GGBase fragment = null;
        switch (menu.getmBoardType()) {
            case GGHome:
                fragment = new GGHello();
                break;
            case GGRoutine:
                break;
            case GGList:
                fragment = new GGNews();
                break;
            case GGOutfit:
                fragment = new GGOutfit();
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", (GGMenu) menu);
        fragment.setArguments(bundle);
        fragment.setOnSelectedMenuItem(mainActivity);
        changeFragment(fragment, menu.getmIdentifier());
    }
}