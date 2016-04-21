
package com.example.eatin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CheckIN extends ActionBarActivity {
	//Fetches and Adds checking data to and from Foursquare
	public static ListView lv;	
	ProgressDialog mPrg; 

	public  ArrayAdapter<String> arrayAdapter;
	public  List<String> venue_names = new ArrayList<String>();
	public  List<String> venue_address = new ArrayList<String>();
	public  List<String> category = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		setContentView(R.layout.activity_check_in);
	
		mPrg = new ProgressDialog(this);
		mPrg.setCancelable(false);
		mPrg.setMessage("Getti" +
				"ng Restaurants ...");
		mPrg.show();
		
		lv = (ListView) findViewById(R.id.checkin_list);
		arrayAdapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_list_item_1, venue_names);
		
		String v = timeMilisToString(System.currentTimeMillis());
		new HttpAsyncTask()
		.execute("https://api.foursquare.com/v2/venues/search?ll=40.7,-74"
				+ "&categoryId=4bf58dd8d48988d1a1941735,4bf58dd8d48988d1d0941735,4bf58dd8d48988d1e0931735,4bf58dd8d48988d1bd941735,4d4b7105d754a06376d81259,4bf58dd8d48988d10c941735,4bf58dd8d48988d10d941735,4bf58dd8d48988d110941735,4bf58dd8d48988d111941735,4bf58dd8d48988d14e941735,4bf58dd8d48988d142941735&oauth_token="
				+ FoursquareApp.mAccessToken + "&v=" + v);		
	     
			lv.setOnItemClickListener(new OnItemClickListener(){
	    		
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
					Intent myIntent = new Intent(CheckIN.this,  SecondActivity.class);
					myIntent.putExtra("testdata", venue_names.get(position).toString());
					myIntent.putExtra("testdata1", venue_address.get(position).toString());
				    myIntent.putExtra("testdata2", category.get(position).toString());
	    			startActivity(myIntent);
					
				}});
		
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
	
	
	Log.i("String", "Length"+arrayVenues.length());
	for (int i = 0; i < arrayVenues.length(); i++) {
		venue_names.add((String) arrayVenues.getJSONObject(i).get("name"));
	
		JSONArray arrayVenuesaddress = arrayVenues.getJSONObject(i).getJSONObject("location").getJSONArray("formattedAddress");
	
		String str = "";
		for (int j=0 ;j <arrayVenuesaddress.length();j++){
			str  += arrayVenuesaddress.getString(j);
			
		}
		venue_address.add(str);
		category.add(arrayVenues.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("id"));
			}


}


catch (JSONException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

 lv.setAdapter(arrayAdapter);
 mPrg.dismiss();
	Log.i("List" , "Count"+lv.getCount());
}
}

private String timeMilisToString(long milis) {
SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
Calendar calendar = Calendar.getInstance();

calendar.setTimeInMillis(milis);

return sd.format(calendar.getTime());
}

}
