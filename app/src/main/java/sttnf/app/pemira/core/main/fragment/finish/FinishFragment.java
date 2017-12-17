package sttnf.app.pemira.core.main.fragment.finish;

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
import sttnf.app.pemira.core.main.fragment.finish.FinishPresenter;
import sttnf.app.pemira.core.main.fragment.finish.FinishView;
import sttnf.app.pemira.core.overview.OverviewActivity;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.model.Calons;
import sttnf.app.pemira.util.CacheManager;
import sttnf.app.pemira.util.GlideUtil;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class FinishFragment extends Fragment implements FinishView {

    @BindView(R.id.img_bem) ImageView imgBem;

    private FinishPresenter presenter;
    private Calons paslonVote;

    public FinishFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_finish, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new FinishPresenter(this);
        ButterKnife.bind(this, view);
        receiveData();
    }

    public void receiveData() {
        paslonVote = new Gson().fromJson(CacheManager.grabString("bem"), Calons.class);
        new GlideUtil()
                .with(getContext())
                .into(imgBem)
                .loadImage(paslonVote.getCapresma().getAvatar());
    }

    @OnClick(R.id.btn_finish)
    public void onBtnFinishClicked() {
        presenter.saveVote(paslonVote);
    }

    @Override public void onSuccess(boolean isSuccess) {
        if (isSuccess) {
            startActivity(new Intent(getContext(), OverviewActivity.class));
            getActivity().finish();
        }
    }
}
