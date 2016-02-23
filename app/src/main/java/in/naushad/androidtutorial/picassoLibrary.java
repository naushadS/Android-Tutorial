package in.naushad.androidtutorial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class picassoLibrary extends AppCompatActivity{
    ImageView ivPicasso1;
    TextView tvPicassoDesc;
    Toolbar tbPicasso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_picasso_library);

        bindXML();

        setSupportActionBar(tbPicasso);
        getSupportActionBar().setTitle("Picasso (Library)");
        getSupportActionBar().setSubtitle("Image Loading & Caching");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);


        Picasso.
                with(this).setIndicatorsEnabled(true);

        Picasso
                .with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                //.placeholder(R.drawable.splash_background)
                //.error(R.drawable.splash_background)
                .into(ivPicasso1);
        tvPicassoDesc.append("\n"
                +"Red = Image Source(Network)"
                +"\n"
                +"Blue = Image Source(Disk)"
                +"\n"
                +"Green = Image Source(Cache)");




    }

    private  void bindXML(){
        tbPicasso = (Toolbar) findViewById(R.id.tbPicasso);
        ivPicasso1 = (ImageView) findViewById(R.id.ivPicasso1);
        tvPicassoDesc=(TextView)findViewById(R.id.tvPicassoDesc);
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
