package muhanxi.zhibodemo;

import android.app.Activity;
import android.os.Bundle;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;

import muhanxi.zhibodemo.widget.MediaController;

public class PlayActivity extends Activity implements PLMediaPlayer.OnPreparedListener,
        PLMediaPlayer.OnInfoListener,
        PLMediaPlayer.OnCompletionListener,
        PLMediaPlayer.OnVideoSizeChangedListener,
        PLMediaPlayer.OnErrorListener{

    private PLVideoView mVideoView;


    private String videoPath = "" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        videoPath = getIntent().getExtras().getString("videoPath");

        mVideoView = (PLVideoView) findViewById(R.id.plvideoview);


        mVideoView.setOnPreparedListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnCompletionListener(this);
        mVideoView.setOnVideoSizeChangedListener(this);
        mVideoView.setOnErrorListener(this);

//        4.3.6 设置画面预览模式
        mVideoView.setDisplayAspectRatio(PLVideoView.ASPECT_RATIO_FIT_PARENT);

        mVideoView.setVideoPath(videoPath);

        MediaController mMediaController = new MediaController(this);
        mVideoView.setMediaController(mMediaController);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mVideoView.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mVideoView != null){
            mVideoView.stopPlayback();
        }
    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
        return false;
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer, int i) {

    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
