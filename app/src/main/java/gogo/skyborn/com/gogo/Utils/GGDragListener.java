package gogo.skyborn.com.gogo.Utils;

import android.graphics.drawable.Drawable;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import fittree.skyborn.com.gogo.R;

/**
 * Created by Sandy on 11/18/2017.
 */

public class GGDragListener implements View.OnDragListener {
  //  Drawable enterShape = getResources().getDrawable(R.drawable.shape_droptarget);
    //Drawable normalShape = getResources().getDrawable(R.drawable.shape);

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // do nothing
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
                // Dropped, reassign View to ViewGroup
                View view = (View) event.getLocalState();
                ViewGroup owner = (ViewGroup) view.getParent();
                owner.removeView(view);
                LinearLayout container = (LinearLayout) v;
                if(container.getChildCount() > 0 && container.getChildAt(0) instanceof TextView) {
                    container.getChildAt(0).setVisibility(View.GONE);
                }
                container.addView(view);
                view.setVisibility(View.VISIBLE);
                break;
            case DragEvent.ACTION_DRAG_ENDED:
            default:
                break;
        }
        return true;
    }
}
