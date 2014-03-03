package com.example.yamba;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import android.widget.EditText;
import android.widget.Toast;

public class StatusActivity extends Activity {

	static final String LOG_TAG = "StatusActivity";

	EditText status_editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// this line uses R to lookup activity_status.xml
		// reads XML and creates java classes and set's properties
		// after this line all widgets (buttons and controls) are in memory
		setContentView(R.layout.activity_status);

		// gets references to widgets
		status_editText = (EditText) findViewById(R.id.editStatusText);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.status, menu);
		return true;
	}

	public void onClick(View arg0) {

		// TODO Auto-generated method stub
		final String statusText = status_editText.getText().toString();
		Log.d(LOG_TAG, "Posting new status: " + statusText);

		// This creates a new thread pointing to an anonymous method, and then
		// starts it
		Thread t = new Thread() {
			public void run() {

				try {
					String user = "student";
					String pass = "password";
					Twitter twitter = new Twitter(user, pass);
					twitter.setAPIRootUrl("http://yamba.marakana.com/api");
					twitter.setStatus(statusText);
					Log.d(LOG_TAG, "Successfuly posted status: " + statusText);
					
					
					Toast.makeText(StatusActivity.this, "Succesfuly Posted", Toast.LENGTH_SHORT).show();

				} catch (TwitterException e) {

					Log.e(LOG_TAG, "Error posting status", e);

					e.printStackTrace();

				}
			}
		};
		
		t.start();

	}
}
