package sttnf.app.pemira.core.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sttnf.app.pemira.R;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class BEMFragment extends Fragment {
    public BEMFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bem, container, false);
    }
}
