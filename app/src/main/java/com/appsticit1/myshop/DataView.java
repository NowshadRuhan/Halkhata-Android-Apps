package com.appsticit1.myshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class DataView extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        MobileAds.initialize(this,"ca-app-pub-3220851972439401~8319086729");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3220851972439401/2792687058");
        AddLoad();

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();

        }
        else {

            AddLoad();
        }

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.

                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                AddLoad();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
           }
        });

        String dateToast1 = getIntent().getStringExtra("date");
        TextView tv = (TextView) findViewById(R.id.textView);
        DataBase df = new DataBase(DataView.this);


        String s="";
        String[] dataList = df.viewData(dateToast1);

        for( int i = 0; i<dataList.length; i++)
        {
            s=s+dataList[i]+"\n";

        }
        tv.setText(s);
    }
    public void AddLoad(){

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}
