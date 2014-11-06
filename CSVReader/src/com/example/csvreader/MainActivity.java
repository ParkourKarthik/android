package com.example.csvreader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

public class MainActivity extends ActionBarActivity {
	private static final int PICKFILE_RESULT_CODE = 1;
	TextView textFile;
	DatabaseHelper helper = new DatabaseHelper(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DatabaseHelper helper = new DatabaseHelper(this);
		helper.getReadableDatabase();
//		DatabaseHandler handle = new DatabaseHandler(getApplicationContext());
//		handle.getWritableDatabase();
		TextView filename= (TextView)findViewById(R.id.csvfile);
		Button buttonPick = (Button)findViewById(R.id.buttonpick);
	       //textFile = (TextView)findViewById(R.id.textfile);
	       buttonPick.setOnClickListener(new Button.OnClickListener(){

	   public void onClick(View arg0) {
	    // TODO Auto-generated method stub

	    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
	             intent.setType("*/*");
	       startActivityForResult(intent,PICKFILE_RESULT_CODE);
	    
	   }});
			Button buttonshow = (Button)findViewById(R.id.buttonshow);
		       //textFile = (TextView)findViewById(R.id.textfile);
		       buttonshow.setOnClickListener(new Button.OnClickListener(){
		    	   
		    	   EditText filterval = (EditText)findViewById(R.id.filtervalue);

		   public void onClick(View arg0) {
		    // TODO Auto-generated method stub
			    Intent it = new Intent(MainActivity.this,ShowData.class);
			    String filtertxt;
			    filtertxt = filterval.getText().toString();
			    it.putExtra("filtertext", filtertxt);
			    startActivity(it);
		   }});
		       
	       

	}
	@Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	  // TODO Auto-generated method stub
	  switch(requestCode){
	  case PICKFILE_RESULT_CODE:
	   if(resultCode==RESULT_OK){
	    String FilePath = "";
	    FilePath = data.getData().getPath();
//	    textFile.setText(FilePath);
	    DatabaseHandler handler = new DatabaseHandler(this);
	    handler.truncateTable();
		try {
			InputStream is = new FileInputStream(FilePath);
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		    try {
		        String line;
		        while ((line = reader.readLine()) != null) {
		             String[] RowData = line.split(",");
		             DataModel mdl = new DataModel();
		             mdl._detailno = RowData[0];
		             mdl._line1 = RowData[1];
		             mdl._line2 = RowData[2];
		             mdl._line3 = RowData[3];
		             mdl._line4 = RowData[4];
		             mdl._line5 = RowData[5];
		             mdl._line6 = RowData[6];
		             mdl._line7 = RowData[7];
		             mdl._line8 = RowData[8];
		             handler.create(mdl);
		            // do something with "data" and "value"
		        }
		    }
		    catch (IOException ex) {
		        // handle exception
		    }
		    finally {
		        try {
		            is.close();
		        }
		        catch (IOException e) {
		            // handle exception
		        }
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
	   }
	   break;
	  }
	 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
