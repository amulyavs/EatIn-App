package com.example.eatin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class SecondActivity extends ActionBarActivity {
	//Controller to show recommendations in the UI

	EditText restaurant;
	RatingBar mBar;

	String selected_item;
	String selected_add;
	String selected_category;
	Double ratings;
	Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);

		final Button check_button = (Button) findViewById(R.id.check_in);
		final Button todo_button = (Button) findViewById(R.id.add_list);

		restaurant = (EditText) findViewById(R.id.address_details);
		Bundle extras = getIntent().getExtras();
		selected_item = extras.getString("testdata");
		selected_add = extras.getString("testdata1");
		selected_category = extras.getString("testdata2");
		String temp = selected_item + "\n" + selected_add + "\n";

		restaurant.setText(temp);

		check_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBar = (RatingBar) findViewById(R.id.ratingbar1);
				float[] i = new float[] { mBar.getRating() };
				
				Log.i("String", "value is.. " + i[0]+"selected_category "+selected_category);
				ratings = (double) i[0];
				String Tag = "";
				cursor = MainActivity.db.rawQuery(
						"SELECT Name FROM Mood where ID=?",
						new String[] { selected_category });
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					Tag = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
					Log.i("string", "** Mood **");
				}
				else {
					Log.i("string", "** Tag if**");
					//Log.i("String", "Column name"+cursor.getColumnName(0) +"Column ID" + cursor.getColumnName(1));
					cursor = MainActivity.db.rawQuery(
							"SELECT Name FROM Hungry where ID=?",
							new String[] { selected_category });
					if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					Log.i("String", "Colum");
					Tag = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
					}
				}
				// if(cursor.getCount()>0){
				// Log.i("string","**if 1**");
				cursor = MainActivity.db
						.rawQuery(
								"SELECT * FROM USERINFO where Tag=? and UserName like?",
								new String[] { Tag,
										"%" + MainActivity.userName + "%" });
				// cursor.close();

				Log.i("String", "Rating float" + i[0]);
				// get from user
				if (cursor.getCount() > 0) {
					Log.i("string", "**if 2**");
					cursor.moveToFirst();
					double updatedrating = (Double.parseDouble(cursor
							.getString(cursor.getColumnIndexOrThrow("Rating"))) + ratings) / 2;
					double updatedLogfreq = 1 + Math.log10(Double
							.parseDouble(cursor.getString(cursor
									.getColumnIndexOrThrow("Count")))
							* updatedrating);
					ContentValues values = new ContentValues();
					double updatedcount = Double.parseDouble(cursor
							.getString(cursor.getColumnIndexOrThrow("Count"))) + 1;
					Log.i("string", "****" + updatedrating + "--"
							+ updatedLogfreq + "--" + updatedcount + "\n");
					values.put("Count", updatedcount);
					values.put("Rating", updatedrating);
					values.put("LogFrequency", "" + updatedLogfreq);
					MainActivity.db.update("USERINFO", values,
							"UserName= ? and Tag=?", new String[] {
									MainActivity.userName, Tag });

				}
				// }
				else {
					Log.i("", " else ");
					ContentValues values = new ContentValues();

					values.put("UserName", MainActivity.userName);
					values.put("Tag", Tag);
					values.put("Count", 1);
					values.put("Rating", ratings);
					Log.i("String", "Rating" + ratings);
					values.put("LogFrequency", "" + 1 + Math.log10(3 * 5));
					MainActivity.db.insert("USERINFO", "", values);
					Log.i("String",
							"SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
					cursor = MainActivity.db.rawQuery(
							"SELECT *  FROM USERINFO", null);
					cursor.moveToFirst();
					do {

						Log.i("",
								"1. " + cursor.getString(0)
										+ cursor.getString(1)
										+ cursor.getString(2)
										+ cursor.getString(3)
										+ cursor.getString(4) + "\n");
					} while (cursor.moveToNext());
				}

				cursor = MainActivity.db
                        .rawQuery(
                                "SELECT * FROM UserVisitedRest where Restaurant=? and UserName like?",
                                new String[] {selected_item,"%" + MainActivity.userName + "%" });

                if (cursor.getCount() <= 0) {
                    
                    ContentValues values = new ContentValues();

                    values.put("UserName", MainActivity.userName);
                    values.put("Lat", "40.7");
                    values.put("Long", "-74");
                    values.put("Restaurant", selected_item);
                    
                    MainActivity.db.insert("UserVisitedRest", "", values);
                    
                    Toast.makeText(SecondActivity.this,
    						"Checked In @ " + selected_item,
    						Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(SecondActivity.this, "Already Visisted this Restaurant!!", Toast.LENGTH_SHORT)
                    .show();
                }
				
				
				
				Intent myIntent = new Intent(SecondActivity.this,
				HomeActivity.class); startActivity(myIntent);
				 

			}
		});
		
		
		todo_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
	
				cursor = MainActivity.db.rawQuery(
						"SELECT Restaurant FROM Todo where UserName=? AND Restaurant=? AND Lat=40.7 AND Long =-74",
						new String[] { MainActivity.userName, selected_item });
				
				if(cursor.getCount()<=0){
				
				ContentValues values = new ContentValues();

	            values.put("UserName", MainActivity.userName);
	            values.put("Lat", "40.7");
	            values.put("Long", "-74");
	            values.put("Restaurant", selected_item);
	            
	            MainActivity.db.insert("ToDo", "", values);	values = new ContentValues();
	            
	            Toast.makeText(SecondActivity.this, "Added to your Todo List", Toast.LENGTH_SHORT)
				.show();
				}
				if(cursor.getCount() > 0){
				Toast.makeText(SecondActivity.this, "Already Exist in your Todo List", Toast.LENGTH_SHORT)
				.show();
				}
				todo_button.setVisibility(View.GONE);
				
			}
				
			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.second, menu);
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
