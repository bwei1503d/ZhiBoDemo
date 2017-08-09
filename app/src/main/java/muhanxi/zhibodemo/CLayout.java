package muhanxi.zhibodemo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by muhanxi on 17/8/7.
 */

public class CLayout extends LinearLayout {


    public CLayout(Context context) {
        super(context);
    }

    public CLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("CLayout  = dispatchTouchEvent " );
//super.dispatchTouchEvent(ev);
//        return false;
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.out.println("CLayout  = onInterceptTouchEvent " + ev.getAction() );

//        return false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("CLayout  = onTouchEvent " + event.getAction());

        return true;
//        return super.onTouchEvent(event);
    }
}
