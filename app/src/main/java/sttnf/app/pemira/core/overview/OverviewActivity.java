package sttnf.app.pemira.core.overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;
import sttnf.app.pemira.core.overview.auth.AuthDialog;
import sttnf.app.pemira.core.overview.finish.FinishDialog;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class OverviewActivity extends BaseActivity<OverviewPresenter> implements OverviewView {

    @BindView(R.id.layout_caution) LinearLayout layoutCaution;
    @BindView(R.id.btn_login) FloatingActionButton btnLogin;
    @BindView(R.id.layout_prodi) RelativeLayout layoutProdi;
    @BindView(R.id.txt_caution) TextView txtCaution;
    @BindView(R.id.card_prodi) CardView cardProdi;
    @BindView(R.id.txt_prodi) TextView txtProdi;
    @BindView(R.id.edt_nim) EditText edtNim;

    @Override protected OverviewPresenter initPresenter() {
        return new OverviewPresenter(this);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_overview);
        edtNim.addTextChangedListener(presenter.nimWatch());
        btnLogin.setOnClickListener(v -> login());
        presenter.isFinish();
    }

    private void login() {
        AuthDialog finish = new AuthDialog();
        Bundle bundle = new Bundle();
        bundle.putString("nim", edtNim.getText().toString().trim());
        finish.setArguments(bundle);
        finish.show(getSupportFragmentManager(), "Finish");
    }

    public void nimCanged(String s) {
        checkProdi(s);
        if (s.length() < 10) {
            presenter.validationNim(layoutCaution, btnLogin, View.VISIBLE, View.GONE);
            presenter.showCaution(layoutCaution, txtCaution, "NIM anda tidak sesuai dengan format.");
        } else {
            presenter.validationNim(layoutCaution, btnLogin, View.GONE, View.VISIBLE);
        }
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

    @Override public AppCompatActivity context() {
        return this;
    }

    private void showProdiLabel(String prodi, int prodiColor) {
        cardProdi.setCardBackgroundColor(ContextCompat.getColor(this, prodiColor));
        layoutProdi.setVisibility(View.VISIBLE);
        txtProdi.setText(prodi);
    }

    public void onError(String err) {
        presenter.showCaution(layoutCaution, txtCaution, err);
    }

    @Override public void showMessage(int code, String message) {
        FinishDialog finish = new FinishDialog();
        Bundle bundle = new Bundle();
        bundle.putString("message", message);
        bundle.putInt("code", code);
        finish.setArguments(bundle);
        finish.show(getSupportFragmentManager(), "Finish");
    }
}
