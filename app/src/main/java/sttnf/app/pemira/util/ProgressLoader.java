package sttnf.app.pemira.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by isfaaghyth on 12/22/17.
 * github: @isfaaghyth
 */

public class ProgressLoader {
    private ProgressDialog mProgressDialog;
    private Context context;

    public ProgressLoader(Context context) {
        this.context = context;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Silahkan Tunggu...");
    }

    public void show(){
        mProgressDialog.show();
    }

    public void hide(){
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
