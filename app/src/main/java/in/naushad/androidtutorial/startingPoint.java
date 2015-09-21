package in.naushad.androidtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class startingPoint extends AppCompatActivity{
    TextView tvDisplay;
    Button bAdd, bSub;
    int counter;

    private InterstitialAd InterstitialAdsP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point);

        tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        bAdd = (Button) findViewById(R.id.bAdd);
        bSub = (Button) findViewById(R.id.bSub);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starting_point, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "You have pressed Settings!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
