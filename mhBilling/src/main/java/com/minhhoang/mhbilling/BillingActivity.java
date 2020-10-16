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
    private TextView txtPrice, txtTitle, txtHeader;
    private ImageView imgSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        billing = new Billing(BillingActivity.this);
        if(!billing.PURCHASE){
            setContentView(R.layout.activity_mh_billing);
            txtPrice = findViewById(R.id.txtsale);
            txtTitle = findViewById(R.id.content);
            imgSale = findViewById(R.id.imgSale);
            btnBuy = findViewById(R.id.btnBuy);
            txtHeader = findViewById(R.id.hearder);
            if(billing.getImageScreen1() != null)
                imgSale.setImageDrawable(billing.getImageScreen1());
            if(billing.getCOLOR() != null){
                btnBuy.setBackgroundColor(billing.getCOLOR());
                txtHeader.setTextColor(billing.getCOLOR());
                txtPrice.setTextColor(billing.getCOLOR());
            }
            txtPrice.setText(billing.getPRICE());
            txtTitle.setText(billing.getTitleScreen1());
            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    billing.buyItem();
                }
            });
        }
        else{
            setContentView(R.layout.billing_suscess);
            imgSale = findViewById(R.id.imgSale);
            txtHeader = findViewById(R.id.hearder);
            txtTitle = findViewById(R.id.content);
            txtTitle.setText(billing.getTitleScreen2());
            if(billing.getImageScreen2() != null)
                imgSale.setImageDrawable(billing.getImageScreen2());
            if(billing.getCOLOR() != null) {
                txtHeader.setTextColor(billing.getCOLOR());
            }

        }

    }

}
