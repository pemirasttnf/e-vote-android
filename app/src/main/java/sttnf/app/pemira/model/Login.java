package sttnf.app.pemira.model;

/**
 * Created by isfaaghyth on 12/15/17.
 * github: @isfaaghyth
 */

public class Login {
    private int statusCode;
    private Mahasiswa data;
    private String secretToken;

    public int getStatusCode() {
        return statusCode;
    }

    public Mahasiswa getData() {
        return data;
    }

    public String getSecretToken() {
        return secretToken;
    }
}
