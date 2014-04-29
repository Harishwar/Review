package com.mobsquad.review;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreenActivity extends Activity {

	private long ldelay = 3000;
	SessionManagement session;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome_screen);
		
		TimerTask task = new TimerTask(){

			@Override
			public void run() {
								
					Login();
			}	
		};
		
		Timer timer = new Timer();
		timer.schedule(task, ldelay);
	}

	public void Login(){
		Intent loginIntent = new Intent(this, LoginActivity.class)	;
		startActivity(loginIntent);
	}
	
	public void UserLoggedIn(){
		Intent loggedInIntent = new Intent(this, DashboardActivity.class)	;
		startActivity(loggedInIntent);
	}