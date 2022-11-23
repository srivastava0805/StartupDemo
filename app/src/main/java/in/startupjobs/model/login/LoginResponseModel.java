
package in.startupjobs.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private LoginData data;
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

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
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

        @SerializedName("CANDIDATEEMAILID")
        @Expose
        private String candidateEmail;
        @SerializedName("candidatepassword")
        @Expose
        private String passwords;
        @SerializedName("candidateAuthToken")
        @Expose
        private String candidateAuthToken = "";
        @SerializedName("candidateImageUrl")
        @Expose
        private String candidateImageUrl = "";
        @SerializedName("candidateName")
        @Expose
        private String candidateName = "";
        @SerializedName("canddateLoginId")
        @Expose
        private String canddateLoginId = "";
        @SerializedName("candidateLoginProvider")
        @Expose
        private String candidateLoginProvider = "CUSTOM";

        @SerializedName("candidateEmail")
        @Expose
        private String candidateEmailWithProvider;


        public String getCandidateEmailWithProvider() {
            return candidateEmailWithProvider;
        }

        public void setCandidateEmailWithProvider(String candidateEmailWithProvider) {
            this.candidateEmailWithProvider = candidateEmailWithProvider;
        }

        public String getCandidateEmail() {
            return candidateEmail;
        }

        public void setCandidateEmail(String candidateEmail) {
            this.candidateEmail = candidateEmail;
        }

        public String getPasswords() {
            return passwords;
        }

        public void setPasswords(String passwords) {
            this.passwords = passwords;
        }

        public String getCandidateAuthToken() {
            return candidateAuthToken;
        }

        public void setCandidateAuthToken(String candidateAuthToken) {
            this.candidateAuthToken = candidateAuthToken;
        }

        public String getCandidateImageUrl() {
            return candidateImageUrl;
        }

        public void setCandidateImageUrl(String candidateImageUrl) {
            this.candidateImageUrl = candidateImageUrl;
        }

        public String getCandidateName() {
            return candidateName;
        }

        public void setCandidateName(String candidateName) {
            this.candidateName = candidateName;
        }

        public String getCanddateLoginId() {
            return canddateLoginId;
        }

        public void setCanddateLoginId(String canddateLoginId) {
            this.canddateLoginId = canddateLoginId;
        }

        public String getCandidateLoginProvider() {
            return candidateLoginProvider;
        }

        public void setCandidateLoginProvider(String candidateLoginProvider) {
            this.candidateLoginProvider = candidateLoginProvider;
        }

    }
}
