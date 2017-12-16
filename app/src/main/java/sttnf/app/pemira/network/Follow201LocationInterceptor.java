package sttnf.app.pemira.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.http.Header;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_SEE_OTHER;

/**
 * To utilize it simply add a header to your endpoint using {@link Header} annotation:
 * <pre><code>
 * interface MyService {
 *   &#64;POST("user")
 *   &#64;Headers(Follow201LocationInterceptor.FOLLOW_HEADER)
 *   Observable&lt;User&gt; createUser(@Body String name)
 * }
 * </code></pre>
 * <p>
 * It has to be used as a network interceptor, otherwise it will not work.
 */
public class Follow201LocationInterceptor implements Interceptor {

    private static final String FOLLOW_REDIRECT = "follow-redirect";
    public static final String FOLLOW_HEADER = FOLLOW_REDIRECT + ":true";
    private static final String LOCATION = "Location";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        boolean shouldRedirect = request.header(FOLLOW_REDIRECT) != null;

        if (shouldRedirect) {
            request = request.newBuilder().removeHeader(FOLLOW_REDIRECT).build();
        }

        Response response = chain.proceed(request);
        if (shouldRedirect && response.code() == HTTP_CREATED && response.header(LOCATION) != null) {
            return response.newBuilder().code(HTTP_SEE_OTHER).build();
        }
        return response;
    }
}