
package in.startupjobs.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("data")
    @Expose
    private LoginDataForMobile data;
    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("fullName")
    @Expose
    private String fullName;

    @SerializedName("userType")
    @Expose
    private String userType;

    @SerializedName("accessToken")
    @Expose
    private String token;

    public String  getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LoginDataForMobile getData() {
        return data;
    }

    public void setData(LoginDataForMobile data) {
        this.data = data;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserType() {
        return userType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserType(String userType) {
        this.userType = userType;
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
