package sttnf.app.pemira.core.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sttnf.app.pemira.R;
import sttnf.app.pemira.core.main.MainActivity;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class IntroduceFragment extends Fragment {

    public IntroduceFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_introduce, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_ready)
    public void btnReadyClicked() {
        ((MainActivity) getActivity()).startedItem();
    }

}
