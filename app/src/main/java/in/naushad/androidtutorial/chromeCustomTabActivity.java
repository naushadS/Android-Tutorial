package in.naushad.androidtutorial;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class chromeCustomTabActivity extends AppCompatActivity {

    private static final String URL = "http://www.androidpolice.com/";
    private CheckBox mColorToolbarCheck, mShowTitleCheck, mCloseIconCheck,
            mActionBarIconCheck, mMenuItemsCheck, mCustomAnimationsCheck;
    private Button mLaunchWebSite;
    private Bitmap mActionButtonBitmap;
    private Bitmap mCloseButtonBitmap;
    private CustomTabActivityHelper mCustomTabActivityHelper;
    private Toolbar tbChromeCustomTab;
    /***
     * Use this method to make UI changes
     */
    private CustomTabActivityHelper.ConnectionCallback mConnectionCallback = new CustomTabActivityHelper.ConnectionCallback() {
        @Override
        public void onCustomTabsConnected() {
            Toast.makeText(getBaseContext(), "Service Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCustomTabsDisconnected() {
            Toast.makeText(getBaseContext(), "Service DisConnected", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrome_custom_tab);

        bindXMLView();


        setSupportActionBar(tbChromeCustomTab);
        getSupportActionBar().setTitle("Chrome Custom Tab");
        getSupportActionBar().setSubtitle("Loading/ed Android Police");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        setupCustomTabHelper();
        decodeBitmaps(this);

        mLaunchWebSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomTab();
            }
        });
    }

    private void bindXMLView() {
        tbChromeCustomTab = (Toolbar) findViewById(R.id.tbChromeCustomTab);
        mColorToolbarCheck = (CheckBox) findViewById(R.id.check_color_toolbar);
        mShowTitleCheck = (CheckBox) findViewById(R.id.check_show_title);
        mCloseIconCheck = (CheckBox) findViewById(R.id.check_close_icon);
        mActionBarIconCheck = (CheckBox) findViewById(R.id.check_action_bar_icon);
        mMenuItemsCheck = (CheckBox) findViewById(R.id.check_menu_items);
        mCustomAnimationsCheck = (CheckBox) findViewById(R.id.check_custom_animations);
        mLaunchWebSite = (Button) findViewById(R.id.button_launchSite);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCustomTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCustomTabActivityHelper.unbindCustomTabsService(this);
    }

    private void setupCustomTabHelper() {
        mCustomTabActivityHelper = new CustomTabActivityHelper();
        mCustomTabActivityHelper.setConnectionCallback(mConnectionCallback);
        mCustomTabActivityHelper.mayLaunchUrl(Uri.parse(URL), null, null);
    }

    private void decodeBitmaps(Context context) {
        mActionButtonBitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_share);
        mCloseButtonBitmap = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.ic_arrow_back);
    }

    private void openCustomTab() {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

        if (mColorToolbarCheck.isChecked()) {
            int color = getResources().getColor(R.color.primary);
            intentBuilder.setToolbarColor(color);
        }

        if (mShowTitleCheck.isChecked()) intentBuilder.setShowTitle(true);

        if (mMenuItemsCheck.isChecked()) {
            String menuItemTitle = getString(R.string.menu_title_share);
            PendingIntent menuItemPendingIntent = createPendingShareIntent();
            intentBuilder.addMenuItem(menuItemTitle, menuItemPendingIntent);
            String menuItemEmailTitle = getString(R.string.menu_title_email);
            PendingIntent menuItemPendingIntentTwo = createPendingEmailIntent();
            intentBuilder.addMenuItem(menuItemEmailTitle, menuItemPendingIntentTwo);
        }

        if (mCloseButtonBitmap != null && mCloseIconCheck.isChecked()) {
            intentBuilder.setCloseButtonIcon(mCloseButtonBitmap);
        }

        if (mActionButtonBitmap != null && mActionBarIconCheck.isChecked()) {
            intentBuilder.setActionButton(mActionButtonBitmap, getString(R.string.menu_title_share), createPendingShareIntent());
        }

        if (mCustomAnimationsCheck.isChecked()) {
            intentBuilder.setStartAnimations(this,
                    R.anim.slide_in_right, R.anim.slide_out_left);
            intentBuilder.setExitAnimations(this,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        }

        CustomTabActivityHelper.openCustomTab(
                this, intentBuilder.build(), Uri.parse(URL), new WebviewFallback());
    }

    private PendingIntent createPendingEmailIntent() {
        Intent emailIntent = new Intent(
                Intent.ACTION_SENDTO, Uri.fromParts("mailto", "naushadshukoor@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "I'm loving Chrome Custom Tab");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "This is really great");
        return PendingIntent.getActivity(getApplicationContext(), 0, emailIntent, 0);
    }

    private PendingIntent createPendingShareIntent() {
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("text/plain");
        actionIntent.putExtra(Intent.EXTRA_TEXT, "This is from Chrome Custom Tab");
        return PendingIntent.getActivity(getApplicationContext(), 0, actionIntent, 0);
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