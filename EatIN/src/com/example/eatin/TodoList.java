package com.example.eatin;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class TodoList extends ActionBarActivity {
	//Controller for Creating adding deleting To-Do list and to add it to recommendation list

	protected static final String TODO = "Todo";
	protected static final String KEY_NAME = "UserName";
	protected static final String KEY_RESTAURANT = "Restaurant";
	public  List<String> todo_list = new ArrayList<String>();
	ListView todoview;
	Cursor cursor ;
	ArrayAdapter arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo_list);
		
		todoview = (ListView) findViewById(R.id.todo_list);
			
		 cursor = MainActivity.db.rawQuery("SELECT * FROM ToDo where UserName like? ", new String[] {"%"+MainActivity.userName+"%"});
		 
		 if(cursor.getCount()>0){
			 
		    cursor.moveToFirst();
			do {
                  todo_list.add(cursor.getString(cursor.getColumnIndexOrThrow("Restaurant")));
                  
				
			} while (cursor.moveToNext());
			
			 arrayAdapter = new ArrayAdapter<String>(
					this, android.R.layout.simple_list_item_1, todo_list);	
		
			todoview.setAdapter(arrayAdapter);
			
		 }
		 
		 if(cursor.getCount()<=0){
			 Toast.makeText(TodoList.this, "No Favourites", Toast.LENGTH_SHORT).show();
		 }
		 
		 
		 todoview.setOnItemLongClickListener (new OnItemLongClickListener() {

	           
	            public boolean onItemLongClick(AdapterView<?> parent, View view,
	                    int arg2, long arg3) {
	             
	            	MainActivity.db.delete(TODO, "Restaurant=? AND UserName like? ", new String[] {todo_list.get(arg2) , "%"+MainActivity.userName+"%"});
	            	todo_list.remove(arg2);
	                arrayAdapter.notifyDataSetChanged();
	                return true;
	            }

	        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.todo_list, menu);
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
