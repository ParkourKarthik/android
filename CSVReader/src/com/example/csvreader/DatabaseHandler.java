package com.example.csvreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends DatabaseHelper {

	private static final String DB_NAME = "csvfile";
	private static final String TABLE_NAME = "data";
	private static final String USER_NAME = "username";
	private static final String USER_AGE = "age";
	private static final String USER_SEX = "sex";

	private static final int DATABASE_VERSION = 2;
	private SQLiteDatabase sqliteDB;
	private DatabaseHelper dbHelper;

	
	public DatabaseHandler(Context context)
	{
		
		super(context);
//		dbHelper=new DatebaseHelper(context, DB_NAME, null, DATABASE_VERSION);
		
	}
	// Open Database 
	public void open()
	{
		sqliteDB=dbHelper.getWritableDatabase();
		
	}
	
	// Close Database 
    public void close() {
        if (sqliteDB != null)
            sqliteDB.close();
    }
    
    public void truncateTable(){
    	SQLiteDatabase db = this.getWritableDatabase();
    	db.execSQL("delete from data");
    }
    
    
	public Cursor getUserInfo() {
		
		Cursor cursor = sqliteDB.query(TABLE_NAME, null, null, null, null, null,
				null);
		cursor.moveToFirst();
//		while(cursor.moveToNext())
//		{
//		Log.e("In getUserInfo()", "name:"+cursor.getString(1)+", age:"+cursor.getString(2));
//		}
		return cursor;
	}
	public long insertUserInfo(String name, String age)
	{
		ContentValues contentValues=new ContentValues();
		sqliteDB=dbHelper.getWritableDatabase();
		contentValues.put(USER_NAME, name);
		contentValues.put(USER_AGE, age);
		
		return  sqliteDB.insert(TABLE_NAME, null, contentValues);
	}
	public boolean create(DataModel moditem) {

		ContentValues values = new ContentValues();

		values.put("DetailNo", moditem._detailno);
		values.put("Line1", moditem._line1);
		values.put("Line2", moditem._line2);
		values.put("Line3", moditem._line3);
		values.put("Line4", moditem._line4);
		values.put("Line5", moditem._line5);
		values.put("Line6", moditem._line6);
		values.put("Line7", moditem._line7);
		values.put("Line8", moditem._line8);

		SQLiteDatabase db = this.getWritableDatabase();

		boolean createSuccessful = db.insert("data", null, values) > 0;
		db.close();

		return createSuccessful;
	}
	
	
	public Cursor GetFilter(String FilterText)
	{
		SQLiteDatabase db = this.getWritableDatabase();
//		String[] allColumns = new String[]{"DetailNo","Line1","Line2","Line3","Line4","Line5","Line6","Line7","Line8"};
//		Cursor cursor = db.query("data", allColumns, "DetailNo='"+FilterText+"'", null, null, null, null);

		Cursor cursor = null;
        String Query ="SELECT * FROM data where DetailNo = '"+ FilterText +"'";
        cursor = db.rawQuery(Query, null);
//        if (cursor != null && cursor.moveToFirst()) {
//      do {
//          } while (cursor.moveToNext());
//
//      cursor.close();
  	return cursor;
		
	}
	
}
