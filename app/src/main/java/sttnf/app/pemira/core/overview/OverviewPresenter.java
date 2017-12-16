package sttnf.app.pemira.core.overview;

import android.util.Log;

import retrofit2.Response;
import rx.Subscriber;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Login;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

class OverviewPresenter extends BasePresenter<OverviewView> {

    OverviewPresenter(OverviewView view) {
        super.attachView(view);
    }

    String checkPrefixProdi(String str) {
        return str.length() < 5 ? str : str.substring(0, 5);
    }

    void doLogin(String nim, String password) {
        onSubscribe(service.doLogin("sttnfnice", nim, password), new Subscriber<Response<Login>>() {
            @Override public void onNext(Response<Login> res) {
                Log.e("TAG", res.toString());
                view.onSuccess(res.body());
            }

            @Override public void onError(Throwable e) {
                Log.d("Overview", e.getMessage());
                view.onError(e.getMessage());
            }

            @Override public void onCompleted() {

            }
        });
    }

}
