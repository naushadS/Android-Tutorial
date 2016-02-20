package in.naushad.androidtutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class picassoLibrary extends AppCompatActivity {
    ImageView ivPicasso1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso_library);

        ivPicasso1 = (ImageView) findViewById(R.id.ivPicasso1);

        Picasso.
                with(this).setIndicatorsEnabled(true);

        Picasso
                .with(this)
                .load("http://i.imgur.com/DvpvklR.png")
                //.placeholder(R.drawable.splash_background)
                //.error(R.drawable.splash_background)
                .into(ivPicasso1);



    }
}
