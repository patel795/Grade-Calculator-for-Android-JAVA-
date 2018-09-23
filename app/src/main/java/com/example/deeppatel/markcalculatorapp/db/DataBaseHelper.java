package com.example.deeppatel.markcalculatorapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.deeppatel.markcalculatorapp.model.Data;

import java.util.ArrayList;

/**
 * Created by Deep Patel on 2017-12-23.
 */

public class DataBaseHelper{

    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Mydatabase.db";

    public static final String INFO_TABLE = "Info";

    public static final String ID = "_id";
    public static final int ID_COLUMN = 0;

    public static final String PASSWORD = "password";
    public static final int PASSWORD_COLUMN = 1;

    public static final String CREATE_INFO_TABLE="CREATE TABLE" + INFO_TABLE + "("
            + ID + "LONG PRIMARY KEY" + PASSWORD + "TEXT" + ")";

    private static class DBHelper extends SQLiteOpenHelper {

        public DBHelper (Context context, String name, int version) {

            super(context, name, null, version);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_INFO_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + INFO_TABLE);

            this.onCreate(db);

        }
    }

    public DataBaseHelper(Context context) {

        openHelper = new DBHelper(context, DATABASE_NAME, DATABASE_VERSION);
    }

    public Data save(Data data) {

        database = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ID, data.getID());
        values.put(PASSWORD, data.getPassword());

        // now do the insert
        long id = database.insert(INFO_TABLE, null,values);

        data.setID(id);

        database.close();

        return data;

    }

    public ArrayList<Data> getAllData(){

        SQLiteDatabase db = openHelper.getReadableDatabase();

        Cursor cursor = db.query(INFO_TABLE,
                null, null, null, null, null, null);

        ArrayList<Data> data = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(ID));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));

            Data datas = new Data(id,password);

            data.add(datas);
        }

        cursor.close();
        db.close();

        return data;
    }
}
