package in.naushad.androidtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class changeBackground extends AppCompatActivity implements View.OnClickListener{

    Button btRed,btGreen,btBlue;
    RelativeLayout rlChangeBackgroundLayout;
    FloatingActionButton fabMaterial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        bindXML();

        btRed.setOnClickListener(this);
        btGreen.setOnClickListener(this);
        btBlue.setOnClickListener(this);
        rlChangeBackgroundLayout.setOnClickListener(this);
        fabMaterial.setOnClickListener(this);
    }

    private void bindXML() {
        btRed = (Button) findViewById(R.id.bt_changeBackgroundRed);
        btGreen = (Button) findViewById(R.id.bt_changeBackgroundGreen);
        btBlue = (Button) findViewById(R.id.bt_changeBackgroundBlue);
        rlChangeBackgroundLayout = (RelativeLayout) findViewById(R.id.rl_changeBackgroundLayout);
        fabMaterial=(FloatingActionButton)findViewById(R.id.fab_changeBackgroundMaterial);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_changeBackgroundRed:
                rlChangeBackgroundLayout.setBackgroundColor(Color.RED);
                Toast.makeText(changeBackground.this,"RED was selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_changeBackgroundGreen:
                rlChangeBackgroundLayout.setBackgroundColor(Color.GREEN);
                Toast.makeText(changeBackground.this,"GREEN was selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_changeBackgroundBlue:
                rlChangeBackgroundLayout.setBackgroundColor(Color.BLUE);
                Toast.makeText(changeBackground.this,"BLUE was selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.rl_changeBackgroundLayout:
                rlChangeBackgroundLayout.setBackgroundColor(Color.WHITE);
                Toast.makeText(changeBackground.this,"DEFAULT was selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_changeBackgroundMaterial:
                rlChangeBackgroundLayout.setBackgroundColor(Color.CYAN);
                Toast.makeText(changeBackground.this,"MATERIAL was selected",Toast.LENGTH_SHORT).show();
                break;


        }
    }
}
