package sttnf.app.pemira.core.main;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.core.main.fragment.BEMFragment;
import sttnf.app.pemira.core.main.fragment.DPMFragment;
import sttnf.app.pemira.core.main.fragment.FinishFragment;
import sttnf.app.pemira.core.main.fragment.IntroduceFragment;
import sttnf.app.pemira.adapter.ViewPagerAdapter;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

class MainPresenter extends BasePresenter<MainView> {

    private ViewPagerAdapter adapter;

    MainPresenter(MainView view) {
        super.attachView(view);
        adapter = new ViewPagerAdapter(view.getSupportFragment());
    }

    void setupViewPager(ViewPager viewPager) {
        adapter.addFragment(new IntroduceFragment());
        adapter.addFragment(new DPMFragment());
        adapter.addFragment(new BEMFragment());
        adapter.addFragment(new FinishFragment());
        viewPager.setAdapter(adapter);
    }

}
