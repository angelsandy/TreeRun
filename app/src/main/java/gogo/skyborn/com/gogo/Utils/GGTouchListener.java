package gogo.skyborn.com.gogo.Utils;

import android.content.ClipData;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Sandy on 11/18/2017.
 */

public class GGTouchListener implements View.OnTouchListener {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
           // view.setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }
}