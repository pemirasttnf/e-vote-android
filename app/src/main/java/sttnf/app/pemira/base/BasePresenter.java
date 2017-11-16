package sttnf.app.pemira.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import sttnf.app.pemira.network.Network;
import sttnf.app.pemira.network.Routes;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class BasePresenter<V> {
    protected V view;
    protected Routes service;
    private CompositeDisposable compositeDisposable;

    public void attachView(V view) {
        this.view = view;
        service = Network.CLIENT().create(Routes.class);
    }

    public void dettachView() {
        this.view = null;
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public void subscribe(Disposable subscriber) {
        if (compositeDisposable == null) compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(subscriber);
    }
}
