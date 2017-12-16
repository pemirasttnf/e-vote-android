package sttnf.app.pemira.core.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import butterknife.BindView;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BaseActivity;
import sttnf.app.pemira.util.CustomViewPager;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.vp_option) CustomViewPager vpOption;


    @Override protected MainPresenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_main);
        presenter.setupViewPager(vpOption);
        vpOption.setOffscreenPageLimit(0);
        vpOption.setPagingEnabled(false);
    }

    public void startedItem(int position) {
        vpOption.setCurrentItem(position, true);
    }

    @Override public FragmentManager getSupportFragment() {
        return getSupportFragmentManager();
    }

}
