package sttnf.app.pemira.core.overview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.isfaaghyth.rak.Rak;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.model.Login;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class OverviewActivity extends BaseActivity<OverviewPresenter> implements OverviewView {

    @BindView(R.id.layout_caution) LinearLayout layoutCaution;
    @BindView(R.id.btn_login) FloatingActionButton btnLogin;
    @BindView(R.id.txt_caution) TextView txtCaution;
    @BindView(R.id.edt_nim) EditText edtNim;

    @BindView(R.id.layout_prodi) RelativeLayout layoutProdi;
    @BindView(R.id.card_prodi) CardView cardProdi;
    @BindView(R.id.txt_prodi) TextView txtProdi;

    private boolean isTogglePassword;
    private AlertDialog adPassword;

    @Override protected OverviewPresenter initPresenter() {
        return new OverviewPresenter(this);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_overview);
        adPassword = new AlertDialog.Builder(this).create();
        edtNim.addTextChangedListener(presenter.nimWatch());
        btnLogin.setOnClickListener(v -> login());
        isFinish();
    }

    private void isFinish() {
        int finishCode = getIntent().getIntExtra("finish", 0);
        if (finishCode == 200) {
            showMessage(200, "Terima kasih\nsudah menggunakan\nhak suara anda.");
        } else if (finishCode == 403) {
            showMessage(200, "Mohon Maaf!\nanda sudah voting sebelumnya\nsilahkan tunggu hasil akhir ya sis.");
        }
    }

    private void showMessage(int code, String message) {
        View dialogFinish = LayoutInflater.from(this).inflate(R.layout.dialog_finish, null);
        ImageView imgIcon = ButterKnife.findById(dialogFinish, R.id.img_ic);
        TextView txtMessage = ButterKnife.findById(dialogFinish, R.id.txt_message);
        if (code == 200) {
            imgIcon.setImageResource(R.mipmap.ic_check);
        } else {
            imgIcon.setImageResource(R.mipmap.ic_caution);
        }
        txtMessage.setText(message);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(dialogFinish)
                .setCancelable(false)
                .show();
        dialog.create();
        new Handler().postDelayed(dialog::dismiss, 10000);
    }

    public void nimCanged(String s) {
        checkProdi(s);
        if (s.length() < 10) {
            validationNim(View.VISIBLE, View.GONE);
            showCaution("NIM anda tidak sesuai dengan format.");
        } else {
            validationNim(View.GONE, View.VISIBLE);
        }
    }

    private void validationNim(int... v) {
        layoutCaution.setVisibility(v[0]);
        btnLogin.setVisibility(v[1]);
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
        View passwordLayout = LayoutInflater.from(this).inflate(R.layout.dialog_password_require, null);
        adPassword.setTitle("Masukkan password anda");
        final EditText edtPassword = ButterKnife.findById(passwordLayout, R.id.edt_password);
        Button btnSubmit = ButterKnife.findById(passwordLayout, R.id.btn_submit);
        Button btnShowHide = ButterKnife.findById(passwordLayout, R.id.btn_pass_toggle);
        btnSubmit.setOnClickListener(v -> {
            presenter.doLogin(edtNim.getText().toString(), edtPassword.getText().toString().trim());
            edtPassword.setText("");
            edtNim.setText("");
            loader.show();
        });
        btnShowHide.setOnClickListener(v -> {
            if (isTogglePassword) {
                isTogglePassword = false;
                btnShowHide.setText("Show");
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                edtPassword.setSelection(edtPassword.length());
            } else {
                isTogglePassword = true;
                btnShowHide.setText("Hide");
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                edtPassword.setSelection(edtPassword.length());
            }
        });
        adPassword.setView(passwordLayout);
        adPassword.show();
    }

    @Override public void onSuccess(Login res) {
        loader.hide();
        adPassword.cancel();
        Rak.entry("nim", res.getData().getNim());
        Rak.entry("token", res.getSecretToken());
        Rak.entry("nama", res.getData().getName());
        Rak.entry("avatar", res.getData().getAvatar());
        Rak.entry("prodi", res.getData().getProgramStudi());
        Rak.entry("statusMahasiswa", res.getData().getStatus());
        Rak.entry("tahunAngkatan", res.getData().getTahunAngkatan());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override public void onError(String err) {
        loader.hide();
        adPassword.cancel();
        showCaution(err);
    }
}
