package sttnf.app.pemira.core.overview;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import retrofit2.Response;
import rx.Subscriber;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Login;
import sttnf.app.pemira.util.Conts;

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
        Log.d("TAG", nim+":"+password);
        onSubscribe(service.doLogin(Conts.INFONF_TOKEN, nim, password), new Subscriber<Response<Login>>() {
            @Override public void onNext(Response<Login> res) {
                Log.e("TAG", res.toString());
                if (res.code() == 200) {
                    view.onSuccess(res.body());
                } else if (res.code() == 401) {
                    view.onError("Periksa kembali NIM atau sandi anda.");
                } else if (res.code() == 403) {
                    view.onError("Maaf, anda sudah voting sebelumnya.\n" +
                            "Terima kasih sudah menggunakan hak suara anda.");
                }
            }
            @Override public void onError(Throwable e) {
                Log.e("TAG e()", e.getMessage());
                view.onError(e.getMessage());
            }
            @Override public void onCompleted() {
                stop();
            }
        });
    }

}
