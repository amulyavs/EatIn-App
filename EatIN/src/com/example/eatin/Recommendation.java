
package com.example.eatin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class Recommendation extends ActionBarActivity {
	//Create  recommendations
	//Getting recommendation from user preferences using the Dataset from FourSqaure

	 private ListView recommendation_spinner, fav_spinner;
	 public  List<String> fav_list = new ArrayList<String>();
	 public  List<String> rec_list = new ArrayList<String>();
		HashSet<String> rec_list_tag = new HashSet<String>();
		
	 public  List<String> restaurant_list_tag = new ArrayList<String>();
	 Cursor cursor;
	 String categoryList;
	public HashSet hs = new HashSet();
	public  ArrayAdapter arrayAdapter;
	ProgressDialog mPrg;
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommendation);
		
		mPrg = new ProgressDialog(this);
		mPrg.setCancelable(false);
		mPrg.setMessage("Getting Recommendations for You...");
		mPrg.show();
		
		recommendation_spinner = (ListView) findViewById(R.id.recommendation_list);
		fav_spinner = (ListView) findViewById(R.id.fav_list);
		cursor = MainActivity.db
				.rawQuery(
						"SELECT * FROM USERINFO where UserName like? ORDER BY LogFrequency DESC LIMIT 5",
						new String[] {
								"%" + MainActivity.userName + "%" });
		if(cursor.getCount()>0){
			   
			    cursor.moveToFirst();
			    do {
			    	rec_list_tag.add(cursor.getString(cursor.getColumnIndexOrThrow("Tag")));
					  Log.i("String","Looogggggggggggggggggg "+cursor.getString(1)+cursor.getString(cursor.getColumnIndexOrThrow("LogFrequency"))+"\n");
					
				}while (cursor.moveToNext());
		}
		
		for (String item :rec_list_tag) {
			
			
			cursor = MainActivity.db.rawQuery(
					"SELECT ID FROM Mood where Name like?",
					new String[] { "%" + item + "%" });	     
			if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				restaurant_list_tag.add(cursor.getString(cursor.getColumnIndexOrThrow("ID")));
				Log.i("string", "** Mood **");
			}
			else {
				Log.i("string", "** Tag if**");
				//Log.i("String", "Column name"+cursor.getColumnName(0) +"Column ID" + cursor.getColumnName(1));
				cursor = MainActivity.db.rawQuery(
						"SELECT ID FROM Hungry where Name like?",
					new String[] { "%" + item + "%" });	
				if (cursor.getCount() > 0) {
				cursor.moveToFirst();
				Log.i("String", "Colum");
				restaurant_list_tag.add(cursor.getString(cursor.getColumnIndexOrThrow("ID")));
				}
			}
			
		}
		 
		
		arrayAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_list_item_1, rec_list);	
		
		
		 categoryList = restaurant_list_tag.toString().replace("[", "").replace("]", "")
	            .replace(", ", ",");
		
		
		 String v = timeMilisToString(System.currentTimeMillis());
			new HttpAsyncTask()
			.execute("https://api.foursquare.com/v2/venues/search?ll=40.7,-74"
					+ "&categoryId="+categoryList+"&oauth_token="
					+ FoursquareApp.mAccessToken + "&v=" + v);		

		
		cursor = MainActivity.db.rawQuery("SELECT * FROM ToDo where UserName like? LIMIT 5", new String[] {"%"+MainActivity.userName+"%"});
		 
		 if(cursor.getCount()>0){
			 fav_list.add("Your Favourite Restaurants:");
		    cursor.moveToFirst();
			do {
				fav_list.add(cursor.getString(cursor.getColumnIndexOrThrow("Restaurant")));
                 
				
			} while (cursor.moveToNext());
		 }
		 
		 
		 
		ArrayAdapter arrayAdapter_fav = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, fav_list);	
	
		fav_spinner.setAdapter(arrayAdapter_fav);
		   }
	public static String GET(String url) {
		InputStream inputStream = null;
		String result = "";
		try {

		// create HttpClient

		HttpClient httpclient = new DefaultHttpClient();

		// make GET request to the given URL
		HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

		// receive response as inputStream
		inputStream = httpResponse.getEntity().getContent();

		// convert inputstream to string
		if (inputStream != null)
			result = convertInputStreamToString(inputStream);
		else
			result = "Did not work!";

		} catch (Exception e) {
		Log.d("InputStream", e.getLocalizedMessage());
		}

		return result;
		}

		private static String convertInputStreamToString(InputStream inputStream)
		throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
			new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
		result += line;

		inputStream.close();
		return result;

		}

		private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

		return GET(urls[0]);
		}

		// onPostExecute displays the results of the AsyncTask.
		@Override
		protected void onPostExecute(String result) {
		
		try {

			JSONObject jsonObject = new JSONObject(result);

			JSONObject responseJson = jsonObject.getJSONObject("response");

			JSONArray arrayVenues = responseJson.getJSONArray("venues");
			
			
			Log.i("Recommend", "Length"+arrayVenues.length());
			for (int i = 0; i < arrayVenues.length(); i++) {
				rec_list.add((String) arrayVenues.getJSONObject(i).get("name"));
		
		}
			cursor = MainActivity.db.rawQuery("SELECT * FROM UserVisitedRest where UserName like? ", new String[] {"%"+MainActivity.userName+"%"});
			 
		     Log.i("Recommend", "Before Differnce "+rec_list.size());
				 if(cursor.getCount()>0){
					 
					 List<String> rest_names = new ArrayList<String>();
				    cursor.moveToFirst();
					do {
						
						rest_names.add(cursor.getString(cursor.getColumnIndexOrThrow("Restaurant")));
						Log.i("Recommend", "Added from DB to a new List with size "+rest_names.size());
		                  
						
					} while (cursor.moveToNext());

					rec_list.removeAll(rest_names);
					hs.addAll(rec_list);
					rec_list.clear();
					rec_list.addAll(hs);
					rec_list.subList(5, rec_list.size()).clear();
			  Log.i("Recommend", "After Difference in Search"+rec_list.size());
			
				 } 
		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
    
		 recommendation_spinner.setAdapter(arrayAdapter);
		 mPrg.dismiss();
		 
		 Log.i("List" , "Count"+recommendation_spinner.getCount());
		
		}
		}
		private String timeMilisToString(long milis) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(milis);

		return sd.format(calendar.getTime());
		}

		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.recommendation, menu);
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
