package edu.ucuccs.addlistview;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	final Context context = this;
	private StudentOperations studentDBoperation;
	Button btn1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(myhandler2);
		studentDBoperation = new StudentOperations(this);
		studentDBoperation.open();

		List values = studentDBoperation.getAllStudents();
		ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}
			
	public void addUser(View view) {
		LayoutInflater li = LayoutInflater.from(MainActivity.this);
		View add = li.inflate(R.layout.add, null);
	
	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
	alertDialogBuilder.setView(add);

	final EditText added = (EditText) add
			.findViewById(R.id.added);
	
	alertDialogBuilder.setView(add);
	alertDialogBuilder.setTitle("Add Values");
	alertDialogBuilder
	.setPositiveButton("Cancel",
			new DialogInterface.OnClickListener() {
		    public void onClick(DialogInterface dialog,int id) {
			dialog.cancel();
	}
	})
	
	.setCancelable(false)
	.setNegativeButton("Add",
			new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog,int id) {
		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
	Student stud = studentDBoperation.addStudent(added.getText().toString());
	adapter.add(stud);
					    }
					  });
	
				
	AlertDialog alertDialog = alertDialogBuilder.create();
 
	alertDialog.show();
 	

	}

	protected void onListItemClick(ListView l, View v, final int position, long id){

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
		context);

		// set dialog message
		alertDialogBuilder
		.setMessage("Do you want to delete?")
		.setCancelable(false)
		.setPositiveButton("Yes ",new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog,int id) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		Student stud = null;

		stud = (Student) getListAdapter().getItem(position);
		studentDBoperation.deleteStudent(stud);
		adapter.remove(stud);
		}
		})

		.setNegativeButton("No",new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog,int id) {
		dialog.cancel();
		}
		});


		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();}


	@Override
	protected void onResume() {
		studentDBoperation.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		studentDBoperation.close();
		super.onPause();
	}
		View.OnClickListener myhandler2 = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				finish();
				System.exit(0);
		}
		} ;
}


