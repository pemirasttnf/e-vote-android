package sttnf.app.pemira.core.main.fragment.bem;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by isfaaghyth on 12/17/17.
 * github: @isfaaghyth
 */

public interface BEMView {
    void onGetPaslon(Iterable<DataSnapshot> datas);
    void onError(String err);
}
