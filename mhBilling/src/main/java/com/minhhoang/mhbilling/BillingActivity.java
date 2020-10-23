package com.minhhoang.mhbilling;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class BillingActivity extends AppCompatActivity  {
    private Button btnBuy;
    private Billing billing;
    private TextView txtTitle, txtHeader;
    private ImageView imgSale;
    RadioButton oneMonthns, threeMonths, sixMonths, twelveMonths;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        if(!billing.PURCHASE){
            setContentView(R.layout.activity_mh_billing);
            txtTitle = findViewById(R.id.content);
            btnBuy = findViewById(R.id.btnBuy);
            txtHeader = findViewById(R.id.hearder);
            oneMonthns = findViewById(R.id.oneMonths);
            threeMonths = findViewById(R.id.threeMonths);
            sixMonths = findViewById(R.id.sixMonths);
            twelveMonths = findViewById(R.id.twelveMonths);

            if(billing.getCOLOR() != null){
                btnBuy.setBackgroundColor(billing.getCOLOR());
                txtHeader.setTextColor(billing.getCOLOR());
                oneMonthns.setButtonTintList(ColorStateList.valueOf(billing.getCOLOR()));
                threeMonths.setButtonTintList(ColorStateList.valueOf(billing.getCOLOR()));
                sixMonths.setButtonTintList(ColorStateList.valueOf(billing.getCOLOR()));
                twelveMonths.setButtonTintList(ColorStateList.valueOf(billing.getCOLOR()));
            }
            txtTitle.setText(billing.getTitleScreen1());
            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   try {
                       if(oneMonthns.isChecked()){
                           billing = new Billing(BillingActivity.this, Billing.LIST_PRODUCT.get(0));
                       }
                       if(threeMonths.isChecked()){
                           billing = new Billing(BillingActivity.this, Billing.LIST_PRODUCT.get(1));
                       }
                       if(sixMonths.isChecked()){
                           billing = new Billing(BillingActivity.this, Billing.LIST_PRODUCT.get(2));
                       }
                       if(twelveMonths.isChecked()){
                           billing = new Billing(BillingActivity.this, Billing.LIST_PRODUCT.get(3));
                       }
                       billing.buyItem();
                   }catch (Exception e){
                       Toast.makeText(BillingActivity.this, "Error! No id product.", Toast.LENGTH_SHORT).show();
                   }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
