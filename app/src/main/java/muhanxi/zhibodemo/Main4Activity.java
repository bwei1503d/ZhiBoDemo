package muhanxi.zhibodemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;

import static muhanxi.zhibodemo.R.id.mainview;

public class Main4Activity extends Activity {

    private View mainView;
    ViewStub viewStub ;
    View view ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        mainView = (View) findViewById(R.id.mainview);
        mainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println("view setOnTouchListener = " + event.getAction());

                return false;
            }
        });

//        findViewById(R.id.button_viewsub).setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if(viewStub == null){
//                    mainView.setVisibility(View.GONE);
//                    viewStub = (ViewStub)  findViewById(R.id.viewsub);
//                     view =   viewStub.inflate() ;
//                } else {
//                    view.setVisibility(View.GONE);
//                    mainView.setVisibility(View.VISIBLE);
//                }
//
//
//            }
//        });



    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        System.out.println("Main4Activity dispatchTouchEvent = " + ev.getAction());

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.out.println("Main4Activity onTouchEvent = " + event.getAction());

        return super.onTouchEvent(event);
    }
}
