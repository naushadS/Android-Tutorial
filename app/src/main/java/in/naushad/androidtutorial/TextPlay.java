package in.naushad.androidtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by Naushad on 25-Sep-15.
 */
public class TextPlay extends AppCompatActivity implements View.OnClickListener {

    EditText etCommand;
    Button bResults;
    ToggleButton tbPassword;
    TextView tvResults;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textplay);
        bindXMLView();
        tbPassword.setOnClickListener(this);
        bResults.setOnClickListener(this);
    }


    private void bindXMLView() {
        etCommand = (EditText) findViewById(R.id.etCommand);
        bResults = (Button) findViewById(R.id.bResults);
        tbPassword = (ToggleButton) findViewById(R.id.tbPassword);
        tvResults = (TextView) findViewById(R.id.tvResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tbPassword:
                if (tbPassword.isChecked()) {
                    etCommand.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    etCommand.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;

            case R.id.bResults:
                String mac = etCommand.getText().toString();
                tvResults.setText(mac);

                if (mac.contentEquals("left")) {
                    tvResults.setGravity(Gravity.LEFT);
                } else if (mac.contentEquals("right")) {
                    tvResults.setGravity(Gravity.RIGHT);
                } else if (mac.contentEquals("center")) {
                    tvResults.setGravity(Gravity.CENTER);
                } else if (mac.contentEquals("blue")) {
                    tvResults.setTextColor(Color.BLUE);
                } else if (mac.contentEquals("crazy")) {
                    Random crazy = new Random();
                    tvResults.setTextSize(crazy.nextInt(75));
                    tvResults.setTextColor(Color.rgb(crazy.nextInt(265), crazy.nextInt(265), crazy.nextInt(265)));
                    switch (crazy.nextInt(3)) {
                        case 0:
                            tvResults.setGravity(Gravity.LEFT);
                            break;
                        case 1:
                            tvResults.setGravity(Gravity.RIGHT);
                            break;
                        case 2:
                            tvResults.setGravity(Gravity.CENTER);
                            break;

                    }

                } else {
                    tvResults.setText("Invalid");
                    tvResults.setGravity(Gravity.CENTER);
                }
                break;
        }

    }

}
