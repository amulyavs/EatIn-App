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

public class SearchResults extends ActionBarActivity {
	//Getting recommendation from user preferences using the Dataset from FourSqaure
	//creating recommendation engine

	public static ListView search_listView;	
    public String Category,table_name,tag;
	public  ArrayAdapter<String> arrayAdapter;
	Cursor cursor;
	ProgressDialog mPrg;    
	public HashSet hs = new HashSet();
	public  List<String> search_list_names = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_results);
		
		mPrg = new ProgressDialog(this);
		mPrg.setCancelable(false);
		mPrg.setMessage("Getting Recommendations for You...");
		mPrg.show();
		Bundle extras=getIntent().getExtras();
		Category=extras.getString("testdata");
		table_name =extras.getString("type");
		search_listView = (ListView) findViewById(R.id.search_list);
		
		//search_list_names.add(Category);
	     
		
	     
	     cursor = MainActivity.db.rawQuery(
					"SELECT ID FROM "+ table_name + " where Name like?",
								new String[] { "%" + Category + "%" });	     
          
	   
	    if (cursor.getCount() > 0) {
			cursor.moveToFirst();
			  tag = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
			Log.i("string", "** Taggggggggggggggggggg ** "+tag);
		} 
	    
	    arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                search_list_names);
	    
	    String v = timeMilisToString(System.currentTimeMillis());
		new HttpAsyncTask()
		.execute("https://api.foursquare.com/v2/venues/search?ll=40.7,-74"
				+ "&categoryId="+tag+"&oauth_token="
				+ FoursquareApp.mAccessToken + "&v=" + v);		

	    
	   // search_list_names.add(tag);
	  
		
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
		// Toast.makeText(getBaseContext(), "Received!",
		// Toast.LENGTH_LONG).show();
		// etResponse.setText(result);

		try {

			JSONObject jsonObject = new JSONObject(result);

			// etResponse.setText(jsonObject.toString(1));
			JSONObject responseJson = jsonObject.getJSONObject("response");

			JSONArray arrayVenues = responseJson.getJSONArray("venues");
			
			
			Log.i("String", "Length"+arrayVenues.length());
			for (int i = 0; i < arrayVenues.length(); i++) {
				search_list_names.add((String) arrayVenues.getJSONObject(i).get("name"));
		
		}
			 cursor = MainActivity.db.rawQuery("SELECT * FROM UserVisitedRest where UserName like? ", new String[] {"%"+MainActivity.userName+"%"});
			 
		     Log.i("Search", "Before Differnce "+search_list_names.size());
				 if(cursor.getCount()>0){
					 
					 List<String> rest_names = new ArrayList<String>();
				    cursor.moveToFirst();
					do {
						
						rest_names.add(cursor.getString(cursor.getColumnIndexOrThrow("Restaurant")));
						Log.i("Search", "Added from DB to a new List with size "+rest_names.size());
		                  
						
					} while (cursor.moveToNext());

					search_list_names.removeAll(rest_names);
					hs.addAll(search_list_names);
					search_list_names.clear();
					search_list_names.addAll(hs);
			  Log.i("Search", "After Difference in Search"+search_list_names.size());
			
		}
		}

		catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				CheckIN.this, android.R.layout.simple_list_item_1, venue_names);


		CheckIN.lv.setAdapter(arrayAdapter);*/
		
		  
		 search_listView.setAdapter(arrayAdapter);
		 mPrg.dismiss();
		Log.i("Search" , "Count of Elements in Adapter"+search_listView.getCount());
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
		getMenuInflater().inflate(R.menu.search_results, menu);
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
