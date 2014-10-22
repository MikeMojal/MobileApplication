package com.com.calc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {

	// database
	static final int DATABASE_VERSION = 1;
	static final String DATABASE_NAME = "db_user";

	// fields for user table
	static final String ROW_ID = "userid";
	static final String UNAME = "username";
	static final String PWORD = "password";

	// table
	static final String TABLE_NAME = "tbl_user";

	// CREATE TABLE
	static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
			+ ROW_ID + " integer primary key autoincrement, " + UNAME
			+ " varchar(255) not null ," + PWORD + " varchar(255) not null )";

	// DBHELPER
	final Context context;

	DatabaseHelper DBHelper;

	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		// ON CREATE
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
				db.execSQL(DATABASE_CREATE);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// ON UPGRADE (DROP DUPLICATE)
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

			onCreate(db);
		}
	}

	public DBAdapter open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	// METHOD LOG IN
	public Cursor method_login(String username, String password)
			throws SQLException {

		Cursor logincursor = db.query(true, TABLE_NAME, new String[] { ROW_ID,
				UNAME, PWORD }, UNAME + " = '" + username + "' and " + PWORD
				+ "='" + password + "'", null, null, null, null, null);
		return logincursor;

	}

	// METHOD FOR REGISTER

	public long method_register(String username, String password) {
		ContentValues initialValues = new ContentValues();
		initialValues.put(UNAME, username);
		initialValues.put(PWORD, password);
		return db.insert(TABLE_NAME, null, initialValues);

	}

}
