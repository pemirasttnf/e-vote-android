package sttnf.app.pemira.core.main.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sttnf.app.pemira.R;
import sttnf.app.pemira.core.overview.OverviewActivity;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.model.CalonBus;
import sttnf.app.pemira.util.CacheManager;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class FinishFragment extends Fragment {

    @BindView(R.id.img_bem) ImageView imgBem;
    @BindView(R.id.img_dpm) ImageView imgDpm;

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
        Calon dpm = new Gson().fromJson(CacheManager.grabString("dpm"), Calon.class);
        Calon bem = new Gson().fromJson(CacheManager.grabString("bem"), Calon.class);
        loadImage(dpm.getAvatar(), imgDpm);
        loadImage(bem.getAvatar(), imgBem);

        Log.d("DPM", dpm.getName());
        Log.d("BEM", bem.getName());
    }

    private void loadImage(String url, final ImageView imageView) {
        Glide.with(getContext()).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable rounded =
                        RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                rounded.setCircular(true);
                imageView.setImageDrawable(rounded);
            }
        });
    }

    @OnClick(R.id.btn_finish)
    public void onBtnFinishClicked() {
        startActivity(new Intent(getContext(), OverviewActivity.class));
        getActivity().finish();
    }

}
