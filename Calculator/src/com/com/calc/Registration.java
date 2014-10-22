package com.com.calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity {

	DBAdapter dbcon = new DBAdapter(this);

	Button btnLogReg, btnRegReg;
	EditText editUserReg, editPaaReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration);

		btnLogReg = (Button) findViewById(R.id.btnLogReg);
		btnRegReg = (Button) findViewById(R.id.btnRegReg);
		editUserReg = (EditText) findViewById(R.id.editUserReg);
		editPaaReg = (EditText) findViewById(R.id.editPaaReg);

		btnRegReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				dbcon.open();
				long insertuser = dbcon.method_register(editUserReg.getText()
						.toString(), editPaaReg.getText().toString());

				if (insertuser > 0) {
					Toast.makeText(getApplicationContext(),
							"RECORD SUCCESFULLY SAVED", Toast.LENGTH_LONG)
							.show();

				} else {

					Toast.makeText(getApplicationContext(), "RECORD NOT SAVED",
							Toast.LENGTH_LONG).show();
				}
				dbcon.close();

			}

		});

		btnLogReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent mainform = new Intent(Registration.this,
						MainActivity.class);
				startActivity(mainform);
			}

		});
	}

}
