package in.naushad.androidtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class sendBundle extends AppCompatActivity implements View.OnClickListener{
    private Toolbar tbSendBundle;
    TextView tvSendBundle;
    RadioGroup rgSelectString;
    Button bSendString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_bundle);

        bindXML();

        setSupportActionBar(tbSendBundle);
        getSupportActionBar().setTitle("Send Bundle");
        getSupportActionBar().setSubtitle("Send Text to next activity");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        bSendString.setOnClickListener(this);



    }

    private void bindXML(){
        tbSendBundle = (Toolbar) findViewById(R.id.tbSendBundle);
        tvSendBundle = (TextView) findViewById(R.id.tvSendBundle);
        rgSelectString = (RadioGroup) findViewById(R.id.rgSelectString);
        bSendString =(Button) findViewById(R.id.bSendString);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSendString:
                RadioButton selectedRB = (RadioButton) findViewById(rgSelectString.getCheckedRadioButtonId());
                Bundle basket = new Bundle();
                basket.putString("itemToBuy", selectedRB.getText().toString());
                Intent person = new Intent(sendBundle.this,receiveBundle.class);
                person.putExtras(basket);
                startActivityForResult(person, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle Acknowledgement=data.getExtras();
            Toast.makeText(sendBundle.this,"Item Bought:\n"+Acknowledgement.getString("itemBought"),Toast.LENGTH_LONG).show();
        }
    }
}
