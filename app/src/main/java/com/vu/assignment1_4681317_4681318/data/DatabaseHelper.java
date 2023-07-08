package com.vu.assignment1_4681317_4681318.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "garage.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "car";
    public static final String COLUMN_NO = "Number";
    public static final String COLUMN_BRAND = "Brand";
    public static final String COLUMN_MODEL = "Model";
    public static final String COLUMN_PRICE = "Price";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
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

    public boolean insertData(String brand, String model, int price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRAND, brand);
        cv.put(COLUMN_MODEL, model);
        cv.put(COLUMN_PRICE, price);
        long result = db.insert(TABLE_NAME,null, cv);

        return result != -1;
    }

    @SuppressLint("Range")
    public int getPrice(String brand, String model) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_PRICE};
        String selection = COLUMN_BRAND + " = ? AND " + COLUMN_MODEL + " = ? ";
        String[] selectionArgs = {brand, model};

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        int price = -1;

        if (cursor.moveToFirst()) {
            price = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
        }

        cursor.close();
        return price;
    }
}
