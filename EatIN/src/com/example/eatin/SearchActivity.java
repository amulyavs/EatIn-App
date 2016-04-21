
package com.example.eatin;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchActivity extends ActionBarActivity {
	//Getting recommendation from user preferences using the Dataset from FourSqaure

	Spinner type_select_spinner,dynamic_spinner;
	public String item;
	public String table_type;
	 ArrayAdapter<String> dataAdapter,dataAdapter1;
      List<String> hungry_list = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		type_select_spinner = (Spinner) findViewById(R.id.type_select);
		dynamic_spinner = (Spinner) findViewById(R.id.try_one);
		

		List<String> mood_list = new ArrayList<String>();
	
		mood_list.add("[How are you Feeling??]");
		mood_list.add("Healthy");
		mood_list.add("Sleepy");
		mood_list.add("Nerdy");
		mood_list.add("Celebration");
        mood_list.add("Party");
        dataAdapter1 = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,mood_list);
        
 
        
        hungry_list.add("[Select a Cuisine]");
        hungry_list.add("American");
        hungry_list.add("Italian");
        hungry_list.add("Asian");
        hungry_list.add("German");
        hungry_list.add("French");
        hungry_list.add("Japanese");
        dataAdapter = new ArrayAdapter<String>
        (this, android.R.layout.simple_spinner_item,hungry_list);
		final Button search_button = (Button) findViewById(R.id.select);

		type_select_spinner
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						
						String item_type = (String) parent
								.getItemAtPosition(position);

						if (item_type.equals("Hungry")) {
							table_type = "Hungry";
							dynamic_spinner.setVisibility(0);
		                    dataAdapter.setDropDownViewResource
		                     (android.R.layout.simple_spinner_dropdown_item);
		                    dynamic_spinner.setAdapter(dataAdapter);
							
							
						}
						if (item_type.equals("Not")) {
							dynamic_spinner.setVisibility(0);
							table_type = "Mood";
							 dataAdapter1.setDropDownViewResource
		                     (android.R.layout.simple_spinner_dropdown_item);
							 dynamic_spinner.setAdapter(dataAdapter1);

						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
					}
				});

		dynamic_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				item = (String) parent.getItemAtPosition(position);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		});

		
		search_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent myIntent = new Intent(SearchActivity.this,
						SearchResults.class);
				myIntent.putExtra("testdata", item);
				myIntent.putExtra("type",table_type);
				startActivity(myIntent);

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search, menu);
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
