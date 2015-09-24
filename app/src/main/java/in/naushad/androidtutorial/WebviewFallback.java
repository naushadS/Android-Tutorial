package in.naushad.androidtutorial;

/**
 * Created by Naushad on 24-Sep-15.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * A Fallback that opens a Webview when Custom Tabs is not available
 */
public class WebviewFallback implements CustomTabActivityHelper.CustomTabFallback {
    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, webViewCCTalternative.class);
        //intent.putExtra(webViewCCTalternative.EXTRA_URL, uri.toString());
        activity.startActivity(intent);
    }
}