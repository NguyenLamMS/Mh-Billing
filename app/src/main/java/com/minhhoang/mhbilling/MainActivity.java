package com.minhhoang.mhbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements CheckBuy {
    private Button button;
    static  Boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        CheckBuy checkBuy = new MainActivity();
        Billing.ID_PRODUCT = "android.test.purchased";
        Billing billing = new Billing(MainActivity.this, checkBuy,"android.test.purchased");
        billing.checkBuy();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BillingActivity.class));
            }
        });
    }

    @Override
    public void resultPurchase(Boolean check) {
        this.check = check;
    }
}