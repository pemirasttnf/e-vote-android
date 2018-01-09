package sttnf.app.pemira.core.overview.auth;

import android.content.Context;

import io.isfaaghyth.rak.Rak;
import retrofit2.Response;
import rx.Subscriber;
import sttnf.app.pemira.R;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Login;
import sttnf.app.pemira.util.Conts;

/**
 * Created by isfaaghyth on 1/10/18.
 * github: @isfaaghyth
 */

public class AuthPresenter extends BasePresenter<AuthView> {

    private Context context;

    public AuthPresenter(AuthView view) {
        super.attachView(view);
        context = view.context();
    }

    void doLogin(String nim, String password) {
        onSubscribe(service.doLogin(Conts.INFONF_TOKEN, nim, password), new Subscriber<Response<Login>>() {
            @Override public void onNext(Response<Login> res) {
                switch (res.code()) {
                    case 200:view.onSuccess(res.body());break;
                    case 401:view.onError(context.getString(R.string.status_401));break;
                    case 403:view.onError(context.getString(R.string.status_403));break;
                    default:view.onError(context.getString(R.string.status_conerr));
                }
            }
            @Override public void onError(Throwable e) {
                view.onError(context.getString(R.string.status_conerr));
            }
            @Override public void onCompleted() {
                stop();
            }
        });
    }

    void onSaveProfile(Login res) {
        Rak.entry("nim", res.getData().getNim());
        Rak.entry("token", res.getSecretToken());
        Rak.entry("nama", res.getData().getName());
        Rak.entry("avatar", res.getData().getAvatar());
        Rak.entry("prodi", res.getData().getProgramStudi());
        Rak.entry("statusMahasiswa", res.getData().getStatus());
        Rak.entry("tahunAngkatan", res.getData().getTahunAngkatan());
    }
}
