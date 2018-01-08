package sttnf.app.pemira.core.overview;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import io.isfaaghyth.rak.Rak;
import retrofit2.Response;
import rx.Subscriber;
import sttnf.app.pemira.R;
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

    void validationNim(LinearLayout layoutCaution, FloatingActionButton btnLogin, int... v) {
        layoutCaution.setVisibility(v[0]);
        btnLogin.setVisibility(v[1]);
    }

    void showCaution(LinearLayout layoutCaution, TextView txtCaution, String message) {
        layoutCaution.setVisibility(View.VISIBLE);
        txtCaution.setText(message);
    }

    void onSaveProfile(Login res) {
        Rak.entry("nim", res.getData().getNim());
        Rak.entry("token", res.getSecretToken());
        Rak.entry("nama", res.getData().getName());
        Rak.entry("avatar", res.getData().getAvatar());
        Rak.entry("prodi", res.getData().getProgramStudi());
        Rak.entry("statusMahasiswa", res.getData().getStatus());
        Rak.entry("tahunAngkatan", res.getData().getTahunAngkatan());
    }

    /**
     * Untuk narik data dari https://info.nurulfikri.ac.id/sisfo/api/user/
     * @param nim
     * @param password
     */
    void doLogin(String nim, String password) {
        Context context = view.getContext();
        onSubscribe(service.doLogin(Conts.INFONF_TOKEN, nim, password), new Subscriber<Response<Login>>() {
            @Override public void onNext(Response<Login> res) {
                switch (res.code()) {
                    case 200:view.onSuccess(res.body());break;
                    case 401:view.onError(context.getString(R.string.status_401));break;
                    case 403:view.onError(context.getString(R.string.status_403));break;
                    default:view.onError(context.getString(R.string.status_conerr));
                }
            }
            @Override public void onError(Throwable e) {
                view.onError(context.getString(R.string.status_conerr));
            }
            @Override public void onCompleted() {
                stop();
            }
        });
    }

}
