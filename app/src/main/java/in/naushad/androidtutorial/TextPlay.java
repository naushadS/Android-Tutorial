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

/**
 * Created by Naushad on 25-Sep-15.
 */
public class TextPlay extends AppCompatActivity {

    EditText etCommand;
    Button bResults;
    ToggleButton tbPassword;
    TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textplay);
        bindXMLView();
        onClickListeners();
    }


    private void bindXMLView() {
        etCommand = (EditText) findViewById(R.id.etCommand);
        bResults = (Button) findViewById(R.id.bResults);
        tbPassword = (ToggleButton) findViewById(R.id.tbPassword);
        tvResults = (TextView) findViewById(R.id.tvResults);

    }

    private void onClickListeners() {

        tbPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbPassword.isChecked()) {
                    etCommand.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    etCommand.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        bResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                } else if (mac.contentEquals("WTF")) {

                } else {
                    tvResults.setText("Invalid");
                    tvResults.setGravity(Gravity.CENTER);
                }

            }
        });

    }
}
