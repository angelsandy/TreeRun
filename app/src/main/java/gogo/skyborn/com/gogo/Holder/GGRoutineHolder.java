package gogo.skyborn.com.gogo.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fittree.skyborn.com.gogo.R;
import gogo.skyborn.com.gogo.Models.GGRoutine;
import gogo.skyborn.com.gogo.Utils.GGSqlInfo;

public class GGRoutineHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView mRoutineTitle;
    private ImageView mRoutineIcon, mRoutineImage, mRoutineAdd, mRoutineDelete;
    private GGRoutine ggRoutine;

    public GGRoutineHolder(View itemView) {
        super(itemView);
        mRoutineTitle = (TextView) itemView.findViewById(R.id.txt_Routine);
        mRoutineImage = (ImageView) itemView.findViewById(R.id.img_background);
        mRoutineIcon = (ImageView) itemView.findViewById(R.id.img_icon);
        mRoutineAdd = (ImageView) itemView.findViewById(R.id.btn_add);
        mRoutineDelete = (ImageView) itemView.findViewById(R.id.btn_close);
        mRoutineAdd.setOnClickListener(this);
        mRoutineDelete.setOnClickListener(this);
    }

    public void setInfo(GGRoutine ggRoutine) {
        if (ggRoutine != null) {
            this.ggRoutine = ggRoutine;
            if (mRoutineTitle != null) {
                mRoutineTitle.setText(ggRoutine.getmRoutineName());
            }
            if (mRoutineIcon != null) {
                int icon = 0;
                switch (ggRoutine.getmIconType()) {
                    case GGBATHROOM:
                        icon = R.drawable.bathtub_with_opened_shower;
                        break;
                    case GGBED:
                        icon = R.drawable.sleeping_bed_silhouette;
                        break;
                    case GGCLOTHES:
                        icon = R.drawable.casual_t_shirt_;
                        break;
                    case GGMAKEUP:
                        icon = R.drawable.beauty_products;
                        break;
                    case GGPAGE:
                        icon = R.drawable.menu;
                        break;
                    case GGPLATE:
                        icon = R.drawable.plate_fork_and_knife;
                        break;
                    case GGRUN:
                        icon = R.drawable.running;
                        break;
                    case GGTOOTHPASTE:
                        icon = R.drawable.bathroom_toothpaste_tube;
                        break;
                    case GGYOGA:
                        icon = R.drawable.buddhist_yoga_pose;
                        break;
                    default:
                        icon = R.drawable.casual_t_shirt;
                        break;
                }
                Picasso.with(mRoutineTitle.getContext()).load(icon).into(mRoutineIcon);
            }
            if (mRoutineImage != null) {
                int background = 0;
               /* switch (ggRoutine.getmBackground()) {
                    case GGCLEAN:
                        break;
                    case GGFOOD:
                        break;
                    case GGGYM:
                        break;
                    case GGNOBACKGROUND:
                        break;
                    case GGPERSONAL:
                        break;
                }*/
                Picasso.with(mRoutineImage.getContext()).load(R.drawable.background).into(mRoutineImage);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mRoutineAdd) {
            if (ggRoutine != null) {
                GGSqlInfo info = new GGSqlInfo(view.getContext());
                info.addRoutine(ggRoutine);
                mRoutineDelete.setVisibility(View.VISIBLE);
                mRoutineAdd.setVisibility(View.GONE);
            }
        }
        if (view == mRoutineDelete) {
            GGSqlInfo info = new GGSqlInfo(view.getContext());
            if(info.deleteRoutine(ggRoutine.getmId())) {
                mRoutineAdd.setVisibility(View.VISIBLE);
                mRoutineDelete.setVisibility(View.GONE);
            }
        }
    }
}
