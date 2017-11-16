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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.pemira.R;
import sttnf.app.pemira.adapter.CalonAdapter;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.util.CacheManager;
import sttnf.app.pemira.util.ItemClickListener;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class DPMFragment extends Fragment implements ItemClickListener {

    @BindView(R.id.lst_dpm) RecyclerView lstDpm;

    private ArrayList<Calon> datas = new ArrayList<>();
    private CalonAdapter adapter;

    public DPMFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_dpm, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        datas.add(new Calon("https://avatars0.githubusercontent.com/u/19663290?s=460&v=4", "Robiyatul Adawiyah", "0110215001"));
        datas.add(new Calon("https://avatars0.githubusercontent.com/u/24696558?s=460&v=4", "Yuli Hardianti", "0110115001"));
        adapter = new CalonAdapter(datas, this);
        lstDpm.setLayoutManager(new GridLayoutManager(getContext(), 2));
        lstDpm.setAdapter(adapter);
    }

    @Override public void onClick(int position) {
        CacheManager.save("dpm", new Gson().toJson(datas.get(position)));
        ((MainActivity) getActivity()).startedItem(2);
    }

}
