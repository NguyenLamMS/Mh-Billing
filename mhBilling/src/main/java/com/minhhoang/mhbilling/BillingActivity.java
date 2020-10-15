package com.minhhoang.mhbilling;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class BillingActivity extends AppCompatActivity  {
    private Button btnBuy;
    private Billing billing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        billing = new Billing(BillingActivity.this);
        if(!billing.PURCHASE)
            setContentView(R.layout.activity_mh_billing);
        else
            setContentView(R.layout.billing_suscess);
        btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billing.buyItem();
            }
        });
    }

}