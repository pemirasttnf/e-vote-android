package sttnf.app.pemira.core.overview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class OverviewActivity extends BaseActivity<OverviewPresenter> implements OverviewView {

    @BindView(R.id.layout_caution) LinearLayout layoutCaution;
    @BindView(R.id.txt_caution) TextView txtCaution;
    @BindView(R.id.edt_nim) EditText edtNim;

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
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.length() == 0) layoutCaution.setVisibility(View.GONE);
            }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count < 10) {
                    layoutCaution.setVisibility(View.VISIBLE);
                    txtCaution.setText("NIM anda tidak terdaftar");
                } else {
                    layoutCaution.setVisibility(View.GONE);
                }
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void isValidNim(String nim) {
        
    }

}
