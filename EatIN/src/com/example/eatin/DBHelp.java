package com.example.eatin;



import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DBHelp extends SQLiteOpenHelper {
	//Creates Database, Tables,User Model, Test data and other data.

    public static final String DATABASE_Users = "EatIN";
    
    public DBHelp(Context context) {
        super(context, DATABASE_Users, null, 23);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    	Log.i("","11111111111111111111");
    	//db.execSQL("DROP TABLE");
    	Log.i("","5555555555555555555555");
    	String sql = "CREATE TABLE IF NOT EXISTS Hungry (" +
                "Name text, " +
                "ID text)";   
db.execSQL(sql);

ContentValues values2 = new ContentValues();

values2.put("Name", "French Restaurant");
values2.put("ID", "4bf58dd8d48988d10c941735");
db.insert("Hungry", "", values2);


values2.put("Name", "German Restaurant");
values2.put("ID", "4bf58dd8d48988d10d941735");
db.insert("Hungry", "", values2);

values2.put("Name", "Italian Restaurant");
values2.put("ID", "4bf58dd8d48988d110941735");
db.insert("Hungry", "", values2);

values2.put("Name", "Japanese Restaurant");
values2.put("ID", "4bf58dd8d48988d111941735");
db.insert("Hungry", "", values2);

values2.put("Name", "American Restaurant");
values2.put("ID", "4bf58dd8d48988d14e941735");
db.insert("Hungry", "", values2);

values2.put("Name", "Asian Restaurant");
values2.put("ID", "4bf58dd8d48988d142941735");
db.insert("Hungry", "", values2);
 
String sql3 = "CREATE TABLE IF NOT EXISTS MOOD (" +
                "Name text, " +
                "ID text)";   
db.execSQL(sql3);
 
ContentValues values1 = new ContentValues();
values1.put("Name", "Nerdy");
values1.put("ID", "4bf58dd8d48988d1a1941735");
db.insert("MOOD", "", values1); 


values1.put("Name", "Celebration");
values1.put("ID", "4bf58dd8d48988d1d0941735");
db.insert("MOOD", "", values1); 

values1.put("Name", "Sleepy");
values1.put("ID", "4bf58dd8d48988d1e0931735");
db.insert("MOOD", "", values1); 

values1.put("Name", "Healthy");
values1.put("ID", "4bf58dd8d48988d1bd941735");
db.insert("MOOD", "", values1); 

values1.put("Name", "Party");
values1.put("ID", "4d4b7105d754a06376d81259");
db.insert("MOOD", "", values1); 
   
//-------------------------------------------

String sql1 = "CREATE TABLE IF NOT EXISTS Todo (" +
                "UserName text, " +
                "Lat double, " +
                "Long double, " + "Restaurant text)";   
db.execSQL(sql1);

//-------------------------------------------        
    

String sqlUserR = "CREATE TABLE IF NOT EXISTS UserVisitedRest (" +
        "UserName text, " +
        "Lat double, " +
        "Long double, " + "Restaurant text)";   
db.execSQL(sqlUserR);


String sql2 = "CREATE TABLE IF NOT EXISTS USERINFO (" +
        "UserName text, " +
        "Tag text, " +
        "Count double, " +"Rating double, " +"LogFrequency double)";   
db.execSQL(sql2);

//User1


ContentValues values = new ContentValues();
values.put("UserName", "User1");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "4");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Nerdy");
values.put("Count", "5");
values.put("Rating", "2");
values.put("LogFrequency",1);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Sleepy");
values.put("Count", "6");
values.put("Rating", "2");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Healthy");
values.put("Count", "2");
values.put("Rating", "5");
values.put("LogFrequency",1);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Party");
values.put("Count", "7");
values.put("Rating", "5");
values.put("LogFrequency",1.54406);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "French Restaurant");
values.put("Count", "1");
values.put("Rating", "1");
values.put("LogFrequency",0);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Italian Restaurant");
values.put("Count", "6");
values.put("Rating", "2");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "4");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "American Restaurant");
values.put("Count", "2");
values.put("Rating", "1");
values.put("LogFrequency",0.3010);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "Asian Restaurant");
values.put("Count", "4");
values.put("Rating", "3");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User1");
values.put("Tag", "German Restaurant");
values.put("Count", "2");
values.put("Rating", "5");
values.put("LogFrequency",1);db.insert("USERINFO", "", values); 

// user 2
values.put("UserName", "User2");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "4");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Nerdy");
values.put("Count", "5");
values.put("Rating", "2");
values.put("LogFrequency",1);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Sleepy");
values.put("Count", "6");
values.put("Rating", "2");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Healthy");
values.put("Count", "2");
values.put("Rating", "5");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Party");
values.put("Count", "7");
values.put("Rating", "5");
values.put("LogFrequency",1.54406);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "French Restaurant");
values.put("Count", "1");
values.put("Rating", "1");
values.put("LogFrequency",0);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Italian Restaurant");
values.put("Count", "6");
values.put("Rating", "2");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "4");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "American Restaurant");
values.put("Count", "2");
values.put("Rating", "1");
values.put("LogFrequency",.3010);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "Asian Restaurant");
values.put("Count", "4");
values.put("Rating", "3");
values.put("LogFrequency",1.07918);db.insert("USERINFO", "", values); 

values.put("UserName", "User2");
values.put("Tag", "German Restaurant");
values.put("Count", "2");
values.put("Rating", "5");
values.put("LogFrequency",1);db.insert("USERINFO", "", values); 

//User 3
values.put("UserName", "User3");
values.put("Tag", "Celebration");
values.put("Count", "5");values.put("Rating", "5");
values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Nerdy");
values.put("Count", "5");values.put("Rating", "5");
values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Sleepy");
values.put("Count", "5");
values.put("Rating", "5");
values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Healthy");
values.put("Count", "5");values.put("Rating", "5");values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Party");
values.put("Count", "5");values.put("Rating", "5");values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Italian Restaurant");
values.put("Count", "5");values.put("Rating", "5");values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "5");values.put("Rating", "5");values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "American Restaurant");
values.put("Count", "5");
values.put("Rating", "3");
values.put("LogFrequency",1.17);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "Asian Restaurant");
values.put("Count", "5");values.put("Rating", "5");values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 

values.put("UserName", "User3");
values.put("Tag", "German Restaurant");
values.put("Count", "5");values.put("Rating", "5");values.put("LogFrequency",1.3979);db.insert("USERINFO", "", values); 
//User 4
values.put("UserName", "User4");
values.put("Tag", "Celebration");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Nerdy");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Sleepy");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Healthy");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Party");
values.put("Count", "7");
values.put("Rating", "4");
values.put("LogFrequency",1.44715);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "French Restaurant");
values.put("Count", "1");
values.put("Rating", "1");
values.put("LogFrequency",0);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Italian Restaurant");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "American Restaurant");
values.put("Count", "4");
values.put("Rating", "1");
values.put("LogFrequency",0.60205);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "Asian Restaurant");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

values.put("UserName", "User4");
values.put("Tag", "German Restaurant");
values.put("Count", "4");
values.put("Rating", "4");
values.put("LogFrequency",1.20411);db.insert("USERINFO", "", values); 

//User 5
values.put("UserName", "User5");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Nerdy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Sleepy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Healthy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Party");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Italian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "American Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "Asian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User5");
values.put("Tag", "German Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

//User 6
values.put("UserName", "User6");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Nerdy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Sleepy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Healthy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Party");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Italian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "American Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "Asian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User6");
values.put("Tag", "German Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

//User 7
values.put("UserName", "User7");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Nerdy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Sleepy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Healthy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Party");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Italian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "American Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "Asian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User7");
values.put("Tag", "German Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

//User 8
values.put("UserName", "User8");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Nerdy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Sleepy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Healthy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Party");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Italian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "American Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "Asian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User8");
values.put("Tag", "German Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

//User 9
values.put("UserName", "User9");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Nerdy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Sleepy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Healthy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Party");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Italian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "American Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "Asian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User9");
values.put("Tag", "German Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

//User 10
values.put("UserName", "User10");
values.put("Tag", "Celebration");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Nerdy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Sleepy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Healthy");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Party");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "French Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Italian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Japanese Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "American Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "Asian Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values); 

values.put("UserName", "User10");
values.put("Tag", "German Restaurant");
values.put("Count", "3");
values.put("Rating", "3");
values.put("LogFrequency",0.9542);db.insert("USERINFO", "", values);
db.insert("USERINFO", "", values);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
        db.execSQL("DROP TABLE IF EXISTS USERINFO");
        onCreate(db);
        
    }
    
}
