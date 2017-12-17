package sttnf.app.pemira.core.overview;

import com.google.firebase.database.DatabaseReference;


/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

interface OverviewView {
    void onSuccess(boolean isSuccess);
    DatabaseReference dbRef();
    void onError(String err);
}
