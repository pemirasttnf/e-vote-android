package sttnf.app.pemira.util;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by isfaaghyth on 12/29/17.
 * github: @isfaaghyth
 */

public class StartActivities {

    public static void start(Activity currentActivity, Class<? extends Activity> newTopActivityClass) {
        Intent intent = new Intent(currentActivity, newTopActivityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }
    
}
