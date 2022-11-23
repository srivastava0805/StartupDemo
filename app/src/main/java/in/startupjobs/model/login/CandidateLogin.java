package in.startupjobs.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CandidateLogin {

    @SerializedName("ID")
    @Expose
    private Integer id;
    @SerializedName("CANDIDATEEMAILID")
    @Expose
    private String candidateemailid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCandidateemailid() {
        return candidateemailid;
    }

    public void setCandidateemailid(String candidateemailid) {
        this.candidateemailid = candidateemailid;
    }
}
