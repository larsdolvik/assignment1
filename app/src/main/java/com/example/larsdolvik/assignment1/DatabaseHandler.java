package com.example.larsdolvik.assignment1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Have gotten alot of help from: http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/

public class DatabaseHandler extends SQLiteOpenHelper {

    // All static variables
    private static final int DATABASE_VERSION = 1;         // Database version
    private static final String DATABASE_NAME = "nameLog"; // Database name
    private static final String TABLE_NAME = "Names";      // Table name


    private static final String KEY_ID = "id";                  // Table -
    private static final String KEY_NAME = "name";              // columns -
                                                                // names

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NAME_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT)";
        db.execSQL(CREATE_NAME_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);    // Drop older table if existed
        onCreate(db);                                        // Create tables again
    }

    //adds  new name
    public void addName(Name name) {
        SQLiteDatabase db = this.getWritableDatabase();       //opens database

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name.getName());                // Name

        db.insert(TABLE_NAME, null, values);                 // Inserting row
        db.close();                                          // Closing database connection
    }

    // getting one single name
    public Name getName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
        KEY_NAME}, KEY_ID + "=?",
        new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
        cursor.moveToFirst();

        Name name = new Name(Integer.parseInt(cursor.getString(0)),
        cursor.getString(1));

        return name;                                          //returns name
    }

    //gets name count
    public int getNameCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;                                          //returns count
    }
}