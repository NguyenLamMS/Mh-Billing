package com.minhhoang.mhbilling;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity implements CheckBuy {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        CheckBuy checkBuy = new MainActivity();
        Billing billing = new Billing(MainActivity.this, checkBuy);
        billing.setCOLOR(Color.parseColor("#FF0000"));
        Billing.LIST_PRODUCT.add("buy_vip_a_month");
        Billing.LIST_PRODUCT.add("buy_vip_3_month");
        Billing.LIST_PRODUCT.add("buy_vip_6_month");
        Billing.LIST_PRODUCT.add("buy_vip_a_year");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BillingActivity.class));
            }
        });
    }
    @Override
    public void resultPurchase(Boolean check, Activity mActivity) {
        mActivity.startActivity(new Intent(mActivity, BillingActivity.class));
    }
}