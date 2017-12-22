package sttnf.app.pemira.network;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;
import sttnf.app.pemira.model.Login;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public interface Routes {

    /**
     * @BaseURL https://info.nurulfikri.ac.id/
     * @param token
     * @param nim
     * @param password
     * @return
     */
    @FormUrlEncoded @POST("auth/login")
    Observable<Response<Login>> doLogin(
            @Field("token") String token,
            @Field("nim") String nim,
            @Field("password") String password
    );

    @FormUrlEncoded @POST("auth/register")
    Observable<Response<Login>> doRegister(
            @Field("nim") String nim
    );

}
