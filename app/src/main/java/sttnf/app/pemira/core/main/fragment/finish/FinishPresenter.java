package sttnf.app.pemira.core.main.fragment.finish;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.HashMap;

import io.isfaaghyth.rak.Rak;
import rx.Observer;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Calons;
import sttnf.app.pemira.util.Conts;
import sttnf.app.pemira.util.RxFirebase;

/**
 * Created by isfaaghyth on 12/18/17.
 * github: @isfaaghyth
 */

public class FinishPresenter extends BasePresenter<FinishView> {

    FinishPresenter(FinishView view) {
        super.attachView(view);
    }

    /**
     * Untuk publish vote!
     * @param calon
     */
    void saveVote(Calons calon) {

    }

}
