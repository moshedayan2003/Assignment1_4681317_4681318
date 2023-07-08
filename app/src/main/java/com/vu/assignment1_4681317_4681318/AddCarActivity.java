package com.vu.assignment1_4681317_4681318;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCarActivity extends AppCompatActivity{

    private EditText carbrand_et;
    private EditText carmodel_et;
    private EditText carprice_et;
    private Button addcar_btn2;

    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);


        dbHelper = new DatabaseHelper(this);

        carbrand_et = findViewById(R.id.carbrand_et);
        carmodel_et = findViewById(R.id.carmodel_et);
        carprice_et = findViewById(R.id.carprice_et);
        addcar_btn2 = findViewById(R.id.addcar_btn2);

        addcar_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String brand = carbrand_et.getText().toString().trim();
                String model = carmodel_et.getText().toString().trim();
                String price = carprice_et.getText().toString().trim();

                if (TextUtils.isEmpty(brand) || TextUtils.isEmpty(model)){
                    Toast.makeText(AddCarActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();

                }else {
                    addCarToDatabase(brand, model, price);
                }
            }
        });

    }

    private void addCarToDatabase(String brand, String model, String price){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("brand", brand);
        values.put("model", model);
        values.put("price", price);

        long newRowId = db.insert("Car", null, values);
        if (newRowId != -1){
            Toast.makeText(this, "Car added successfully", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(this, "Failed to add car", Toast.LENGTH_SHORT).show();
        }


    }

    protected void onDestroy(){
        dbHelper.close();
        super.onDestroy();
    }
}