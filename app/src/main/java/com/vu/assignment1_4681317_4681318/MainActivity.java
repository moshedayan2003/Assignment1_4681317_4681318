package com.vu.assignment1_4681317_4681318;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper dbhelper;
     Button addcar_btn1, checkcar_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbhelper = new DatabaseHelper(this);

        addcar_btn1 = (Button) findViewById(R.id.addcar_btn1);
        checkcar_btn = (Button) findViewById(R.id.checkcar_btn);

        addcar_btn1.setOnClickListener(this);
        checkcar_btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(id == R.id.addcar_btn1){
            goToAddCarPage();
        }

        if(id == R.id.checkcar_btn){
            goToCheckCarPage();
        }
    }

    private void goToAddCarPage(){
        Intent intent = new Intent(this, AddCarActivity.class);
        startActivity(intent);
    }

    private void goToCheckCarPage(){
        Intent intent = new Intent(this, CheckCarActivity.class);
        startActivity(intent);
    }
}