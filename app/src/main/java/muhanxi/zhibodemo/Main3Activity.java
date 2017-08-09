package muhanxi.zhibodemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import muhanxi.zhibodemo.keyboard.KeyBoardHelper;

public class Main3Activity extends Activity implements KeyBoardHelper.OnKeyBoardStatusChangeListener {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        linearLayout = (LinearLayout) findViewById(R.id.b_view);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                keyPop();



            }
        });


        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(this);

        keyBoardHelper.onCreate();

        keyBoardHelper.setOnKeyBoardStatusChangeListener(this);











    }


    private void keyPop(){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)linearLayout.getLayoutParams() ;
        params.setMargins(0,0,0,556);
        params.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(params);
    }

    private void keyClose(){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)linearLayout.getLayoutParams() ;
        params.setMargins(0,0,0,40);
        params.gravity = Gravity.BOTTOM;
        linearLayout.setLayoutParams(params);
    }

    @Override
    public void OnKeyBoardPop(int keyBoardheight) {
        System.out.println("OnKeyBoardPop keyBoardheight = " + keyBoardheight);
    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {
        System.out.println("OnKeyBoardClose keyBoardheight = " + oldKeyBoardheight);
        keyClose();


    }
}
