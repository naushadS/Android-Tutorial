package in.naushad.androidtutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class ListingMenu extends AppCompatActivity {


    public String classes[] = {"Incrementor/Decrementor", "Android Police (Web View)", "Android Police (Chrome Custom Tab)", "TextPlay",
            "example5","example6","example7","example8","example9","example10","example11",
            "example12","example13","example14","example15","example16","example17","example18",
            "example19","example20" +
            ""};
    ListView lvListingMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_menu);


        /* Loading the banner ad in listing menu*/
        AdView avListingMenu = (AdView) findViewById(R.id.avListingMenu);
        AdRequest adRequestListingMenu = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                .addTestDevice("A851D03B6D976CAA2BDAABFC232841DC")  // My Xiaomi Redmi 1s test phone
                .build();
        avListingMenu.loadAd(adRequestListingMenu);


        /* Populating List View*/
        ArrayAdapter<String> naushadAdapter=new ArrayAdapter<String>(ListingMenu.this, android.R.layout.simple_list_item_1, classes);
        lvListingMenu = (ListView) findViewById(R.id.lvListingMenu);
        lvListingMenu.setAdapter(naushadAdapter);


        /* OnItemClickListener for List Items*/
        lvListingMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cheese=classes[position];
                if (cheese == "Incrementor/Decrementor") {
                    cheese="startingPoint";
                } else if (cheese == "Android Police (Web View)") {
                    cheese = "webView";
                } else if (cheese == "Android Police (Chrome Custom Tab)") {
                    cheese = "chromeCustomTabActivity";
                }
                Class ourClass = null;
                try {
                    ourClass = Class.forName("in.naushad.androidtutorial."+cheese);
                    Intent ourIntent = new Intent(ListingMenu.this,ourClass);
                    startActivity(ourIntent);
                } catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String cheese=classes[position];
        Class ourClass = null;
        try {
            ourClass = Class.forName("in.naushad.androidtutorial."+cheese);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Intent ourIntent = new Intent(ListingMenu.this,ourClass);
            startActivity(ourIntent);
        }
    } */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listing_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(),"You have pressed Settings!",Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_About) {
            Toast.makeText(getApplicationContext(),"You have pressed About!",Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
