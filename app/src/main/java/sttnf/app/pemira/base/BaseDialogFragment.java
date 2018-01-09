package sttnf.app.pemira.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.ButterKnife;
import io.isfaaghyth.rak.Rak;
import sttnf.app.pemira.util.ProgressLoader;

/**
 * Created by isfaaghyth on 1/10/18.
 * github: @isfaaghyth
 */

public abstract class BaseDialogFragment<P extends BasePresenter> extends DialogFragment {

    protected P presenter;
    protected ProgressLoader loader;
    protected abstract P initPresenter();
    protected abstract int contentView();
    protected abstract void viewCreated(View view);

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(contentView(), container, false);
    }

    @Override public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loader = new ProgressLoader(getContext());
        ButterKnife.bind(this, view);
        Rak.initialize(getContext());
        presenter = initPresenter();
        viewCreated(view);
    }

    protected void Toast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

}
