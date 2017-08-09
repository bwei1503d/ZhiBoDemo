package muhanxi.zhibodemo;

import android.app.Application;

import com.qiniu.pili.droid.streaming.StreamingEnv;

/**
 * Created by muhanxi on 17/7/27.
 */

public class IApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        StreamingEnv.init(this);
    }
}
