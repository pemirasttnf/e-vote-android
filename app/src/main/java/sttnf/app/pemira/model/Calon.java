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

    public int getCapresPhoto() {
        return capresPhoto;
    }

    public int getCawapresPhoto() {
        return cawapresPhoto;
    }

    public Calon setCandidateId(String candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public Calon setCapres(String capres) {
        this.capres = capres;
        return this;
    }

    public Calon setCawapres(String cawapres) {
        this.cawapres = cawapres;
        return this;
    }

    public Calon setCapresPhoto(int capresPhoto) {
        this.capresPhoto = capresPhoto;
        return this;
    }

    public Calon setCawapresPhoto(int cawapresPhoto) {
        this.cawapresPhoto = cawapresPhoto;
        return this;
    }
}
