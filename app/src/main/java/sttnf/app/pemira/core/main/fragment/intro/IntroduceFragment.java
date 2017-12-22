package sttnf.app.pemira.core.main.fragment.intro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.isfaaghyth.rak.Rak;
import sttnf.app.pemira.R;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.util.GlideUtil;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class IntroduceFragment extends Fragment {

    @BindView(R.id.img_avatar) ImageView imgAvatar;
    @BindView(R.id.txt_name) TextView txtName;
    @BindView(R.id.txt_nim) TextView txtNim;

    public IntroduceFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_introduce, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        loadProfile();
    }

    private void loadProfile() {
        String avatar = Rak.grab("avatar");
        String name = Rak.grab("nama");
        String nim = Rak.grab("nim");
        new GlideUtil()
                .with(getContext())
                .into(imgAvatar)
                .loadImage(avatar);

        txtName.setText(name);
        txtNim.setText(nim);
    }

    @OnClick(R.id.btn_ready)
    public void btnReadyClicked() {
        ((MainActivity) getActivity()).startedItem(1);
    }

}
