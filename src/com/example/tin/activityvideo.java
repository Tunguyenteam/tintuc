package com.example.tin;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class activityvideo extends Activity{
	MainActivity mainActivity = new MainActivity();
	public String titlevvideo;
	ProgressDialog progressDialog;
	VideoView videoView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		getActionBar().setTitle(MainActivity.titleactionbar);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intentvideo = getIntent();
		getActionBar().setTitle(intentvideo.getStringExtra("title"));
		videoView = (VideoView)findViewById(R.id.videoView1);
		progressDialog = new ProgressDialog(activityvideo.this);
		progressDialog.setMessage(intentvideo.getStringExtra("title"));
		progressDialog.setIndeterminate(false);
		progressDialog.setCancelable(false);
		progressDialog.show();
		try {
			MediaController controller = new MediaController(this);
			controller.setAnchorView(videoView);
			Uri videouri = Uri.parse(intentvideo.getStringExtra("linkvideo"));
			videoView.setMediaController(new MediaController(this));
			videoView.setVideoURI(videouri);
		} catch (Exception e) {
			// TODO: handle exception
		}
		videoView.requestFocus();
		videoView.setOnPreparedListener(new OnPreparedListener() {
			
			@Override
			public void onPrepared(MediaPlayer mp) {
				progressDialog.dismiss();
				videoView.start();
				
			}
		});
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return super.onOptionsItemSelected(item);
	}
	

}
