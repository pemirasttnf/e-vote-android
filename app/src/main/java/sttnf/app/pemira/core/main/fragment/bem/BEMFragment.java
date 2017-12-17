package sttnf.app.pemira.core.main.fragment.bem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.pemira.R;
import sttnf.app.pemira.adapter.CalonAdapter;
import sttnf.app.pemira.core.main.MainActivity;
import sttnf.app.pemira.core.main.fragment.bem.BEMPresenter;
import sttnf.app.pemira.core.main.fragment.bem.BEMView;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.model.Calons;
import sttnf.app.pemira.util.CacheManager;
import sttnf.app.pemira.util.ItemClickListener;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class BEMFragment extends Fragment implements ItemClickListener, BEMView {

    @BindView(R.id.lst_bem) RecyclerView lstbem;

    private ArrayList<Calons> data = new ArrayList<>();
    private CalonAdapter adapter;

    public BEMFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bem, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        BEMPresenter presenter = new BEMPresenter(this);
        lstbem.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CalonAdapter(data, this);
        presenter.getPaslonData();
        lstbem.setAdapter(adapter);
    }

    @Override public void onClick(int position) {
        CacheManager.save("bem", new Gson().toJson(data.get(position)));
        ((MainActivity) getActivity()).startedItem(4);
    }

    @Override public void onGetPaslon(Iterable<DataSnapshot> datas) {
        data.clear();
        for (DataSnapshot s: datas) {
            Calons calon = s.getValue(Calons.class);
            data.add(calon);
            adapter.notifyDataSetChanged();
        }
    }

    @Override public void onError(String err) {

    }
}
