package com.ucu.diary_app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MYHOME extends Activity {

	DBAdapter dbcon = new DBAdapter(this);

	Button btnADD, btnDELETE, btnEDIT, btnSEARCH, btnnote;
	EditText edittype, textID;
	
	// private variables
	String vname, vserachid;
	Integer vid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myhome);

		btnADD = (Button) findViewById(R.id.btnADD);
		btnDELETE = (Button) findViewById(R.id.btnDELETE);
		btnEDIT = (Button) findViewById(R.id.btnEDIT);
		btnSEARCH = (Button) findViewById(R.id.btnSEARCH);

		btnnote= (Button) findViewById(R.id.btnnote);

		edittype = (EditText) findViewById(R.id.edittype);
		textID = (EditText) findViewById(R.id.textID);

		
btnADD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				vname = edittype.getText().toString();

				dbcon.open();

				long consave = dbcon.method_savecon(vname);

				
				if (edittype.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "WRITE YOUR NOTE",
							Toast.LENGTH_LONG).show();
					
				}
				
				else if (consave > 0) {

					Toast.makeText(getApplicationContext(), "SAVED DIARY NOTE",
							Toast.LENGTH_LONG).show();

				
				} else {

					Toast.makeText(getApplicationContext(), "NOT SAVED",
							Toast.LENGTH_LONG).show();
				}
				dbcon.close();
			}

		});

		btnSEARCH.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				dbcon.open();

				vid = Integer.parseInt(textID.getText().toString());
				Cursor searchid = dbcon.method_searchid(vid);

			
				
				 if (searchid.moveToFirst()) {

					// count the number of records searched

					Integer recount = searchid.getCount();

					// notify the user
					Toast.makeText(getApplicationContext(),
							recount.toString() + "NOTE found",
							Toast.LENGTH_LONG).show();
					// assign the searched values

					textID.setText(searchid.getString(0).toString());
					edittype.setText(searchid.getString(1).toString());

			

				} else {

					// notify record not found

					Toast.makeText(getApplicationContext(),
							"NOTE IS NOT FOUND", Toast.LENGTH_LONG).show();
				}
				dbcon.close();

			}

		});

		btnDELETE.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				vid = Integer.parseInt(textID.getText().toString());

				dbcon.open();
				
			

				if (dbcon.method_delete(vid)) {

					Toast.makeText(getApplicationContext(),
							"record succesfull delete", Toast.LENGTH_LONG)
							.show();

				

				} else {

					Toast.makeText(getApplicationContext(), "delete failed",
							Toast.LENGTH_LONG).show();
				}

			}

		});

		btnEDIT.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				vid = Integer.parseInt(textID.getText().toString());
				vname = edittype.getText().toString();

				dbcon.open();

				if (dbcon.method_update(vid, vname)) {

					Toast.makeText(getApplicationContext(), "Record Update",
							Toast.LENGTH_LONG).show();

					//populateList();

				} else {
					Toast.makeText(getApplicationContext(), "fail to  Update",
							Toast.LENGTH_LONG).show();

				}
				dbcon.close();
			}

		});
		
		
		btnnote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent loginform11=new Intent (MYHOME.this,Note.class);
				startActivity(loginform11);
					
			}
			
		
		});

	}

}
		