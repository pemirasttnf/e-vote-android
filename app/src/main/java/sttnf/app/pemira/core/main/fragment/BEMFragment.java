package sttnf.app.pemira.core.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.pemira.R;
import sttnf.app.pemira.adapter.CalonAdapter;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.model.CalonBus;
import sttnf.app.pemira.util.CacheManager;
import sttnf.app.pemira.util.ItemClickListener;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class BEMFragment extends Fragment implements ItemClickListener {

    @BindView(R.id.lst_bem) RecyclerView lstbem;

    private ArrayList<Calon> datas = new ArrayList<>();
    private CalonAdapter adapter;

    public BEMFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bem, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        datas.add(new Calon("https://avatars2.githubusercontent.com/u/6775159?s=460&v=4", "Muh Isfhani Ghiath", "0110215046"));
        datas.add(new Calon("https://avatars0.githubusercontent.com/u/13671268?s=460&v=4", "Dhimas Akbar Noor B.", "0110115001"));
        adapter = new CalonAdapter(datas, this);
        lstbem.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lstbem.setAdapter(adapter);
    }

    @Override public void onClick(int position) {
        CacheManager.save("bem", new Gson().toJson(datas.get(position)));
        ((MainActivity) getActivity()).startedItem(4);
    }

}
