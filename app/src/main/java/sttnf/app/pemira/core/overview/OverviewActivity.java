package sttnf.app.pemira.core.overview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.model.Login;
import sttnf.app.pemira.util.Conts;
import sttnf.app.pemira.util.RxFirebase;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class OverviewActivity extends BaseActivity<OverviewPresenter> implements OverviewView {

    @BindView(R.id.layout_caution) LinearLayout layoutCaution;
    @BindView(R.id.txt_caution) TextView txtCaution;
    @BindView(R.id.edt_nim) EditText edtNim;

    @BindView(R.id.layout_prodi) RelativeLayout layoutProdi;
    @BindView(R.id.card_prodi) CardView cardProdi;
    @BindView(R.id.txt_prodi) TextView txtProdi;

    @Override protected OverviewPresenter initPresenter() {
        return new OverviewPresenter(this);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_overview);
        edtNim.setText("0110215046");
        checkNim();

        findViewById(R.id.test).setOnClickListener(v -> presenter.testLogin());
    }

    private void checkNim() {
        edtNim.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nimCanged(s.toString());
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                nimCanged(s.toString());
            }
            @Override public void afterTextChanged(Editable s) {
                nimCanged(s.toString());
            }
        });
    }

    private void nimCanged(String s) {
        checkProdi(s);
        isValidNim(s.length());
    }

    private void isValidNim(int count) {
        if (count < 10) {
            showCaution("NIM anda tidak sesuai dengan format.");
        } else {
            layoutCaution.setVisibility(View.GONE);
            login();
        }
    }

    private void showCaution(String message) {
        layoutCaution.setVisibility(View.VISIBLE);
        txtCaution.setText(message);
    }

    private void checkProdi(String nim) {
        if (presenter.checkPrefixProdi(nim).equals(getString(R.string.prefix_si))) {
            showProdiLabel(getString(R.string.prodi_si), R.color.colorCaution);
        } else if (presenter.checkPrefixProdi(nim).equals(getString(R.string.prefix_ti))) {
            showProdiLabel(getString(R.string.prodi_ti), R.color.colorPrimary);
        } else {
            layoutProdi.setVisibility(View.GONE);
        }
    }

    private void showProdiLabel(String prodi, int prodiColor) {
        cardProdi.setCardBackgroundColor(ContextCompat.getColor(this, prodiColor));
        layoutProdi.setVisibility(View.VISIBLE);
        txtProdi.setText(prodi);
    }

    private void login() {
        final String nim = edtNim.getText().toString();
        final AlertDialog adPassword = new AlertDialog.Builder(this).create();
        @SuppressLint("InflateParams") View passwordLayout = LayoutInflater
                .from(this)
                .inflate(R.layout.dialog_password_require, null);
        adPassword.setTitle("Masukkan password anda");
        final EditText edtPassword = ButterKnife.findById(passwordLayout, R.id.edt_password);
        Button btnSubmit = ButterKnife.findById(passwordLayout, R.id.btn_submit);
        btnSubmit.setOnClickListener(v -> {
            presenter.doLogin(nim, edtPassword.getText().toString().trim());
            adPassword.dismiss();
        });
        adPassword.setView(passwordLayout);
        adPassword.show();
    }

    @Override public void onSuccess(boolean isSuccess) {
        if (isSuccess) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            onError("Periksa kembal");
        }
    }

    @Override public void onError(String err) {
        showCaution(err);
    }
}
