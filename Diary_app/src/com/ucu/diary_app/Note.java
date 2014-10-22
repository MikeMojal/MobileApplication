package com.ucu.diary_app;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Note extends Activity {
DBAdapter dbcon=new DBAdapter(this);
	
	ListView mylist;
	Button button1;
	

	// arraylist

	ArrayList<String> arraylist = new ArrayList<String>();
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_note);
	
	mylist=(ListView) findViewById(R.id.mylist);
	button1=(Button) findViewById(R.id.button1);
		
		// adapter
				adapter = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, arraylist);
				mylist.setAdapter(adapter);
				
				button1.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						dbcon.open();

						populateList();
						dbcon.close();
					}

				});
				
	}

	protected void populateList() {
		// TODO Auto-generated method stub
		
		Cursor c = dbcon.method_showallrecords();
		adapter.clear();
		adapter.notifyDataSetChanged();

		if (c.moveToFirst()) {
			do {
				adapter.add(c.getString(0).toString() + ""
						+ c.getString(1).toString());

			} while (c.moveToNext());
		}
	}
}