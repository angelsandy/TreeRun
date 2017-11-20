package gogo.skyborn.com.gogo.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Interfaces.GGOnSelectedMenuItem;
import gogo.skyborn.com.gogo.Models.GGNews;

/**
 * Created by Sandy on 11/19/2017.
 */

public class GGHolderArticle extends GGStandardHolder implements View.OnClickListener {
    private ImageView mImg;
    private TextView mTitle;
    private GGNews ggNews;
    private GGOnSelectedMenuItem ggOnSelectedMenuItem;


    public GGHolderArticle(View itemView,GGOnSelectedMenuItem ggOnSelectedMenuItem) {
        super(itemView);
        this.ggOnSelectedMenuItem = ggOnSelectedMenuItem;
        mImg = (ImageView) itemView.findViewById(R.id.imgNte);
        mTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        itemView.setOnClickListener(this);
    }

    @Override
    public void setInfo(Object ggRoutine) {
        if (ggRoutine instanceof GGNews) {
            ggNews = (GGNews) ggRoutine;
            Picasso.with(mImg.getContext()).load(((GGNews) ggRoutine).getmImage()).into(mImg);
            if (mTitle != null) {
                mTitle.setText(((GGNews) ggRoutine).getmTitle());
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (ggOnSelectedMenuItem != null) {
            ggOnSelectedMenuItem.onSelectedMenuItem(ggNews);
        }
    }
}
