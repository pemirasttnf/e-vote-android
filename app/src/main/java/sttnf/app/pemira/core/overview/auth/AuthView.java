package sttnf.app.pemira.core.overview.auth;

import android.content.Context;

import sttnf.app.pemira.model.Login;

/**
 * Created by isfaaghyth on 1/10/18.
 * github: @isfaaghyth
 */

public interface AuthView {
    void onSuccess(Login res);
    void onError(String err);
    Context context();
}
