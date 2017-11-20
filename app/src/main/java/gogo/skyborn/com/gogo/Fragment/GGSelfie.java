package gogo.skyborn.com.gogo.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import fittree.skyborn.com.gogo.R;

/**
 * Created by Sandy on 11/19/2017.
 */

public class GGSelfie extends Fragment {
    private ImageView imgSelfie;
    private FloatingActionButton mShare;
    private Bitmap mSelfie;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gg_fragment_selfie, container, false);
        imgSelfie = (ImageView) v.findViewById(R.id.imgSelfie);
        mShare = (FloatingActionButton)v.findViewById(R.id.imgShare);
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_STREAM,getLocalBitmapUri(mSelfie));
                sendIntent.putExtra(Intent.EXTRA_TEXT,"Mira como empiezo mi mañana..");
                sendIntent.setType("*/*");
                startActivity(Intent.createChooser(sendIntent,"Comparte en..."));
            }
        });
        if(imgSelfie != null) {
            imgSelfie.setImageBitmap(mSelfie);
        }
        return v;
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file =  new File(getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }

    public void setmSelfie(Bitmap mSelfie) {
        this.mSelfie = mSelfie;
    }
}
