package com.vu.assignment1_4681317_4681318;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CheckCarActivity extends AppCompatActivity {

    private EditText carbrand_et;
    private EditText carmodel_et;
    private TextView carprice_et;
    private Button checkprice_btn;

    private DatabaseHelper dbHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_car);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbHelper = new DatabaseHelper(this);

        carbrand_et = findViewById(R.id.carbrand_et);
        carmodel_et = findViewById(R.id.carmodel_et);
        carprice_et = findViewById(R.id.carprice_et);
        checkprice_btn = findViewById(R.id.checkprice_btn);

        checkprice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand = carbrand_et.getText().toString().trim();
                String model = carmodel_et.getText().toString().trim();

                if (TextUtils.isEmpty(brand) || TextUtils.isEmpty(model)){
                    Toast.makeText(CheckCarActivity.this, "Please enter car brand and model", Toast.LENGTH_SHORT).show();

                }else {
                    checkCarPrice(brand, model);
                }
            }
        });
    }

    private void checkCarPrice(String brand, String model){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"price"};
        String selection = "brand = ? AND model = ?";
        String[] selectionArgs = {brand, model};

        Cursor cursor = db.query("car", projection, selection, selectionArgs, null, null, null);

        if(cursor.moveToFirst()){
            String price = cursor.getString(cursor.getColumnIndexOrThrow("price"));
            carprice_et.setText(price);
        }else{
            carprice_et.setText("");
            Toast.makeText(this, "Car not found", Toast.LENGTH_SHORT).show();
        }

        cursor.close();

    }

    protected void onDestroy(){
        dbHelper.close();
        super.onDestroy();

    }
}