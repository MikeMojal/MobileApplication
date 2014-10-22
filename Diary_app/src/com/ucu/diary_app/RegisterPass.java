package com.ucu.diary_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPass extends Activity {

	DBAdapter dbcon=new DBAdapter(this);
	Button btnReg1;
	EditText textPass11;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_pass);

		btnReg1 = (Button) findViewById(R.id.btnReg1);
		textPass11 = (EditText) findViewById(R.id.textPass11);

		btnReg1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				dbcon.open();
				long insertuser = dbcon.method_register(textPass11.getText()
						.toString());

				if (textPass11.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "REQUIRED TO FILL UP THIS EDIT TEXT",
							Toast.LENGTH_LONG).show();
					
				}
				
				else if (insertuser > 0) {
					Toast.makeText(getApplicationContext(),
							"SUCCESSFULLY ENTER TO DIARY", Toast.LENGTH_LONG)
							.show();
					
				Intent loginform11=new Intent (RegisterPass.this,MYHOME.class);
				startActivity(loginform11);
					
					
					
				}else{
					
					Toast.makeText(getApplicationContext(),
							"FAILED ENTER", Toast.LENGTH_LONG)
							.show();
				}
				dbcon.close();

			}

		});
		
	}

}
