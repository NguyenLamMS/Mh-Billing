package com.minhhoang.mhbilling;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class BillingActivity extends AppCompatActivity  {
    private Button btnBuy;
    private Billing billing;
    private TextView txtPrice, txtTitle;
    private ImageView imgSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        billing = new Billing(BillingActivity.this);
        if(billing.PURCHASE){
            setContentView(R.layout.activity_mh_billing);
            txtPrice = findViewById(R.id.txtsale);
            txtTitle = findViewById(R.id.content);
            imgSale = findViewById(R.id.imgSale);
            if(billing.getIMAGE() != null)
                imgSale.setImageDrawable(billing.getIMAGE());
            txtPrice.setText(billing.getPRICE());
            txtTitle.setText(billing.getTITLE());
        }
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
