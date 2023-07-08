package com.vu.assignment1_4681317_4681318;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "garage.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "car";
    public static final String COLUMN_NO = "Number";
    public static final String COLUMN_BRAND = "Brand";
    public static final String COLUMN_MODEL = "Model";
    public static final String COLUMN_PRICE = "Price";


    private static final String CREATE_CAR_TABLE = "CREATE TABLE car (brand TEXT, model TEXT, price TEXT)";

    public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            SQLiteDatabase db = this.getWritableDatabase();
        }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CAR_TABLE);
        String CREATE_CAR_TABLE = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BRAND + " TEXT, " +
                COLUMN_MODEL + " TEXT, " +
                COLUMN_PRICE + " TEXT); ";

        db.execSQL(CREATE_CAR_TABLE);
}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
