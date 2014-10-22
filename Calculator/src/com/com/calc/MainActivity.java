package com.com.calc;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	DBAdapter dbcon = new DBAdapter(this);

	Button btnLog, btnReg;
	EditText editUser, editPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnLog = (Button) findViewById(R.id.btnLog);
		btnReg = (Button) findViewById(R.id.btnReg);
		editUser = (EditText) findViewById(R.id.editUser);
		editPass = (EditText) findViewById(R.id.editPass);

		btnLog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				dbcon.open();
				Cursor logcursor = dbcon.method_login(editUser.getText()
						.toString(), editPass.getText().toString());
				if (editUser.getText().toString().equals("")
						&& editPass.getText().toString().equals("")) {
					Toast.makeText(getApplicationContext(), "LOG IN FAILED",
							Toast.LENGTH_LONG).show();
					editUser.setText("");
					editPass.setText("");
					return;

				} else if (logcursor.moveToFirst()) {
					Toast.makeText(getApplicationContext(),
							"WELCOME " + logcursor.getString(1).toString(),
							Toast.LENGTH_LONG).show();

					// code for redirect

					Intent loginform = new Intent(MainActivity.this,
							Calculator.class);
					startActivity(loginform);

				} else {
					Toast.makeText(getApplicationContext(), "LOG IN FAILED",
							Toast.LENGTH_LONG).show();
					editUser.setText("");
					editPass.setText("");
					return;
				}
				dbcon.close();
			}

		});

		btnReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent gotoreg = new Intent(MainActivity.this,
						Registration.class);
				startActivity(gotoreg);

			}

		});
	}

}
