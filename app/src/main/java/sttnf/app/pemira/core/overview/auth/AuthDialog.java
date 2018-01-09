package sttnf.app.pemira.core.overview.auth;

import android.content.Context;
import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseDialogFragment;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.core.overview.OverviewActivity;
import sttnf.app.pemira.model.Login;

/**
 * Created by isfaaghyth on 1/10/18.
 * github: @isfaaghyth
 */

public class AuthDialog extends BaseDialogFragment<AuthPresenter> implements AuthView {

    @BindView(R.id.btn_pass_toggle) TextView btnToggle;
    @BindView(R.id.edt_password) EditText edtPassword;
    @BindView(R.id.edt_unique) EditText edtUnique;

    private boolean isTogglePassword;
    private String nim; //nomor induk mahasiswa
    public AuthDialog() {}

    @Override protected AuthPresenter initPresenter() {
        return new AuthPresenter(this);
    }

    @Override protected int contentView() {
        return R.layout.dialog_password_require;
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        nim = getArguments().getString("nim");
    }

    @OnClick(R.id.btn_submit)
    public void onSubmitClicked() {
        String password = edtPassword.getText().toString().trim();
        String unique = edtUnique.getText().toString().trim();
        onLoginClicked(password, unique);
    }

    @Override protected void viewCreated(View view) {
        passwordToggle();
    }

    private void onLoginClicked(String password, String unique) {
        if (!password.isEmpty()) {
            if (!unique.isEmpty()) {
                presenter.doLogin(nim, password + unique);
                loader.show();
            } else {
                Toast("Maaf, anda belum memasukkan kode unik");
            }
        } else {
            Toast("Silahkan masukkan kata sandi anda.");
        }
    }

    private void passwordToggle() {
        btnToggle.setOnClickListener(v -> {
            if (isTogglePassword) {
                isTogglePassword = false;
                btnToggle.setText("show");
                edtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                edtPassword.setSelection(edtPassword.length());
            } else {
                isTogglePassword = true;
                btnToggle.setText("hide");
                edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                edtPassword.setSelection(edtPassword.length());
            }
        });
    }

    @Override public void onSuccess(Login res) {
        loader.hide();
        presenter.onSaveProfile(res);
        startActivity(new Intent(getContext(), MainActivity.class));
        getActivity().finish();
    }

    @Override public void onError(String err) {
        ((OverviewActivity) getActivity()).onError(err);
        loader.hide();
        dismiss();
    }

    @Override public Context context() {
        return getContext();
    }

}
