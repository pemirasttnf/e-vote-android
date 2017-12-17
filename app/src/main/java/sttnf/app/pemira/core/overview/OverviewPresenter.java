package sttnf.app.pemira.core.overview;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.HashMap;

import io.isfaaghyth.rak.Rak;
import retrofit2.Response;
import rx.Observer;
import rx.Subscriber;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Login;
import sttnf.app.pemira.model.Mahasiswa;
import sttnf.app.pemira.util.Conts;
import sttnf.app.pemira.util.RxFirebase;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

class OverviewPresenter extends BasePresenter<OverviewView> {

    private DatabaseReference dbref;

    OverviewPresenter(OverviewView view) {
        super.attachView(view);
        dbref = FirebaseDatabase.getInstance().getReference();
    }

    String checkPrefixProdi(String str) {
        return str.length() < 5 ? str : str.substring(0, 5);
    }

    /**
     * Untuk narik data dari https://info.nurulfikri.ac.id/sisfo/api/user/
     * @TODO(Masih belum bisa coy)
     * @param nim
     * @param password
     */
    void doLogin(String nim, String password) {
        onSubscribe(service.doLogin("sttnfnice", nim, password), new Subscriber<Response<Login>>() {
            @Override public void onNext(Response<Login> res) {
                if (res.isSuccessful()) {
                    if (res.body().getStatus().equals("success")) {
                        doStateLogin(res.body().getData());
                    }
                }
            }
            @Override public void onError(Throwable e) {
                view.onError(e.getMessage());
            }
            @Override public void onCompleted() {}
        });
    }

    void testLogin() {
        String test = "{\n" +
                "    \"status\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"nim\": \"0110215039\",\n" +
                "        \"nama\": \"Aufa Billah Putra Jazama\",\n" +
                "        \"prodi\": \"Teknik Informatika\",\n" +
                "        \"tahun_angkatan\": \"2015\",\n" +
                "        \"status_mhsw\": \"Aktif\",\n" +
                "        \"url_foto\": \"https://info.nurulfikri.ac.id/sisfo/\"\n" +
                "    }\n" +
                "}";
        Login success = new Gson().fromJson(test, Login.class);
        doStateLogin(success.getData());
    }

    /**
     * Untuk register user ke firebase, auth untuk voting
     * @param user
     */
    void doStateLogin(Mahasiswa user) {
        DatabaseReference ref = dbref.child(Conts.MAHASISWA);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    String yours = AESCrypt.encrypt(Conts.HASHPASSW, user.getNim());
                    if (!dataSnapshot.child(yours).exists()) {
                        HashMap<String, String> data = new HashMap<>();
                        data.put("nim", user.getNim());
                        data.put("nama", user.getNama());
                        data.put("prodi", user.getProdi());
                        data.put("status_mhsw", user.getStatus_mhsw());
                        data.put("tahun_angkatan", user.getTahun_angkatan());
                            RxFirebase.setValue(dbref
                                    .child(Conts.MAHASISWA)
                                    .child(yours), data)
                                    .subscribe(new Observer<Boolean>() {
                                        @Override public void onCompleted() {
                                            //Untuk menyimpan session berdasarkan nim.
                                            Rak.entry("key", yours);
                                            view.onSuccess(true);
                                        }
                                        @Override public void onError(Throwable e) {
                                            view.onSuccess(false);
                                        }
                                        @Override public void onNext(Boolean aBoolean) {}
                                    });
                    } else {
                        view.onError("Anda sudah melakukan vote sebelumnya");
                    }
                } catch (GeneralSecurityException e) {
                    e.printStackTrace();
                }
            }
            @Override public void onCancelled(DatabaseError databaseError) {
                view.onError("Periksa kembali koneksi internet anda.");
            }
        });
    }

}
