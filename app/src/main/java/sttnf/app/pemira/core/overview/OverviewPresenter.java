package sttnf.app.pemira.core.overview;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

    private AppCompatActivity context;

    OverviewPresenter(OverviewView view) {
        super.attachView(view);
        context = view.context();
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

    void isFinish() {
        int finishCode = context.getIntent().getIntExtra("finish", 0);
        if (finishCode == 200) {
            view.showMessage(200, context.getString(R.string.thank_you));
        } else if (finishCode == 403) {
            view.showMessage(403, context.getString(R.string.permission_denied));
        }
    }

}
