package in.naushad.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by Naushad on 19-Sep-15.
 */
public class Splash extends Activity{

    public MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        ourSong=MediaPlayer.create(Splash.this,R.raw.splashsound);
        ourSong.start();
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(4000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally{
                    Intent openListingMenu=new Intent("in.naushad.androidtutorial.LISTINGMENU");
                  startActivity(openListingMenu);

                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ourSong.release();
        finish();
    }
}
