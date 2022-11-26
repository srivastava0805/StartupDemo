package in.startupjobs.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginDataForMobile {

    @SerializedName("candidateLogin")
    @Expose
    private List<CandidateLogin> candidateLogin = null;
    @SerializedName("mobile")
    @Expose
    private String token;

    public List<CandidateLogin> getCandidateLogin() {
        return candidateLogin;
    }

    public void setCandidateLogin(List<CandidateLogin> candidateLogin) {
        this.candidateLogin = candidateLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
