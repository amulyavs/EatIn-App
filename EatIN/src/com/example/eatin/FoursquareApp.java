package com.example.eatin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.eatin.FoursquareDialog.FsqDialogListener;
import com.example.eatin.FoursquareApp;

public class FoursquareApp {
	
	//Create URLS to hit foursquare API through HTTP call to get JSON response and then process it

	private FoursquareSession mSession;
	private FoursquareDialog mDialog;
	private FsqAuthListener mListener;
	private ProgressDialog mProgress;
	private String mTokenUrl;
	public static String mAccessToken;
	public String str_venue = "";
	public static List<String> venue_names = new ArrayList<String>();

	public static final String CALLBACK_URL = "eatintest-android-app://xyz";
	private static final String AUTH_URL = "https://foursquare.com/oauth2/authenticate?response_type=code";
	private static final String TOKEN_URL = "https://foursquare.com/oauth2/access_token?grant_type=authorization_code";
	private static final String API_URL = "https://api.foursquare.com/v2";

	private static final String TAG = "FoursquareApi";

	public FoursquareApp(Context context, String clientId, String clientSecret) {
		mSession = new FoursquareSession(context);

		mAccessToken = mSession.getAccessToken();

		mTokenUrl = TOKEN_URL + "&client_id=" + clientId + "&client_secret="
				+ clientSecret + "&redirect_uri=" + CALLBACK_URL;

		String url = AUTH_URL + "&client_id=" + clientId + "&redirect_uri="
				+ CALLBACK_URL;

		FsqDialogListener listener = new FsqDialogListener() {
			@Override
			public void onComplete(String code) {
				getAccessToken(code);
			}

			@Override
			public void onError(String error) {
				mListener.onFail("Authorization failed");
			}
		};

		mDialog = new FoursquareDialog(context, url, listener);
		mProgress = new ProgressDialog(context);

		mProgress.setCancelable(false);
	}

	private void getAccessToken(final String code) {
		mProgress.setMessage("Getting access token ...");
		mProgress.show();

		new Thread() {
			@Override
			public void run() {
				Log.i(TAG, "Getting access token");

				int what = 0;

				try {
					URL url = new URL(mTokenUrl + "&code=" + code);

					Log.i(TAG, "Opening URL " + url.toString());

					HttpURLConnection urlConnection = (HttpURLConnection) url
							.openConnection();

					urlConnection.setRequestMethod("GET");
					urlConnection.setDoInput(true);
					// urlConnection.setDoOutput(true);

					urlConnection.connect();

					JSONObject jsonObj = (JSONObject) new JSONTokener(
							streamToString(urlConnection.getInputStream()))
							.nextValue();
					mAccessToken = jsonObj.getString("access_token");

					Log.i(TAG, "Got access token: " + mAccessToken);
				} catch (Exception ex) {
					what = 1;

					ex.printStackTrace();
				}

				mHandler.sendMessage(mHandler.obtainMessage(what, 1, 0));
			}
		}.start();
	}

	private void fetchUserName() {
		mProgress.setMessage("Finalizing ...");

		new Thread() {
			@Override
			public void run() {
				Log.i(TAG, "Fetching user name");
				int what = 0;

				try {
					String v = timeMilisToString(System.currentTimeMillis());
					URL url = new URL(API_URL + "/users/self?oauth_token="
							+ mAccessToken + "&v=" + v);

					Log.d(TAG, "Opening URL " + url.toString());

					HttpURLConnection urlConnection = (HttpURLConnection) url
							.openConnection();

					urlConnection.setRequestMethod("GET");
					urlConnection.setDoInput(true);
					// urlConnection.setDoOutput(true);

					urlConnection.connect();

					String response = streamToString(urlConnection
							.getInputStream());
					JSONObject jsonObj = (JSONObject) new JSONTokener(response)
							.nextValue();

					JSONObject resp = (JSONObject) jsonObj.get("response");
					JSONObject user = (JSONObject) resp.get("user");

					String firstName = user.getString("firstName");
					String lastName = user.getString("lastName");

					Log.i(TAG, "Got user name: " + firstName + " " + lastName);

					mSession.storeAccessToken(mAccessToken, firstName + " "
							+ lastName);
				} catch (Exception ex) {
					what = 1;

					ex.printStackTrace();
				}

				mHandler.sendMessage(mHandler.obtainMessage(what, 2, 0));
			}
		}.start();
	}

	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.arg1 == 1) {
				if (msg.what == 0) {
					fetchUserName();
				} else {
					mProgress.dismiss();

					mListener.onFail("Failed to get access token");
				}
			} else {
				mProgress.dismiss();

				mListener.onSuccess();
			}
		}
	};

	public boolean hasAccessToken() {
		return (mAccessToken == null) ? false : true;
	}

	public void setListener(FsqAuthListener listener) {
		mListener = listener;
	}

	public String getUserName() {
		return mSession.getUsername();
	}

	public void authorize() {
		mDialog.show();
	}

	public void getNearby(String latitude, String longitude) throws Exception {
		Log.i("String", "33333333333333333333333333333333333333");
		String v = timeMilisToString(System.currentTimeMillis());
		new HttpAsyncTask()
		.execute("https://api.foursquare.com/v2/venues/search?ll="
				+ latitude + "," + longitude
				+ "&categoryId=4bf58dd8d48988d1a1941735,4bf58dd8d48988d1d0941735,4bf58dd8d48988d1e0931735,4bf58dd8d48988d1bd941735,4d4b7105d754a06376d81259,4bf58dd8d48988d10c941735,4bf58dd8d48988d10d941735,4bf58dd8d48988d110941735,4bf58dd8d48988d111941735,4bf58dd8d48988d14e941735,4bf58dd8d48988d142941735&oauth_token="
				+ FoursquareApp.mAccessToken + "&v=" + v);
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
			Log.i("String", "44444444444444444444444444444");
			try {

				JSONObject jsonObject = new JSONObject(result);

				// etResponse.setText(jsonObject.toString(1));
				JSONObject reponseJson = jsonObject.getJSONObject("response");

				JSONArray arrayVenues = reponseJson.getJSONArray("venues");

				/*str_venue += "Number of Restaurants = " + arrayVenues.length();
				str_venue += "\n********************************\n";
*/
				for (int i = 0; i < arrayVenues.length(); i++) {
					venue_names.add((String) arrayVenues.getJSONObject(i).get("name"));
				}

				Log.i("String", "Arraaaauuuusffdfgdfgdfgsdffuuuu"+venue_names.size());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private String streamToString(InputStream is) throws IOException {

		String str = "";

		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is));

				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}

				reader.close();
			} finally {
				is.close();
			}

			str = sb.toString();
		}

		return str;
	}

	private String timeMilisToString(long milis) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();

		calendar.setTimeInMillis(milis);

		return sd.format(calendar.getTime());
	}

	public interface FsqAuthListener {
		public abstract void onSuccess();

		public abstract void onFail(String error);
	}

}