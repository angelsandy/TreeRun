package gogo.skyborn.com.gogo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Models.GGOutfit;
import gogo.skyborn.com.gogo.Models.GGRoutine;
import gogo.skyborn.com.gogo.Utils.GGTouchListener;

/**
 * Created by Sandy on 11/17/2017.
 */

public class GGOutfitHolder extends GGStandardHolder {
    private ImageView mImg;
    private View view;

    public GGOutfitHolder(View itemView) {
        super(itemView);
        mImg = (ImageView) itemView.findViewById(R.id.img_outfit);
        view = itemView;
        itemView.setOnTouchListener(new GGTouchListener());
    }

    @Override
    public void setInfo(Object ggOutfit) {
        if(ggOutfit != null) {
            ggRoutine = ggOutfit;
            view.setTag(ggOutfit);
            Picasso.with(mImg.getContext()).load(((GGOutfit)ggOutfit).getmImage()).into(mImg);
        }
    }
}