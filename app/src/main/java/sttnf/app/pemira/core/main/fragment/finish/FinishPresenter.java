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

    private DatabaseReference dbref;

    FinishPresenter(FinishView view) {
        super.attachView(view);
        dbref = FirebaseDatabase.getInstance().getReference();
    }

    /**
     * Untuk publish vote!
     * @param calon
     */
    void saveVote(Calons calon) {
        HashMap<String, String> data = new HashMap<>();
        String yours = Rak.grab("key");
        data.put("mahasiswa", yours);
        data.put("vote", calon.getCapresma().getUuid());
        try {
            RxFirebase.setValue(dbref
                    .child(Conts.VOTE)
                    .child(AESCrypt.decrypt(Conts.HASHPASSW, yours)), data)
                    .subscribe(new Observer<Boolean>() {
                        @Override public void onCompleted() {
                            view.onSuccess(true);
                        }
                        @Override public void onError(Throwable e) {
                            view.onSuccess(false);
                        }
                        @Override public void onNext(Boolean aBoolean) {}
                    });
        } catch (GeneralSecurityException ignored) {}
    }

}
