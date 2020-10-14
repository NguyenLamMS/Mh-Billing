package com.minhhoang.mhbilling;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchaseHistoryRecord;
import com.android.billingclient.api.PurchaseHistoryResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

interface checkBuy{
    void resultPurchase(Boolean check);
}
public class billing {
    private Activity mActivity;
    static String ID_PRODUCT;
    static Boolean PURCHASE = false;
    private checkBuy checkBuy;
    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        @Override
        public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> list) {
            if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                Toast.makeText(mActivity, "Thank you for purchasing the product",Toast.LENGTH_SHORT).show();
                mActivity.finish();
            }else{
                Toast.makeText(mActivity, "Transaction failed !!!",Toast.LENGTH_SHORT).show();
            }
        }
    };
    private BillingClient billingClient;

    billing(Activity activity, checkBuy checkBuy){
        mActivity = activity;
        this.checkBuy = checkBuy;
        init();
        checkBuy();
    }
    billing(Activity activity){
        mActivity = activity;
        init();
    }
    void init(){
        billingClient = BillingClient.newBuilder(mActivity)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();
    }
    public void buyItem(){
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    List<String> skuList = new ArrayList<>();
                    skuList.add(ID_PRODUCT);
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(), new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                    .setSkuDetails(list.get(0))
                                    .build();
                            int responseCode = billingClient.launchBillingFlow(mActivity, flowParams).getResponseCode();
                        }
                    });
                }
            }

            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }
    public void checkBuy(){
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchaseHistoryAsync(BillingClient.SkuType.INAPP, new PurchaseHistoryResponseListener() {
                        @Override
                        public void onPurchaseHistoryResponse(@NonNull BillingResult billingResult, @Nullable List<PurchaseHistoryRecord> list) {
                            if (list.size() > 0) {
                                PURCHASE = true;
                                checkBuy.resultPurchase(true);
                                PURCHASE = true;
                            }else{
                                PURCHASE = false;
                                checkBuy.resultPurchase(false);
                            }
                        }
                    });
                }
            }
            @Override
            public void onBillingServiceDisconnected() {

            }
        });
    }

}
