package sttnf.app.pemira.base;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import sttnf.app.pemira.network.Network;
import sttnf.app.pemira.network.Routes;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class BasePresenter<V> {
    public V view;
    protected Routes service;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    public void attachView(V view) {
        this.view = view;
        service = Network.CLIENT().create(Routes.class);
    }

    public void dettachView() {
        this.view = null;
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("BasePresenter", "dettachView");
        }
    }

    protected void onSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;

        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    protected void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}