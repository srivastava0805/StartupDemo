package in.startupjobs.model.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePasswordModel {


    public static class OtpDataToSend {
        @SerializedName("email")
        @Expose
        private Object email;
        @SerializedName("mobileNumber")
        @Expose
        private Long mobileNumber;
        @SerializedName("code")
        @Expose
        private Long code;
        @SerializedName("password")
        @Expose
        private String password;

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Long getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(Long mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public Long getCode() {
            return code;
        }

        public void setCode(Long code) {
            this.code = code;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
    }

