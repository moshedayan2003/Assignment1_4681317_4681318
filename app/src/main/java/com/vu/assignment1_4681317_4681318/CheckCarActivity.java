package com.vu.assignment1_4681317_4681318;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vu.assignment1_4681317_4681318.data.DatabaseHelper;

public class CheckCarActivity extends AppCompatActivity implements View.OnClickListener {

    EditText carbrand_et2;
    EditText carmodel_et2;
    TextView carprice_et2;
    Button checkprice_btn;

    public DatabaseHelper dbHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_car);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dbHelper = new DatabaseHelper(this);

        carbrand_et2 = findViewById(R.id.carbrand_et2);
        carmodel_et2 = findViewById(R.id.carmodel_et2);
        carprice_et2 = findViewById(R.id.carprice_et2);
        checkprice_btn = findViewById(R.id.checkprice_btn);

        //checkprice_btn.setOnClickListener(new View.OnClickListener() {
        checkprice_btn.setOnClickListener(this);


    }
    private void displayPrice() {
        String brand = carbrand_et2.getText().toString().trim();
        String model = carmodel_et2.getText().toString().trim();

        if(brand.isEmpty())
        {
            Toast.makeText(this, "Please enter brand name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(model.isEmpty())
        {
            Toast.makeText(this, "Please enter model name!", Toast.LENGTH_SHORT).show();
            return;
        }

        int price = dbHelper.getPrice(brand,model);

        if(price == -1){
            Toast.makeText(this, "Car not found. Please enter the brand and model again!",Toast.LENGTH_SHORT).show();
            carprice_et2.setText("");
        }
        else {
            carprice_et2.setText(String.valueOf(price));
        }
    }

    @Override
    public void onClick(View v) {
        int id =v.getId();
        if(id == R.id.checkprice_btn)
        {
            displayPrice();
        }
    }
}
