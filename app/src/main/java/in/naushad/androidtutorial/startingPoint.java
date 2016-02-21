package in.naushad.androidtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class startingPoint extends AppCompatActivity{
    TextView tvDisplay;
    Button bAdd, bSub;
    Toolbar tbStartingPoint;
    int counter;

    private InterstitialAd InterstitialAdsP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point);

        tbStartingPoint = (Toolbar) findViewById(R.id.tbStartingPoint);
        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        bAdd = (Button) findViewById(R.id.bAdd);
        bSub = (Button) findViewById(R.id.bSub);

        setSupportActionBar(tbStartingPoint);
        getSupportActionBar().setTitle("Incrementor/Decrementor");
        getSupportActionBar().setSubtitle("Really Tough Logic :P");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        counter = 0;
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tvDisplay.setText("Your total is " + counter);
            }
        });

        bSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                tvDisplay.setText("Your total is " + counter);
            }
        });


        InterstitialAdsP = new InterstitialAd(this);
        InterstitialAdsP.setAdUnitId(getString(R.string.interstitial_ad_unit_starting_point_id));


                InterstitialAdsP.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        if (InterstitialAdsP.isLoaded()) {
                            InterstitialAdsP.show();
                        }
                    }

                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
            }
        });

        requestNewInterstitial();


    }



    public void requestNewInterstitial(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("A851D03B6D976CAA2BDAABFC232841DC")
                .build();

        // Load the adView object with the request
        InterstitialAdsP.loadAd(adRequest);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
