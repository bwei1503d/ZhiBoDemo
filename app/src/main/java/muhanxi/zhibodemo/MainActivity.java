package muhanxi.zhibodemo;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import muhanxi.zhibodemo.keyboard.KeyBoardHelper;

import static android.R.id.list;
import static android.R.id.message;
import static java.security.AccessController.getContext;

public class MainActivity extends Activity implements KeyBoardHelper.OnKeyBoardStatusChangeListener{


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    private static final String TAG = "MainActivity";
    private Button btn;
    private LinearLayout linearLayout;
    private EditTextPreIme editText;
    private TextView textViewFirst;
    private TextView textViewSecond;
    private TextView textViewThird;
    private int width;
    private TextView textViewXXX;
    private LinearLayout linearLayoutFirst;

    private String requestStreamJson() {
        try {
            // Replace "Your app server" by your app sever url which can get the StreamJson as the SDK's input.
            HttpURLConnection httpConn = (HttpURLConnection) new URL("Your app server").openConnection();
            httpConn.setConnectTimeout(5000);
            httpConn.setReadTimeout(10000);
            int responseCode = httpConn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }
            int length = httpConn.getContentLength();
            if (length <= 0) {
                return null;
            }
            InputStream is = httpConn.getInputStream();
            byte[] data = new byte[length];
            int read = is.read(data);
            is.close();
            if (read <= 0) {
                return null;
            }
            return new String(data, 0, read);
        } catch (Exception e) {
//            showToast("Network error!");
        }
        return null;
    }

    int position = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.start);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            // Get the stream json from http
//                            String streamJson = requestStreamJson();
//                            Intent intent = new Intent(MainActivity.this, SWCameraStreamingActivity.class);
//                            intent.putExtra("stream_json_str", "rtmp://pili-publish.2dyt.com/1503d/streamkey?e=1501136013&token=tYBGEzG7NE_D23EScw43ZTxynVkyt1IpHig5WHRY:rVF7BKcjHHJGejvzI2_YD4940PY=");
//                            startActivity(intent);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

                String streamJson = requestStreamJson();
                Intent intent = new Intent(MainActivity.this, SWCameraStreamingActivity.class);
                intent.putExtra("stream_json_str", "rtmp://pili-publish.2dyt.com/1503d/streamkey?e=1501208736&token=tYBGEzG7NE_D23EScw43ZTxynVkyt1IpHig5WHRY:b949oC2mtx-KqrFv4jov3fPreCg=");
                startActivity(intent);




            }
        });

//        message.setAttribute("state", "live"); 表示直播消息

//        message.setAttribute("type", "leave"); 离开聊天室
//        message.setAttribute("type", "join");   加入聊天室



        findViewById(R.id.playstart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String streamJson = requestStreamJson();
                Intent intent = new Intent(MainActivity.this, PLVideoViewActivity.class);
                intent.putExtra("videoPath", "rtmp://pili-live-rtmp.2dyt.com/1503d/streamkey");
                intent.putExtra("liveStreaming", 1);

                startActivity(intent);

            }
        });


//        ExecutorService executorService =  Executors.newCachedThreadPool();

//        executorService.execute();

//        new ITask().execute("");

        linearLayout = (LinearLayout) findViewById(R.id.b_view);
        editText = (EditTextPreIme) findViewById(R.id.edit_query);
        editText.setListener(new EditTextPreIme.EditTextListener() {
            @Override
            public void onBack() {

            }
        });

        findViewById(R.id.startbtn_key).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout.setVisibility(View.VISIBLE);

                keyPop();



            }
        });

        KeyBoardHelper keyBoardHelper = new KeyBoardHelper(this) ;
        keyBoardHelper.onCreate();
        keyBoardHelper.setOnKeyBoardStatusChangeListener(this);

        ListView listView = (ListView) findViewById(R.id.listview);

//        listView.setBa




        ExecutorService executorService =  Executors.newCachedThreadPool() ;

        executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return null;
            }
        });

//        new ITask().execute()



//        new ITask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");

        linearLayoutFirst = (LinearLayout) findViewById(R.id.firstlinearlayout);

        textViewFirst = (TextView) findViewById(R.id.first_paoche_view);
        textViewXXX = (TextView) findViewById(R.id.xxx);
        textViewSecond = (TextView) findViewById(R.id.second_paoche_view);
        textViewThird = (TextView) findViewById(R.id.thrird_paoche_view);

        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        findViewById(R.id.danmu_key).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //弹幕移动
                textViewFirst.setVisibility(View.VISIBLE);
                float sw =  (float)( - width +  textViewFirst.getWidth()+dip2px(MainActivity.this,80)) ;
                float curTranslationX = textViewFirst.getTranslationX();

                System.out.println("sw = " + sw);
                System.out.println("curTranslationX = " + curTranslationX);
                System.out.println("textViewFirst = " + textViewFirst.getWidth());

                ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayoutFirst, "translationX", curTranslationX,sw );
                animator.setDuration(3000);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        textViewXXX.setText("X 1");
                        ObjectAnimator scaleAnimator = ObjectAnimator.ofFloat(textViewXXX, "xxx", 1.0f,2.0f,1.0f);
                        scaleAnimator.setDuration(500);
                        scaleAnimator.setRepeatCount(10);
                        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float cVal = (Float) animation.getAnimatedValue();
                                textViewXXX.setScaleX(cVal);
                                textViewXXX.setScaleY(cVal);



                            }
                        });
                        scaleAnimator.start();
                        scaleAnimator.addListener(new Animator.AnimatorListener() {
                            @Override
                            public void onAnimationStart(Animator animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                textViewXXX.setText("");

                                textViewFirst.setVisibility(View.GONE);
                                textViewFirst.setTranslationX(0);
                                position = 0 ;

                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animator animation) {
                                System.out.println("position = " + position);
                                position ++ ;

                                textViewXXX.setText("X "+ position);

                            }
                        });


                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {



                    }
                });
                animator.start();



            }
        });





        startActivity(new Intent(this,Main4Activity.class));


    }


    @Override
    protected void onResume() {
        super.onResume();




    }



    public void hidenKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public void showKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText,InputMethodManager.SHOW_FORCED);
    }


    @Override
    public Object onRetainNonConfigurationInstance() {
        System.out.println("onRetainNonConfigurationInstance this = " + this);
        return this;
    }

    @Override
    public void OnKeyBoardPop(int keyBoardheight) {

        System.out.println("keyBoardheight OnKeyBoardPop = " + keyBoardheight);
    }

    @Override
    public void OnKeyBoardClose(int oldKeyBoardheight) {

        System.out.println("keyBoardheight OnKeyBoardClose = " + oldKeyBoardheight);

        editText.setVisibility(View.GONE);
        keyClose();

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



    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
