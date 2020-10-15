package com.minhhoang.mhbilling;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class BillingActivity extends AppCompatActivity  {
    private Button btnBuy;
    private Billing billing;
    private TextView txtPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        billing = new Billing(BillingActivity.this);
        if(!billing.PURCHASE)
            setContentView(R.layout.activity_mh_billing);
        else
            setContentView(R.layout.billing_suscess);
        btnBuy = findViewById(R.id.btnBuy);
        txtPrice = findViewById(R.id.txtsale);
        String price = "Only $" + Billing.PRICE + " left";
        txtPrice.setText(price);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billing.buyItem();
            }
        });
    }

}