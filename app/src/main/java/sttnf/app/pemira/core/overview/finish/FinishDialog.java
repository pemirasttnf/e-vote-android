package sttnf.app.pemira.core.overview.finish;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.pemira.R;

/**
 * Created by isfaaghyth on 1/10/18.
 * github: @isfaaghyth
 */

public class FinishDialog extends DialogFragment {

    @BindView(R.id.txt_message) TextView txtMessage;
    @BindView(R.id.img_ic) ImageView imgIcon;

    private String message;
    private int code;

    public FinishDialog() {}

    @Override public void onAttach(Context context) {
        super.onAttach(context);
        message = getArguments().getString("message");
        code = getArguments().getInt("code", 0);
    }

    @Override public void onResume() {
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = LinearLayout.LayoutParams.MATCH_PARENT;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        super.onResume();
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.dialog_finish, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setCancelable(false);
        switch (code) {
            case 200: imgIcon.setImageResource(R.mipmap.ic_check); break;
            default: imgIcon.setImageResource(R.mipmap.ic_caution);
        }
        txtMessage.setText(message);
        new Handler().postDelayed(this::dismiss, 10000);
    }

}
