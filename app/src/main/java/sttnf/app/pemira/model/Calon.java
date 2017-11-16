package sttnf.app.pemira.model;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class Calon {
    private String avatar;
    private String name;
    private String nim;

    public Calon(String avatar, String name, String nim) {
        this.avatar = avatar;
        this.name = name;
        this.nim = nim;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }
}
