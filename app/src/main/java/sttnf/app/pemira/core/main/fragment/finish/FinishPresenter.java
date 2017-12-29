package sttnf.app.pemira.core.main.fragment.finish;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Response;
import rx.Subscriber;
import sttnf.app.pemira.base.BasePresenter;
import sttnf.app.pemira.model.Calon;
import sttnf.app.pemira.model.Vote;

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
    void saveVote(String token, Calon calon) {
        onSubscribe(service.vote(calon.getCandidateId(), token), new Subscriber<Response<Vote>>() {
            @Override public void onCompleted() {}
            @Override public void onError(Throwable e) {
                Log.d("TAG", e.getMessage());
                view.onVote(false, 0);
            }
            @Override public void onNext(Response<Vote> res) {
                Log.d("TAG", new Gson().toJson(res.body()));
                Log.d("TAG", res.toString());
                if (res.code() == 200) {
                    view.onVote(true, 200);
                } else if (res.code() == 403) {
                        view.onVote(true, 403);
                } else {
                    view.onVote(false, 0);
                }
            }
        });
    }

}
