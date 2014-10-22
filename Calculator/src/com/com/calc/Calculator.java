package com.com.calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Calculator extends Activity {
	private int firstnum;
	private int secondnum;
	private int result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calculator);
		Button button1 = (Button) findViewById(R.id.button1);
		Button btnAdd = (Button) findViewById(R.id.btnAdd);
		Button btnSubtract = (Button) findViewById(R.id.btnSubtract);
		Button btnMultiply = (Button) findViewById(R.id.btnMultiply);
		Button btnDivide = (Button) findViewById(R.id.btnDivide);
		Button btnClear = (Button) findViewById(R.id.btnClear);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent loginform = new Intent(Calculator.this,
						MainActivity.class);
				startActivity(loginform);

			}

		});
		btnAdd.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AddOp();
			}

		});
		btnSubtract.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				SubtractOp();
			}

		});
		btnMultiply.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MultiplyOp();
			}

		});
		btnDivide.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				DivideOp();
			}

		});
		btnClear.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Clear();
			}

		});

	}

	private void AddOp() {
		firstnum = 0;
		secondnum = 0;
		EditText first = (EditText) findViewById(R.id.txtfirst);
		EditText second = (EditText) findViewById(R.id.txtsecond);
		String a = first.getText().toString();
		String b = second.getText().toString();
		if (a.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else if (b.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else {
			try {
				firstnum = Integer.parseInt(a);
				secondnum = Integer.parseInt(b);
				Add(firstnum, secondnum);
			} catch (NumberFormatException e) {
				Toast.makeText(getApplicationContext(),
						"Number format only" + " " + "", Toast.LENGTH_SHORT)
						.show();

			}
		}
	}

	private void SubtractOp() {
		firstnum = 0;
		secondnum = 0;
		EditText first = (EditText) findViewById(R.id.txtfirst);
		EditText second = (EditText) findViewById(R.id.txtsecond);
		String a = first.getText().toString();
		String b = second.getText().toString();
		if (a.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else if (b.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else {
			try {
				firstnum = Integer.parseInt(a);
				secondnum = Integer.parseInt(b);
				Subtract(firstnum, secondnum);
			} catch (NumberFormatException e) {
				Toast.makeText(getApplicationContext(),
						"Number format only" + " " + "", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private void MultiplyOp() {
		firstnum = 0;
		secondnum = 0;
		EditText first = (EditText) findViewById(R.id.txtfirst);
		EditText second = (EditText) findViewById(R.id.txtsecond);
		String a = first.getText().toString();
		String b = second.getText().toString();
		if (a.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else if (b.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else {
			try {
				firstnum = Integer.parseInt(a);
				secondnum = Integer.parseInt(b);
				Multiply(firstnum, secondnum);
			} catch (NumberFormatException e) {
				Toast.makeText(getApplicationContext(),
						"Number format only" + " " + "", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private void DivideOp() {
		firstnum = 0;
		secondnum = 0;
		EditText first = (EditText) findViewById(R.id.txtfirst);
		EditText second = (EditText) findViewById(R.id.txtsecond);
		String a = first.getText().toString();
		String b = second.getText().toString();
		if (a.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else if (b.equals("")) {
			Toast.makeText(getApplicationContext(),
					"Please fill the field" + " " + "", Toast.LENGTH_SHORT)
					.show();
		} else {
			try {
				firstnum = Integer.parseInt(a);
				secondnum = Integer.parseInt(b);
				if (firstnum > secondnum) {
					Toast.makeText(getApplicationContext(),
							"Undefine" + " " + "", Toast.LENGTH_SHORT).show();

				} else {
					Divide(firstnum, secondnum);
				}
			} catch (NumberFormatException e) {
				Toast.makeText(getApplicationContext(),
						"Number format only" + " " + "", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}

	private void Add(int firstnumber, int secondnumber) {
		result = 0;
		result = firstnumber + secondnumber;
		Toast.makeText(getApplicationContext(),
				"The answer is " + " " + result, Toast.LENGTH_SHORT).show();

	}

	private void Subtract(int firstnumber, int secondnumber) {
		result = 0;
		result = firstnumber - secondnumber;
		Toast.makeText(getApplicationContext(),
				"The answer is " + " " + result, Toast.LENGTH_SHORT).show();
	}

	private void Multiply(int firstnumber, int secondnumber) {
		result = 0;
		result = firstnumber * secondnumber;
		Toast.makeText(getApplicationContext(),
				"The answer is " + " " + result, Toast.LENGTH_SHORT).show();
	}

	private void Divide(int firstnumber, int secondnumber) {
		result = 0;
		result = firstnumber / secondnumber;
		Toast.makeText(getApplicationContext(),
				"The answer is " + " " + result, Toast.LENGTH_SHORT).show();
	}

	private void Clear() {
		EditText first = (EditText) findViewById(R.id.txtfirst);
		EditText second = (EditText) findViewById(R.id.txtsecond);
		first.setText("");
		second.setText("");

	}

}
