package sttnf.app.pemira.model;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class Calon {
    private String candidateId;
    private String capres;
    private String cawapres;
    private int capresPhoto;
    private int cawapresPhoto;

    public String getCandidateId() {
        return candidateId;
    }

    public String getCapres() {
        return capres;
    }

    public String getCawapres() {
        return cawapres;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public int getCapresPhoto() {
        return capresPhoto;
    }

    public int getCawapresPhoto() {
        return cawapresPhoto;
    }

    public void setCapres(String capres) {
        this.capres = capres;
    }

    public void setCawapres(String cawapres) {
        this.cawapres = cawapres;
    }

    public void setCapresPhoto(int capresPhoto) {
        this.capresPhoto = capresPhoto;
    }

    public void setCawapresPhoto(int cawapresPhoto) {
        this.cawapresPhoto = cawapresPhoto;
    }
}
