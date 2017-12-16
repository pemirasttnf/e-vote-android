package sttnf.app.pemira.core.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sttnf.app.pemira.R;
import sttnf.app.pemira.core.overview.OverviewActivity;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.util.CacheManager;
import sttnf.app.pemira.util.GlideUtil;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class FinishFragment extends Fragment {

    @BindView(R.id.img_bem) ImageView imgBem;

    public FinishFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_finish, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        receiveData();
    }

    public void receiveData() {
        Calon bem = new Gson().fromJson(CacheManager.grabString("bem"), Calon.class);
        new GlideUtil().with(getContext())
                .into(imgBem).loadImage(bem.getAvatar());
        Log.d("BEM", bem.getName());
    }

    @OnClick(R.id.btn_finish)
    public void onBtnFinishClicked() {
        startActivity(new Intent(getContext(), OverviewActivity.class));
        getActivity().finish();
    }

}
