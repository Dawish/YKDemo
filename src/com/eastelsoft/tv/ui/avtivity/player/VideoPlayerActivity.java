package com.eastelsoft.tv.ui.avtivity.player;

import com.eastelsoft.tv.R;
import com.eastelsoft.tv.ui.avtivity.base.BaseActivity;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnCompletionListener;
import io.vov.vitamio.MediaPlayer.OnInfoListener;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.ImageView;

public class VideoPlayerActivity extends BaseActivity implements OnCompletionListener, OnInfoListener {
	
	private String data_id;
	private String video_url;
	private String title;
	private String brief;
	
	private String mPath;
	private String mTitle;
	private VideoView mVideoView;
	/** 当前缩放模式 */
	private int mLayout = VideoView.VIDEO_LAYOUT_ZOOM;
	private MediaController mMediaController;
	private View mLoadingView;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		Intent intent = getIntent();
		data_id = intent.getStringExtra("id");
		mPath = intent.getStringExtra("url");
		title = intent.getStringExtra("title");
		brief = intent.getStringExtra("brief");
		mPath = "http://live.3gv.ifeng.com/zixun.m3u8";
		mPath = "http://61.155.192.42/videos/v0/20140828/7a/de/59e9a52eb2dbed184ef512a6017b0fe3.mp4?key=98fa086ded79bc90&m=v&qd_src=ih5&qd_tm=1410775731650&qd_ip=10.46.227.57&qd_sc=e2071363efe0ecf2cb9c8e453c0cf31d&ip=10.46.227.57&uuid=6fce1654-5416bab3-33";
		System.out.println("video url : " + mPath);

		// ~~~ 检测Vitamio是否解压解码包
		if (!LibsChecker.checkVitamioLibs(this)){
			System.out.println("解码失败!!!");
			return;
		}
		// ~~~ 获取播放地址和标题

		// ~~~ 绑定控件
		setContentView(R.layout.activity_video_player);
		mVideoView = (VideoView) findViewById(R.id.surface_view);
		mLoadingView = findViewById(R.id.video_loading);

		// ~~~ 绑定事件
		mVideoView.setOnCompletionListener(this);
		mVideoView.setOnInfoListener(this);
		mVideoView.setVideoChroma(MediaPlayer.VIDEOCHROMA_RGB565);
		
		// ~~~ 绑定数据
		if (mPath.startsWith("http:"))
			mVideoView.setVideoURI(Uri.parse(mPath));
		else
			mVideoView.setVideoPath(mPath);

		//设置显示名称
		mMediaController = new MediaController(this);
		mMediaController.setFileName(mTitle);
		mVideoView.setMediaController(mMediaController);
		mVideoView.requestFocus();
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mVideoView != null)
			mVideoView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mVideoView != null)
			mVideoView.resume();
		startPlayer();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mVideoView != null)
			mVideoView.stopPlayback();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		if (mVideoView != null)
			mVideoView.setVideoLayout(mLayout, 0);
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCompletion(MediaPlayer player) {
		finish();
	}

	private void stopPlayer() {
		if (mVideoView != null)
			mVideoView.pause();
	}

	private void startPlayer() {
		if (mVideoView != null)
			mVideoView.start();
	}

	private boolean isPlaying() {
		return mVideoView != null && mVideoView.isPlaying();
	}

	/** 是否需要自动恢复播放，用于自动暂停，恢复播放 */
	private boolean needResume;

	@Override
	public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
		switch (arg1) {
		case MediaPlayer.MEDIA_INFO_BUFFERING_START:
			//开始缓存，暂停播放
			if (isPlaying()) {
				stopPlayer();
				needResume = true;
			}
			mLoadingView.setVisibility(View.VISIBLE);
			break;
		case MediaPlayer.MEDIA_INFO_BUFFERING_END:
			//缓存完成，继续播放
			if (needResume)
				startPlayer();
			mLoadingView.setVisibility(View.GONE);
			break;
		case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
			//显示 下载速度
			//mListener.onDownloadRateChanged(arg2);
			break;
		}
		return true;
	}
}
