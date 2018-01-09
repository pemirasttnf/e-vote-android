package sttnf.app.pemira.core.main.fragment.finish;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.isfaaghyth.rak.Rak;
import sttnf.app.pemira.R;
import sttnf.app.pemira.core.overview.OverviewActivity;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.util.ProgressLoader;
import sttnf.app.pemira.util.StartActivities;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class FinishFragment extends Fragment implements FinishView {

    @BindView(R.id.chk_agree) CheckBox chkAgree;

    private FinishPresenter presenter;
    private ProgressLoader loader;
    private Calon paslonVote;

    public FinishFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_finish, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = new ProgressLoader(getContext());
        presenter = new FinishPresenter(this);
        ButterKnife.bind(this, view);
        receiveData();
    }

    public void receiveData() {
        paslonVote = new Gson().fromJson((String) Rak.grab("paslon"), Calon.class);
    }

    @OnClick(R.id.btn_finish)
    public void onBtnFinishClicked() {
        String token = "JWT " + Rak.grab("token");
        if (chkAgree.isChecked()) {
            loader.show();
            presenter.saveVote(token, paslonVote);
        } else {
            Toast.makeText(getContext(), getString(R.string.err_term), Toast.LENGTH_LONG).show();
        }
    }

    @Override public void onVote(boolean isSuccess, int resCode) {
        loader.hide();
        if (isSuccess) {
            StartActivities.start(getActivity(), OverviewActivity.class, resCode);
        } else {
            Toast.makeText(getContext(), "Terjadi kesalahan.", Toast.LENGTH_LONG).show();
        }
    }
}
