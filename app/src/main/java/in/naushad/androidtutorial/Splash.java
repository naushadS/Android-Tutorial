package in.naushad.androidtutorial;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by Naushad on 19-Sep-15.
 */
public class Splash extends Activity{

    public MediaPlayer ourSong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound);

        final SharedPreferences getPrefs=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        if(getPrefs.getBoolean("splash_screen_enabled",true)){

        if(getPrefs.getBoolean("splash_music_enabled",true)) {
            ourSong.start();
        }
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(Integer.valueOf(getPrefs.getString("splash_screen_duration","2000")));
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally{
                    startActivity(new Intent(Splash.this, ListingMenu.class));
                }
            }
        };
        timer.start();
    } else{

            startActivity(new Intent(Splash.this, ListingMenu.class));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        ourSong.release();
        finish();
    }
}
