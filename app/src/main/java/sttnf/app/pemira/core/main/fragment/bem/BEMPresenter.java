package sttnf.app.pemira.core.main.fragment.bem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.util.RxFirebase;

/**
 * Created by isfaaghyth on 12/17/17.
 * github: @isfaaghyth
 */

public class BEMPresenter extends BasePresenter<BEMView> {

    private DatabaseReference dbref;

    public BEMPresenter(BEMView view) {
        super.attachView(view);
        dbref = FirebaseDatabase.getInstance().getReference();
    }

    public void getPaslonData() {
        DatabaseReference paslon = dbref.child("paslon");
        paslon.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                view.onGetPaslon(dataSnapshot.getChildren());
            }
            @Override public void onCancelled(DatabaseError databaseError) {
                view.onError(databaseError.getMessage());
            }
        });
    }

}
