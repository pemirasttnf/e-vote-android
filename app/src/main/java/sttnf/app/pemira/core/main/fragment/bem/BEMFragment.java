package sttnf.app.pemira.core.main.fragment.bem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

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

public class BEMFragment extends Fragment implements ItemClickListener, BEMView {

    @BindView(R.id.lst_bem) InfinitePlaceHolderView lstbem;

    private List<Calon> calons = new ArrayList<>();
    public BEMFragment() {}

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_bem, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        BEMPresenter presenter = new BEMPresenter(this);
        calons.addAll(presenter.getPaslonData().getData());
        initList();
    }

    private void initList() {
        int[] capres = {R.drawable.ic_joe, R.drawable.ic_karim};
        int[] cawapres = {R.drawable.ic_haya, R.drawable.ic_chai};
        lstbem.removeAllViews();
        int position = 0;
        for (Calon c: calons) {
            Calon calon = new Calon();
            calon.setCapres(c.getCapres());
            calon.setCawapres(c.getCawapres());
            calon.setCandidateId(c.getCandidateId());
            calon.setCapresPhoto(capres[position]);
            calon.setCawapresPhoto(cawapres[position]);
            lstbem.addView(
                    new CalonAdapter(position)
                    .with(getContext())
                    .model(calon)
                    .click(this)
            );
            position++;
        }
    }

    @Override public void onClick(int position) {
        String result = new Gson().toJson(calons.get(position));
        CacheManager.save("bem", result);
        ((MainActivity) getActivity()).startedItem(3);
    }

}
