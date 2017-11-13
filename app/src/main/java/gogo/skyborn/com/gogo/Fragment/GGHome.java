package gogo.skyborn.com.gogo.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.Models.GGMenu;

public class GGHome extends GGBase implements View.OnClickListener {
    private TextView mHome;
    private Button mAgain;


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_home,container,false);
        mHome = (TextView)view.findViewById(R.id.btnHome);
        mAgain = (Button)view.findViewById(R.id.btnAgain);
        mHome.setOnClickListener(this);
        mAgain.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view == mHome) {
            GGCollectionManager.findMenuWithId("SBHome", new GGOnDownloadResponse() {
                @Override
                public void onDownloadResponse(Object object) {
                    if(onSelectedMenuItem != null) {
                        onSelectedMenuItem.onSelectedMenuItem(object);
                    }
                }
            });
        } else if(view == mAgain) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("identificador","GGCountDown");
                jsonObject.put("titulo","Cuenta");
                jsonObject.put("url","");
                jsonObject.put("tipoIcono","4");
                jsonObject.put("tipoContenido","4");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GGMenu menu = new GGMenu(jsonObject);
            onSelectedMenuItem.onSelectedMenuItem(menu);
        }
    }
}