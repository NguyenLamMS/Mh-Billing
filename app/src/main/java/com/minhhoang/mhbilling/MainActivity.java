package com.minhhoang.mhbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements checkBuy {
    private Button button;
    static  Boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        button = findViewById(R.id.button);
//        checkBuy checkBuy = new MainActivity();
//        billing.ID_PRODUCT = "android.test.purchased";
//        billing billing = new billing(MainActivity.this, checkBuy);
//        billing.checkBuy();
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, mhBilling.class));
//            }
//        });
    }

    @Override
    public void resultPurchase(Boolean check) {
        this.check = check;
    }
}