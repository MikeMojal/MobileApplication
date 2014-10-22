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

public class MainActivity extends Activity {

	DBAdapter dbcon=new DBAdapter(this);
	
	Button btnLogin;
	EditText textPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnLogin=(Button) findViewById(R.id.btnLogin);
        textPass=(EditText) findViewById(R.id.textPass);
        
        btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				dbcon.open();
				Cursor logcursor=dbcon.method_login(textPass.getText().toString());
				
				if (logcursor.moveToFirst()){
					Toast.makeText(getApplicationContext(), "WELCOME "+logcursor.getString(1).toString(),Toast.LENGTH_LONG).show();
					
					// code for redirect
					
					Intent loginform1=new Intent (MainActivity.this,MYHOME.class);
					startActivity(loginform1);
					
					
				}else{
					Toast.makeText(getApplicationContext(), "LOG IN FAILED \n PASSWORD INCORRECT \n REGISTER FIRST",Toast.LENGTH_LONG).show();
					
					Intent loginform=new Intent (MainActivity.this,RegisterPass.class);
					startActivity(loginform);
					
				}
				dbcon.close();
			}
        	
        	
        	
        });

    }



    
}
