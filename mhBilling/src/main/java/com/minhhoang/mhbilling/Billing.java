package com.minhhoang.mhbilling;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

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

public class Billing {
    private Activity mActivity;
    public static String ID_PRODUCT;
    public static Boolean PURCHASE = false;
    private static String PRICE = "Only $2";
    private static String TITLE_SCREEN_1 = "Buy premium for only $2. Remove ads permanently.";
    private static String TITLE_SCREEN_2 = "Thank you for purchasing the product.";
    private static Drawable IMAGE_SCREEN_1;
    private static Drawable IMAGE_SCREEN_2;
    private static Integer COLOR;
    private CheckBuy checkBuy;

    public static String getIdProduct() {
        return ID_PRODUCT;
    }

    public static void setIdProduct(String idProduct) {
        ID_PRODUCT = idProduct;
    }

    public static Integer getCOLOR() {
        return COLOR;
    }

    public static void setCOLOR(Integer COLOR) {
        Billing.COLOR = COLOR;
    }

    public static String getTitleScreen1() {
        return TITLE_SCREEN_1;
    }

    public static void setTitleScreen1(String titleScreen1) {
        TITLE_SCREEN_1 = titleScreen1;
    }

    public static String getTitleScreen2() {
        return TITLE_SCREEN_2;
    }

    public static void setTitleScreen2(String titleScreen2) {
        TITLE_SCREEN_2 = titleScreen2;
    }

    public static Drawable getImageScreen1() {
        return IMAGE_SCREEN_1;
    }

    public static void setImageScreen1(Drawable imageScreen1) {
        IMAGE_SCREEN_1 = imageScreen1;
    }

    public static Drawable getImageScreen2() {
        return IMAGE_SCREEN_2;
    }

    public static void setImageScreen2(Drawable imageScreen2) {
        IMAGE_SCREEN_2 = imageScreen2;
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setID_PRODUCT(String ID_PRODUCT) {
        this.ID_PRODUCT = ID_PRODUCT;
    }

    public static void setPURCHASE(Boolean PURCHASE) {
        Billing.PURCHASE = PURCHASE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public void setCheckBuy(CheckBuy checkBuy) {
        this.checkBuy = checkBuy;
    }

    public void setPurchasesUpdatedListener(PurchasesUpdatedListener purchasesUpdatedListener) {
        this.purchasesUpdatedListener = purchasesUpdatedListener;
    }

    public void setBillingClient(BillingClient billingClient) {
        this.billingClient = billingClient;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public String getID_PRODUCT() {
        return ID_PRODUCT;
    }

    public static Boolean getPURCHASE() {
        return PURCHASE;
    }

    public String getPRICE() {
        return PRICE;
    }

    public CheckBuy getCheckBuy() {
        return checkBuy;
    }

    public PurchasesUpdatedListener getPurchasesUpdatedListener() {
        return purchasesUpdatedListener;
    }

    public BillingClient getBillingClient() {
        return billingClient;
    }

    public PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
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

    public Billing(Activity activity, CheckBuy checkBuy, String id_product){
        mActivity = activity;
        this.checkBuy = checkBuy;
        this.ID_PRODUCT = id_product;
        init();
        checkBuy();
    }
    public Billing(Activity activity, CheckBuy checkBuy){
        mActivity = activity;
        this.checkBuy = checkBuy;
        init();
        checkBuy();
    }
    public Billing(Activity activity){
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
                            if(list.size() > 0){
                                BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                        .setSkuDetails(list.get(0))
                                        .build();
                                int responseCode = billingClient.launchBillingFlow(mActivity, flowParams).getResponseCode();
                            }else{
                                Toast.makeText(mActivity, "Error! An error occurred. Please try again later",Toast.LENGTH_SHORT).show();
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
