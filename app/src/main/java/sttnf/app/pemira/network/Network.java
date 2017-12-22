package sttnf.app.pemira.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import sttnf.app.pemira.BuildConfig;
import sttnf.app.pemira.util.UnsafeOkHttpClient;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class Network {
    public static Retrofit CLIENT() {

        HttpLoggingInterceptor t = new HttpLoggingInterceptor();
        t.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        OkHttpClient test = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.SECONDS)
                .writeTimeout(1000, TimeUnit.SECONDS)
                .readTimeout(3000, TimeUnit.SECONDS)
                .addInterceptor(t)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(test)
                .build();
    }
}
