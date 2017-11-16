package sttnf.app.pemira.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

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
        presenter = initPresenter();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
