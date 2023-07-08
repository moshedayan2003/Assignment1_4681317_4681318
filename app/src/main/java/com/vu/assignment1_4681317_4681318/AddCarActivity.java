package com.vu.assignment1_4681317_4681318;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vu.assignment1_4681317_4681318.data.DatabaseHelper;

public class AddCarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText carbrand_et, carmodel_et, carprice_et;
    Button addcar_btn2;

    DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);


        dbHelper = new DatabaseHelper(this);

        carbrand_et = findViewById(R.id.carbrand_et);
        carmodel_et = findViewById(R.id.carmodel_et);
        carprice_et = findViewById(R.id.carprice_et);
        addcar_btn2 = findViewById(R.id.addcar_btn2);

        addcar_btn2.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                int id=v.getId();
                if(id==R.id.addcar_btn2){
                    insertData();
                }
            }

    private void insertData() {
        String brand = carbrand_et.getText().toString();
        String model = carmodel_et.getText().toString();
        String price = carprice_et.getText().toString();

        boolean isInserted = false;
        if (brand.isEmpty() || model.isEmpty() || price.isEmpty()) {
            Toast.makeText(AddCarActivity.this, "Enter values in all fields!", Toast.LENGTH_SHORT).show();
        }
        else {
            isInserted = dbHelper.insertData(brand, model, Integer.parseInt(price));
        }

        if (isInserted) {
            Toast.makeText(AddCarActivity.this, "Data inserted Successfully!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(AddCarActivity.this, "Data entry Failed!", Toast.LENGTH_LONG).show();
        }
    }
}