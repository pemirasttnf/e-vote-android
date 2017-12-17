package sttnf.app.pemira.base;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import io.isfaaghyth.rak.Rak;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

abstract public class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P presenter;
    protected abstract P initPresenter();

    protected void binding(int layout) {
        setContentView(layout);
        ButterKnife.bind(this);
        Rak.initialize(this);
        presenter = initPresenter();
    }

    protected void Toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
