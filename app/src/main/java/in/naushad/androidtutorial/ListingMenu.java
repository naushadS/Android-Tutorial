package in.naushad.androidtutorial;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class ListingMenu extends AppCompatActivity {


    public String classes[] = {"Incrementor/Decrementor", "Android Police (Web View)", "Android Police (Chrome Custom Tab)",
            "Text Play", "Image Capture", "Change Layout Background", "Picasso Library","Email the Developer!","Set a New Wallpaper!",
            "Pass String between Activities","Settings"};
    ListView lvListingMenu;
    Toolbar tbListingMenu;
    private Intent drawerintent = null;
    private Drawer result = null;
    private AccountHeader headerResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_menu);


            tbListingMenu = (Toolbar) findViewById(R.id.tbListingMenu);
            setSupportActionBar(tbListingMenu);
            getSupportActionBar().setTitle("List Of Options");
            getSupportActionBar().setSubtitle("Select One Already !");

        /*
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);
 */
            //show back arrow
            //result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(false);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //show hamburger icon
           //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            //result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);


             SharedPreferences getPrefs=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
             String user_name = getPrefs.getString("user_name", "Naushad Shukoor");
             String user_email = getPrefs.getString("user_email", "naushadshukoor@gmail.com");


            //Account Header for Material Drawer
            headerResult = new AccountHeaderBuilder()
                    .withActivity(this)
                    .withHeaderBackground(R.drawable.header)
                    .addProfiles(
                            new ProfileDrawerItem()
                                    .withName(user_name)
                                    .withEmail(user_email)
                                    .withIcon(getResources().getDrawable(R.drawable.profile3))
                    )
                    .withSelectionListEnabled(false)
                    .withProfileImagesClickable(false)
                    .build();


        final PrimaryDrawerItem email = new PrimaryDrawerItem()
                .withIcon(MaterialDesignIconic.Icon.gmi_email_open)
                .withName("Email the Developer!")
                .withDescription("Send a pre-formatted mail")
                .withIdentifier(1)
                //.withBadge("1")
                //.withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.Black))
                .withSelectable(false);

        final PrimaryDrawerItem settings = new PrimaryDrawerItem()
                .withIcon(MaterialDesignIconic.Icon.gmi_settings)
                .withName("Settings")
                .withIdentifier(2)
                        //.withBadge("2")
                        //.withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.Black))
                .withSelectable(false);

        final PrimaryDrawerItem about = new PrimaryDrawerItem()
                .withIcon(MaterialDesignIconic.Icon.gmi_info)
                .withName("About")
                .withDescription("Get a brief idea about this app")
                .withIdentifier(3)
                //.withBadge("2")
                //.withBadgeStyle(new BadgeStyle().withTextColor(Color.WHITE).withColorRes(R.color.Black))
                .withSelectable(false);

        final DividerDrawerItem divider = new DividerDrawerItem();

            //Material Drawer for listing menu activity
            result = new DrawerBuilder()
                    .withActivity(this)
                    .withToolbar(tbListingMenu)
                    .withTranslucentStatusBar(true)
                    .withTranslucentNavigationBar(false)
                    .withFullscreen(false)
                    //.withSliderBackgroundColor(getResources().getColor(R.color.material_drawer_dark_background))
                    .withSelectedItem(-1)
                    .withActionBarDrawerToggle(true)
                    .withAccountHeader(headerResult)
                    .addDrawerItems(email,divider,settings,about)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (drawerItem != null) {
                                drawerintent = null;
                                if (drawerItem.getIdentifier() == 1) {
                                    drawerintent = new Intent(ListingMenu.this, Email_Dev.class);
                                } else if (drawerItem.getIdentifier() == 2) {
                                    drawerintent = new Intent(ListingMenu.this, settings.class);
                                }else if (drawerItem.getIdentifier() == 3) {
                                    drawerintent = new Intent(ListingMenu.this, about.class);
                                }
                            }
                            if (drawerintent != null) {
                                startActivity(drawerintent);
                            }
                            return false;
                        }
                    })
                    .build();


            // Loading the banner ad in listing menu
            AdView avListingMenu = (AdView) findViewById(R.id.avListingMenu);
            AdRequest adRequestListingMenu = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
                    .addTestDevice("A851D03B6D976CAA2BDAABFC232841DC")  // My Xiaomi Redmi 1s test phone
                    //.addTestDevice("") //add the tablet as well later
                    .build();
            avListingMenu.loadAd(adRequestListingMenu);


            // Populating List View
            ArrayAdapter<String> naushadAdapter = new ArrayAdapter<String>(ListingMenu.this, android.R.layout.simple_list_item_1, classes);
            lvListingMenu = (ListView) findViewById(R.id.lvListingMenu);
            lvListingMenu.setAdapter(naushadAdapter);


            // OnItemClickListener for List Items
            lvListingMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String cheese = classes[position];

                    //converting display name to class name
                    switch (cheese) {
                        case "Incrementor/Decrementor":
                            cheese = "startingPoint";
                            break;
                        case "Android Police (Web View)":
                            cheese = "webView";
                            break;
                        case "Android Police (Chrome Custom Tab)":
                            cheese = "chromeCustomTabActivity";
                            break;
                        case "Text Play":
                            cheese = "TextPlay";
                            break;
                        case "Image Capture":
                            cheese = "camera";
                            break;
                        case "Change Layout Background":
                            cheese = "changeBackground";
                            break;
                        case "Picasso Library":
                            cheese = "picassoLibrary";
                            break;
                        case "Email the Developer!":
                            cheese = "Email_Dev";
                            break;
                        case "Set a New Wallpaper!":
                            cheese = "setWallpaper";
                            break;
                        case "Pass String between Activities":
                            cheese = "sendBundle";
                            break;
                        case "Settings":
                            cheese= "settings";
                            break;
                    }

                    //redirecting the registered click by user to the appropriate class
                    Class ourClass = null;
                    try {
                        ourClass = Class.forName("in.naushad.androidtutorial." + cheese);
                        Intent ourIntent = new Intent(ListingMenu.this, ourClass);
                        startActivity(ourIntent);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

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
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.action_email_dev:
                startActivity( new Intent(ListingMenu.this, Email_Dev.class));
                return true;

            case R.id.action_settings:
                startActivity(new Intent(ListingMenu.this, settings.class));
                return true;

            case R.id.action_about:
                startActivity(new Intent(ListingMenu.this, about.class));
                return true;

            case R.id.action_exit:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}