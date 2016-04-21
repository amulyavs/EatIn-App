package com.example.eatin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.eatin.FoursquareApp.FsqAuthListener;
import com.example.eatin.R.drawable;
public class MainActivity extends ActionBarActivity {
	//Set controller for login and provide access to our application to use user sensitive data through client secret and code

	private FoursquareApp mFsqApp;
	private ProgressDialog mProgress;
	private boolean flag;
	public static String userName;
	public static SQLiteDatabase db;


	public static final String CLIENT_ID = "YRIRUTEFWMOTET20F3N0CMYV0GGGJZJZPG5TSMJITL0YDKCM";
	public static final String CLIENT_SECRET = "VAWJFBZOGKURZM2GMEN0YFVRPCVWQGPXMAIQLZDDZMDUP5CD";
	Activity activity;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		db =(new DBHelp(this)).getWritableDatabase();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity =this;

		final Button login_button = (Button) findViewById(R.id.login);
	
		
	
		
		mFsqApp = new FoursquareApp(this, CLIENT_ID, CLIENT_SECRET);
		mProgress = new ProgressDialog(this);
		mProgress.setMessage("Loading data ...");
		

		FsqAuthListener listener = new FsqAuthListener() {
			@Override
			public void onSuccess() {
				
				
				Toast.makeText(MainActivity.this,
						"Connected as " + mFsqApp.getUserName(),
						Toast.LENGTH_SHORT).show();
				userName = mFsqApp.getUserName();
				Intent myIntent2 = new Intent(MainActivity.this, HomeActivity.class);
		          startActivity(myIntent2); 
			}

			@Override
			public void onFail(String error) {
				Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT)
						.show();
				
			}
		};

		mFsqApp.setListener(listener);

		login_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				activity.findViewById(android.R.id.content).setBackgroundResource(drawable.bg);
				mFsqApp.authorize();

			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
