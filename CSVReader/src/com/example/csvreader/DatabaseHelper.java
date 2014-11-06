package com.example.csvreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "csvfile";
	private static final String TABLE_NAME = "data";
	private static final String USER_NAME = "username";
	private static final String USER_AGE = "age";
	private static final String USER_SEX = "sex";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);
	}


	@Override
	public void onCreate(SQLiteDatabase db) {
		String create_table = "create table " + TABLE_NAME
				+ " (DetailNo integer,Line1 text,Line2 text,Line3 text,Line4 text,Line5 text,Line6 text,Line7 text,Line8 text)";
		db.execSQL(create_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}

}
