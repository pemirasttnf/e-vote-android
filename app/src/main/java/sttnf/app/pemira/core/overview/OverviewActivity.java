package sttnf.app.pemira.core.overview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class OverviewActivity extends BaseActivity<OverviewPresenter> implements OverviewView {

    @Override protected OverviewPresenter initPresenter() {
        return new OverviewPresenter(this);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_overview);
    }

}
