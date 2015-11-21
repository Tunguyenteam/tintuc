package com.example.tin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class activitydoc extends Activity {
	MainActivity mainActivity = new MainActivity();
	TextView tvtitledoc,tvcontentdoc;
	public String titledoc,contentdoc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doc);
		tvtitledoc = (TextView)findViewById(R.id.titledoc);
		tvcontentdoc = (TextView)findViewById(R.id.contentdoc);
		getActionBar().setTitle(MainActivity.titleactionbar);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intentread = getIntent();
		tvtitledoc.setText(intentread.getStringExtra("title"));
		tvcontentdoc.setText(intentread.getStringExtra("content"));
		
	}
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return super.onOptionsItemSelected(item);
	}

}
