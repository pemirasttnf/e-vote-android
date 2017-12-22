package sttnf.app.pemira.model;

/**
 * Created by isfaaghyth on 11/16/17.
 * github: @isfaaghyth
 */

public class Calon {
    private String candidateId;
    private String name;
    private String cawapres;
    private String header;

    public String getCandidateId() {
        return candidateId;
    }

    public String getName() {
        return name;
    }

    public String getCawapres() {
        return cawapres;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCawapres(String cawapres) {
        this.cawapres = cawapres;
    }

    public String getHeader() {
        return header;
    }
}
