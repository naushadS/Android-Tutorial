package in.naushad.androidtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class receiveBundle extends AppCompatActivity {
    Toolbar tbReceiveBundle;
    TextView tvReceivedText;
    Button bReturn;
    String item1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_bundle);

        tbReceiveBundle = (Toolbar) findViewById(R.id.tbReceiveBundle);
        tvReceivedText = (TextView) findViewById(R.id.tvReceivedText);
        bReturn =(Button) findViewById(R.id.bReturn);

        setSupportActionBar(tbReceiveBundle);
        getSupportActionBar().setTitle("Receive Bundle");

        //get bundle content from previous activity
        Bundle basket = getIntent().getExtras();
        item1=basket.getString("itemToBuy");
        //set the item name to textview
        tvReceivedText.append("\n"+item1);

        bReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buyer = new Intent();
                Bundle carryBag = new Bundle();
                carryBag.putString("itemBought",item1);
                buyer.putExtras(carryBag);
                setResult(RESULT_OK,buyer);
                finish();
            }
        });
    }



}
