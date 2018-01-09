package sttnf.app.pemira.core.overview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import sttnf.app.pemira.model.Login;


/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

interface OverviewView {
    void nimCanged(String s);
    AppCompatActivity context();
    void showMessage(int code, String message);
}
