package com.store.beautifulpicsfromtulsi.freewebsitebytulsi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");
        myWebView = (WebView)findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl("http://www.mysmartsupport.com");
        myWebView.setWebViewClient(new WebViewClient());
        //banner ad
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //interstitial ad
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        //mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice(adRequest.DEVICE_ID_EMULATOR).build());
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("EFD34BEA93D17CEF49B08B740CFD800C").build());
        mInterstitialAd.setAdListener(new AdListener(){

          public void onAdLoaded() {
              // Call displayInterstitial() function
              InterstitialAd();
          }

            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                super.onAdClosed();

            }
        }

        );


    }
    public void InterstitialAd(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }/* else {
          finish();
        }*/

    }

    @Override
    public void onBackPressed() {
        if(myWebView.canGoBack()) {
            myWebView.goBack();

        } else {
            InterstitialAd();
            super.onBackPressed();
        }
    }
}
