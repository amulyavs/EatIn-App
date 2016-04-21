package com.example.eatin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends ActionBarActivity {
	//Set controller for Home page


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);


		final Button search_button = (Button) findViewById(R.id.Search_new);
		final Button check_button = (Button) findViewById(R.id.CheckIn_new);
		final Button recommend_button = (Button) findViewById(R.id.Recommendations_new);
		final Button todoButton = (Button) findViewById(R.id.TodoList_new);



		search_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent2 = new Intent(HomeActivity.this, SearchActivity.class);
				startActivity(myIntent2); 

			}
		});
		check_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {


				Intent myIntent3 = new Intent(HomeActivity.this, CheckIN.class);
				startActivity(myIntent3);	
				}

		}
				);
		recommend_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent4 = new Intent(HomeActivity.this, Recommendation.class);
				startActivity(myIntent4);

			}
		});

		todoButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent5 = new Intent(HomeActivity.this, TodoList.class);
				startActivity(myIntent5);

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
