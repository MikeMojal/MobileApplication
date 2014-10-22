package com.ucu.diary_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	


		//database 
		static final int DATABASE_VERSION = 1;
		static final String DATABASE_NAME = "db_user";
				
		
		//fields for user table
		static final String ROW_ID = "userid";
		static final String UNAME = "username";
		
		//field for contact table
		static final String CID = "cid";
		static final String CNAME = "cname";
		

		
		//table
		static final String TABLE_NAME = "tbl_user";
		static final String TABLE_NAME2 = "tbl_contact";
		
		
		//CREATE TABLE
		static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " ( "
				+ ROW_ID + " integer primary key autoincrement, " + UNAME
				+ " varchar(255) not null  )";

		static final String DATABASE_CREATE_CONTACT = "CREATE TABLE " + TABLE_NAME2 + " ( "
				+ CID + " integer primary key autoincrement, " + CNAME
				+ " varchar(255) not null  )";
		
		
		//DBHELPER
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

			
		//ON CREATE
			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
				try {
					db.execSQL(DATABASE_CREATE);
					db.execSQL(DATABASE_CREATE_CONTACT);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		//ON UPGRADE (DROP DUPLICATE)
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
				db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
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
		
		//METHOD LOG IN
		public Cursor method_login(String username)throws SQLException{
			
			Cursor logincursor=db.query(true,TABLE_NAME, new String[] { ROW_ID, UNAME}, UNAME + " = '" + username + "'", null, null, null, null, null);
		return logincursor;
		
		}
		
		//METHOD FOR REGISTER
		
		public long method_register(String username){
			ContentValues initialValues=new ContentValues();
			initialValues.put(UNAME, username);
		
			return db.insert(TABLE_NAME, null, initialValues);
			
				}
		//METHOD SAVE CONTACT
		
		public long method_savecon(String cname){
			ContentValues initialValues=new ContentValues();
			initialValues.put(CNAME, cname);
		
			return db.insert(TABLE_NAME2, null, initialValues);
			
				}
		
		//METHOD FOR SEARCH ID
		
		public Cursor method_searchid(long cid){
			Cursor search_cursor=db.query(true, TABLE_NAME2, new String[]{CID,CNAME},CID+"="+cid+"",null,null,null,null,null);
		return search_cursor;	
		} 
		
		//UPDATE SEARCH ID
		
		public boolean method_update(long cid, String cname){
			
			ContentValues args=new ContentValues();
			args.put(CNAME, cname);
		
			
			return db.update(TABLE_NAME2, args, CID +"="+ cid +"",null)>0;
				}
		
		// SHOW ALL CONTACT RECORDS
		public Cursor method_showallrecords(){
			Cursor search_cursor=db.query(true, TABLE_NAME2, new String[]{CID, CNAME}, null, null, null,null,null,null);
			return search_cursor;
			
			
		}
		
		//DELETE CONTACT
		
		public boolean method_delete(long cid){
			return db.delete(TABLE_NAME2, CID +"=" +cid +"",null)>0;
			
			
		}
		
		
	


}
