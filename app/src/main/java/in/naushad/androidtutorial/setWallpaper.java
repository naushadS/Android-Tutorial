package in.naushad.androidtutorial;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class setWallpaper extends AppCompatActivity implements View.OnClickListener{

    final static int cameraData=1;
    CoordinatorLayout clSetWallpaper;
    Toolbar tbSetWallpaper;
    ImageView ivSetWallpaper;
    Button btClickPicture,btSetWallpaper;
    Intent clickPicture;
    Bitmap bmp;
    private WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);

        bindXML();

        setSupportActionBar(tbSetWallpaper);
        getSupportActionBar().setTitle("Set Wallpaper");
        getSupportActionBar().setSubtitle("Click a pic & set it as wallpaper");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        initialize();

        btSetWallpaper.setEnabled(false);

        btClickPicture.setOnClickListener(this);
        btSetWallpaper.setOnClickListener(this);


    }

    private void bindXML(){
        clSetWallpaper =(CoordinatorLayout) findViewById(R.id.clSetWallpaper);
        tbSetWallpaper = (Toolbar) findViewById(R.id.tbSetWallpaper);
        ivSetWallpaper = (ImageView) findViewById(R.id.ivSetWallpaper);
        btClickPicture = (Button) findViewById(R.id.btClickPicture);
        btSetWallpaper = (Button) findViewById(R.id.btSetWallpaper);
    }
    private void initialize(){
        wallpaperManager=WallpaperManager.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.btClickPicture:
                clickPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                ivSetWallpaper.setBackgroundColor(Color.WHITE);
                startActivityForResult(clickPicture, cameraData);
                break;

            case R.id.btSetWallpaper:
                try {
                    if(bmp!= null){
                        wallpaperManager.setBitmap(bmp);
                    }
                    Snackbar.make(clSetWallpaper ,"Wallpaper Set Successfully",Snackbar.LENGTH_LONG).show();
                    btSetWallpaper.setEnabled(false);
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;

            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode){

            case RESULT_OK:
                Bundle extras = data.getExtras();
                bmp= (Bitmap) extras.get("data");
                ivSetWallpaper.setImageBitmap(bmp);

                btClickPicture.setEnabled(false);
                btSetWallpaper.setEnabled(true);
                break;

            case RESULT_CANCELED:
                Toast.makeText(setWallpaper.this, "Image capture was cancelled", Toast.LENGTH_LONG).show();
                break;

            default:
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
