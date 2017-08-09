package muhanxi.zhibodemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by muhanxi on 17/8/9.
 */

public class CLayoutView extends LinearLayout {
    public CLayoutView(Context context) {
        super(context);
    }

    public CLayoutView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CLayoutView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("CLayoutView dispatchTouchEvent = " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("CLayoutView onInterceptTouchEvent = " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        System.out.println("CLayoutView onTouchEvent = " + ev.getAction());
        return true;
    }
}
