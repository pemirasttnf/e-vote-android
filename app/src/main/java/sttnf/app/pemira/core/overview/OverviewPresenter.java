package sttnf.app.pemira.core.overview;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.HashMap;

import io.isfaaghyth.rak.Rak;
import retrofit2.Response;
import rx.Observer;
import rx.Subscriber;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Login;
import sttnf.app.pemira.model.Mahasiswa;
import sttnf.app.pemira.util.Conts;
import sttnf.app.pemira.util.RxFirebase;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

class OverviewPresenter extends BasePresenter<OverviewView> {

    OverviewPresenter(OverviewView view) {
        super.attachView(view);
    }

    String checkPrefixProdi(String str) {
        return str.length() < 5 ? str : str.substring(0, 5);
    }

    TextWatcher nimWatch() {
        return new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                view.nimCanged(s.toString());
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                view.nimCanged(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {
                view.nimCanged(s.toString());
            }
        };
    }

    /**
     * Untuk narik data dari https://info.nurulfikri.ac.id/sisfo/api/user/
     * @param nim
     * @param password
     */
    void doLogin(String nim, String password) {
        onSubscribe(service.doLogin(Conts.INFONF_TOKEN, nim, password), new Subscriber<Response<Login>>() {
            @Override public void onNext(Response<Login> res) {
                if (res.isSuccessful()) {
                    view.onSuccess(res.body());
                }
            }
            @Override public void onError(Throwable e) {
                view.onError(e.getMessage());
            }
            @Override public void onCompleted() {}
        });
    }

}
