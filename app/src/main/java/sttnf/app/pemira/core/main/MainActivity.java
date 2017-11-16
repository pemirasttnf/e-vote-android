package sttnf.app.pemira.core.main;

import android.os.Bundle;

import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @Override protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_main);
    }

}
