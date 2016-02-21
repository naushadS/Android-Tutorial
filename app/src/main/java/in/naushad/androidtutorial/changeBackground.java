package in.naushad.androidtutorial;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class changeBackground extends AppCompatActivity implements View.OnClickListener{

    Button btRed,btGreen,btBlue;
    RelativeLayout rlChangeBackgroundLayout;
    FloatingActionButton fabMaterial;
    private Toolbar tbChangeBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        bindXML();

        setSupportActionBar(tbChangeBackground);
        getSupportActionBar().setTitle("Change Background");
        getSupportActionBar().setSubtitle("CSI Android Task(2)");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        btRed.setOnClickListener(this);
        btGreen.setOnClickListener(this);
        btBlue.setOnClickListener(this);
        rlChangeBackgroundLayout.setOnClickListener(this);
        fabMaterial.setOnClickListener(this);
    }

    private void bindXML() {
        tbChangeBackground = (Toolbar) findViewById(R.id.tbChangeBackground);
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
}
