package com.minhhoang.mhbilling;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class mhBilling extends AppCompatActivity  {
    private Button btnBuy;
    private billing billing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        billing = new billing(mhBilling.this);
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