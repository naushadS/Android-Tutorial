package in.naushad.androidtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class splitter extends AppCompatActivity {

    EditText etBillValue;
    SeekBar sbNoPeople;
    TextView tvSeekbarCounter,tvIndividualAmount;
    float BillAmount=0;
    float IndividualShare=0;

    static{
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splitter);

        initXML();

        if(etBillValue.getText().toString().matches(""))
        {
            Toast.makeText(splitter.this,"Enter the bill amount!",Toast.LENGTH_SHORT).show();
            sbNoPeople.setEnabled(false);
            sbNoPeople.setProgress(1);
        }
        etBillValue.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                sbNoPeople.setEnabled(true);
                sbNoPeople.setProgress(1);
                calculateIndividualShare();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        tvSeekbarCounter.setText(sbNoPeople.getProgress()+"");

        sbNoPeople.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<1) {
                    sbNoPeople.setProgress(1);
                }else {
                    tvSeekbarCounter.setText(progress + "");
                    calculateIndividualShare();
                }
            }
            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void initXML() {
        getSupportActionBar().setTitle("Split the Bill!");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
        etBillValue = (EditText) findViewById(R.id.etBillValue);
        sbNoPeople = (SeekBar) findViewById(R.id.sbNoPeople);
        tvSeekbarCounter = (TextView) findViewById(R.id.tvSeekbarCounter);
        tvIndividualAmount =(TextView) findViewById(R.id.tvIndividualAmount);
    }

    private void calculateIndividualShare(){
                try {
                    BillAmount = Float.valueOf(etBillValue.getText().toString());
                    IndividualShare = BillAmount / sbNoPeople.getProgress();
                    tvIndividualAmount.setText("Rs." + String.format("%.2f", IndividualShare));
                }catch (NumberFormatException ne){
                    tvIndividualAmount.setText("");
                }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splitter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_night_theme_off:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                return true;
            case R.id.action_night_theme_on:
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
