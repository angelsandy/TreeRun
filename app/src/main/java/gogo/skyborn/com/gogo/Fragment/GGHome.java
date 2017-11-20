package gogo.skyborn.com.gogo.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView mSelfie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_home, container, false);
        mHome = (TextView) view.findViewById(R.id.btnHome);
        mAgain = (Button) view.findViewById(R.id.btnAgain);
        mSelfie = (ImageView) view.findViewById(R.id.imgCamera);
        mSelfie.setOnClickListener(this);
        mHome.setOnClickListener(this);
        mAgain.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            GGSelfie fragment = new GGSelfie();
            fragment.setmSelfie(imageBitmap);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.framContainer, fragment, "GGSelfie").addToBackStack("GGSelfie").commit();
        }

    }

    @Override
    public void onClick(View view) {
        if (view == mSelfie) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(intent, 200);
            }
        }
        if (view == mHome) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("identificador", "SBHome");
                jsonObject.put("titulo", "Portada");
                jsonObject.put("url", "1");
                jsonObject.put("tipoIcono", "0");
                jsonObject.put("tipoContenido", "0");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GGMenu menu = new GGMenu(jsonObject);
            onSelectedMenuItem.onSelectedMenuItem(menu);
        } else if (view == mAgain) {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("identificador", "GGCountDown");
                jsonObject.put("titulo", "Cuenta");
                jsonObject.put("url", "");
                jsonObject.put("tipoIcono", "4");
                jsonObject.put("tipoContenido", "4");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GGMenu menu = new GGMenu(jsonObject);
            onSelectedMenuItem.onSelectedMenuItem(menu);
        }
    }
}