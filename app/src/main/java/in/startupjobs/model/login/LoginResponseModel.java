
package in.startupjobs.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private LoginDataForMobile data;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("token")
    @Expose
    private String token;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LoginDataForMobile getData() {
        return data;
    }

    public void setData(LoginDataForMobile data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class LoginDataToSend {

        @SerializedName("email")
        @Expose
        private String candidateEmail;
        @SerializedName("password")
        @Expose
        private String passwords;

        public void setCandidateEmail(String candidateEmail) {
            this.candidateEmail = candidateEmail;
        }

        public void setPasswords(String passwords) {
            this.passwords = passwords;
        }
    }
}
