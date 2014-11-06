package com.example.csvreader;

import java.io.*;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowData extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_data);
		Intent i = getIntent();
		// DatabaseHandler handler = new DatabaseHandler(this);
		// String filtertext;
		// filtertext = i.getStringExtra("filtertext");
		// try {
		// Cursor crsor;
		// crsor = handler.GetFilter(filtertext);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		table = (TableLayout) findViewById(R.id.tableview);
		AsyncForTab async = new AsyncForTab();
		async.execute();

	}

	TableLayout table;

	public void initTable() {
		TableRow row = new TableRow(ShowData.this);
		row.setLayoutParams(new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		TextView tv = new TextView(this);
		tv.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);
		tv.setPadding(5, 5, 5, 5);
		tv.setTextColor(getResources().getColor(android.R.color.white));
		tv.setText("Column");
//		row.addView(tv);
		tv = new TextView(ShowData.this);
		tv.setLayoutParams(new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(20);
		tv.setPadding(5, 5, 5, 5);
		tv.setTextColor(getResources().getColor(android.R.color.white));
		tv.setText("Value");
		row.addView(tv);
		table.addView(row);
	}

	public void BuildTable(Cursor crsor) {
		try {
			Cursor c = crsor;
			if(c.moveToFirst())
			{
			int rows = c.getCount();
			int cols = c.getColumnCount();
			for (int i = 0; i < cols; i++) {
				TableRow row = new TableRow(ShowData.this);
				row.setLayoutParams(new TableLayout.LayoutParams(
						LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

//				for (int j = 0; j < rows; j++) {
					TextView tv = new TextView(ShowData.this);
					tv.setLayoutParams(new TableRow.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
					tv.setGravity(Gravity.CENTER);
					tv.setTextColor(getResources().getColor(
							android.R.color.white));
					tv.setTextSize(15);
					tv.setPadding(5, 5, 5, 5);
					String val = c.getString(i).toString();
						tv.setText(val);
					row.addView(tv);
//				}
				//c.moveToNext();
				table.addView(row);
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	DatabaseHandler handler = new DatabaseHandler(this);
	private class AsyncForTab extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			return null;
		}

		ProgressDialog PD;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			table.removeAllViews();
			initTable();
			PD = new ProgressDialog(ShowData.this);
			PD.setTitle("Please Wait..");
			PD.setMessage("Loading...");
			PD.setCancelable(false);
			PD.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			try {
				String filtertext;
				Intent i = getIntent();
				filtertext = i.getStringExtra("filtertext");
				Cursor crsor;
				crsor = handler.GetFilter(filtertext);
				BuildTable(crsor);
			} catch (Exception e) {
				e.printStackTrace();
			}
			super.onPostExecute(result);
			PD.dismiss();
		}

	}

}
