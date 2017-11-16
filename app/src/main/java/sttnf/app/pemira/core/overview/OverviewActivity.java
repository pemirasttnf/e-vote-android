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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;
import sttnf.app.pemira.core.main.MainActivity;

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
        checkNim();
    }

    private void checkNim() {
        edtNim.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override public void afterTextChanged(Editable s) {
                checkProdi(s.toString());
                isValidNim(s.length());
            }
        });
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
        if (checkPrefixProdi(nim).equals(getString(R.string.prodi_si))) {
            cardProdi.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorCaution));
            showProdi("SI");
        } else if (checkPrefixProdi(nim).equals(getString(R.string.prodi_ti))) {
            cardProdi.setCardBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            showProdi("TI");
        } else {
            layoutProdi.setVisibility(View.GONE);
        }
    }

    private void showProdi(String prodi) {
        layoutProdi.setVisibility(View.VISIBLE);
        txtProdi.setText(prodi);
    }

    public String checkPrefixProdi(String str) {
        return str.length() < 5 ? str : str.substring(0, 5);
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

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (edtPassword.getText().toString().equals("123")) {
                    startActivity(new Intent(OverviewActivity.this, MainActivity.class));
                    finish();
                } else {
                    adPassword.dismiss();
                }
            }
        });

        adPassword.setView(passwordLayout);
        adPassword.show();
    }
}
