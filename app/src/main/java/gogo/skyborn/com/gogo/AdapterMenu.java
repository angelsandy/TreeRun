package gogo.skyborn.com.gogo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Enums.GGIconType;
import gogo.skyborn.com.gogo.Models.GGMenu;

/**
 * Created by Sandy on 10/24/2017.
 */

public class AdapterMenu extends BaseAdapter {
    private ArrayList<GGMenu> mMenuList;
    private Context mContext;

    public AdapterMenu(ArrayList<GGMenu>mMenuList,Context mContext) {
        this.mMenuList  = mMenuList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        if(mMenuList != null) {
            return mMenuList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = View.inflate(mContext, R.layout.gg_item_menu, null);
        }
        ImageView mIcon = (ImageView)view.findViewById(R.id.imageIcon);
        TextView mTitle = (TextView)view.findViewById(R.id.menuTitle);
        if(mMenuList.get(i) != null) {
            view.setTag(mMenuList.get(i));
            if(mTitle != null) {
                mTitle.setText(mMenuList.get(i).getmTitle());
            }
            if(mIcon != null) {
                int resource = 0;
                switch (mMenuList.get(i).getmIcon()){
                    case GGOufit:
                        resource = R.drawable.hanger;
                        break;
                    case GGRutina:
                        resource = R.drawable.alarm_multiple;
                        break;
                    case GGConsejos:
                        resource = R.drawable.file_document;
                        break;
                    case GGHome:
                        resource = R.drawable.home;
                        break;
                }
                Picasso.with(mIcon.getContext()).load(resource).into(mIcon);
            }
        }
        return view;
    }
}