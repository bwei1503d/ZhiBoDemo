package muhanxi.zhibodemo;

import android.app.Activity;
import android.hardware.Camera;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.qiniu.pili.droid.streaming.AVCodecType;
import com.qiniu.pili.droid.streaming.CameraStreamingSetting;
import com.qiniu.pili.droid.streaming.MediaStreamingManager;
import com.qiniu.pili.droid.streaming.StreamingProfile;
import com.qiniu.pili.droid.streaming.StreamingState;
import com.qiniu.pili.droid.streaming.StreamingStateChangedListener;
import com.qiniu.pili.droid.streaming.widget.AspectFrameLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import static com.qiniu.pili.droid.streaming.StreamingState.PREPARING;
import static com.qiniu.pili.droid.streaming.StreamingState.READY;
import static com.qiniu.pili.droid.streaming.StreamingState.STREAMING;

//
//SWCameraStreamingActivity 的主要工作包括：
//
//        SWCameraStreamingActivity 获取 MainActivity 从 app server 获取到的 stream json
//        在 onCreate 通过 stream json 初始化推流 SDK 的核心类 MediaStreamingManager
//        在 onResume 中调用 mMediaStreamingManager.resume();
//        在接收到 READY 之后，开始推流 mMediaStreamingManager.startStreaming();，startStreaming 需要在非 UI 线程中进行操作。
public class SWCameraStreamingActivity extends Activity implements StreamingStateChangedListener {

    private JSONObject mJSONObject;
    private MediaStreamingManager mMediaStreamingManager;
    private StreamingProfile mProfile;
    private String streamJsonStrFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swcamera_streaming);


        AspectFrameLayout afl = (AspectFrameLayout) findViewById(R.id.cameraPreview_afl);
        // Decide FULL screen or real size
        afl.setShowMode(AspectFrameLayout.SHOW_MODE.FULL);
        GLSurfaceView glSurfaceView = (GLSurfaceView) findViewById(R.id.cameraPreview_surfaceView);
        streamJsonStrFromServer = getIntent().getStringExtra("stream_json_str");
        try {
//            mJSONObject = new JSONObject(streamJsonStrFromServer);
//            StreamingProfile.Stream stream = new StreamingProfile.Stream(mJSONObject);
            mProfile = new StreamingProfile();
            try {
                mProfile.setPublishUrl(streamJsonStrFromServer);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            mProfile.setVideoQuality(StreamingProfile.VIDEO_QUALITY_HIGH1)
                    .setAudioQuality(StreamingProfile.AUDIO_QUALITY_MEDIUM2)
                    .setEncodingSizeLevel(StreamingProfile.VIDEO_ENCODING_HEIGHT_480)
                    .setEncoderRCMode(StreamingProfile.EncoderRCModes.QUALITY_PRIORITY);
//                    .setStream(stream);  // You can invoke this before startStreaming, but not in initialization phase.
            //摄像头 相关类
            CameraStreamingSetting setting = new CameraStreamingSetting();
            setting.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK)
                    .setContinuousFocusModeEnabled(true)
                    .setCameraPrvSizeLevel(CameraStreamingSetting.PREVIEW_SIZE_LEVEL.MEDIUM)
                    .setCameraPrvSizeRatio(CameraStreamingSetting.PREVIEW_SIZE_RATIO.RATIO_16_9);

            //美颜
            setting.setBuiltInFaceBeautyEnabled(true);
            setting.setFaceBeautySetting(new CameraStreamingSetting.FaceBeautySetting(1.0f, 1.0f, 0.8f))
                    .setVideoFilter(CameraStreamingSetting.VIDEO_FILTER_TYPE.VIDEO_FILTER_BEAUTY);

            mMediaStreamingManager = new MediaStreamingManager(this, afl, glSurfaceView, AVCodecType.SW_VIDEO_WITH_SW_AUDIO_CODEC);  // soft codec
            mMediaStreamingManager.prepare(setting, mProfile);
            mMediaStreamingManager.setStreamingStateListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMediaStreamingManager.resume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        // You must invoke pause here.
        mMediaStreamingManager.pause();
    }
    @Override
    public void onStateChanged(StreamingState streamingState, Object extra) {
        switch (streamingState) {
            case PREPARING:
                break;
            case READY:
                // start streaming when READY
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (mMediaStreamingManager != null) {
                            mMediaStreamingManager.startStreaming();
                        }
                    }
                }).start();
                break;
            case CONNECTING:
                break;
            case STREAMING:
                // The av packet had been sent.
                break;
            case SHUTDOWN:
                // The streaming had been finished.
                break;
            case IOERROR:
                // Network connect error.
                break;
            case OPEN_CAMERA_FAIL:
                // Failed to open camera.
                break;
            case DISCONNECTED:
                // The socket is broken while streaming
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMediaStreamingManager.destroy();
    }
}
