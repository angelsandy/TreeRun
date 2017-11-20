package gogo.skyborn.com.gogo.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Adapter.AdapterOutfit;
import gogo.skyborn.com.gogo.DataManager.GGCollectionManager;
import gogo.skyborn.com.gogo.Interfaces.GGOnDownloadResponse;
import gogo.skyborn.com.gogo.Models.GGBoard;
import gogo.skyborn.com.gogo.Models.GGCollectionNews;
import gogo.skyborn.com.gogo.Models.GGCollectionOutfit;
import gogo.skyborn.com.gogo.Models.GGMenu;
import gogo.skyborn.com.gogo.Models.GGNews;
import gogo.skyborn.com.gogo.Utils.GGDownload;

/**
 * Created by Sandy on 11/19/2017.
 */

public class GGDetailNews extends GGBase {
    private GGNews mGgNews;
    private ImageView mImageDetail;
    private TextView mTitle, mContent, mAutor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            if (bundle != null) {
                mGgNews = bundle.getParcelable("content");
            }

        }
    }

    private void setDetailNews() {
        if (mGgNews != null) {
            if (mImageDetail != null) {
                Picasso.with(mImageDetail.getContext()).load(mGgNews.getmImage()).into(mImageDetail);
            }
            if (mTitle != null) {
                mTitle.setText(mGgNews.getmTitle());
            }
            if(mAutor != null) {
                mAutor.setText(mGgNews.getmAutor()+" "+mGgNews.getmFecha());
            }
            if(mContent != null) {
                mContent.setText(mGgNews.getmConent());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gg_fragment_detail_article, container, false);
        mImageDetail = (ImageView) view.findViewById(R.id.imgPhoto);
        mTitle = (TextView) view.findViewById(R.id.txtTitle);
        mAutor = (TextView) view.findViewById(R.id.txtautor);
        mContent = (TextView) view.findViewById(R.id.txtContent);
        FloatingActionButton share = (FloatingActionButton)view.findViewById(R.id.floatingShare);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,mGgNews.getmTitle());
                sendIntent.putExtra(Intent.EXTRA_TEXT,mGgNews.getmConent());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Comparte en..."));
            }
        });
        setDetailNews();
        return view;
    }
}