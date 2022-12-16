package in.startupjobs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static class RegistrationDataToSend {
        @SerializedName("socialAccType")
        @Expose
        private Object socialAccType;
        @SerializedName("socialAccId")
        @Expose
        private Object socialAccId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("mobileNumber")
        @Expose
        private Long mobileNumber;
        @SerializedName("mobileOtp")
        @Expose
        private String mobileOtp;
        @SerializedName("emailOtp")
        @Expose
        private String emailOtp;
        @SerializedName("mobileVerificationId")
        @Expose
        private Integer mobileVerificationId;
        @SerializedName("emailVerificationId")
        @Expose
        private Integer emailVerificationId;
        @SerializedName("termsAccepted")
        @Expose
        private Boolean termsAccepted;
        @SerializedName("employerType")
        @Expose
        private String employerType;

        public Object getSocialAccType() {
            return socialAccType;
        }

        public void setSocialAccType(Object socialAccType) {
            this.socialAccType = socialAccType;
        }

        public Object getSocialAccId() {
            return socialAccId;
        }

        public void setSocialAccId(Object socialAccId) {
            this.socialAccId = socialAccId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Long getMobileNumber() {
            return mobileNumber;
        }

        public void setMobileNumber(Long mobileNumber) {
            this.mobileNumber = mobileNumber;
        }

        public String getMobileOtp() {
            return mobileOtp;
        }

        public void setMobileOtp(String mobileOtp) {
            this.mobileOtp = mobileOtp;
        }

        public String getEmailOtp() {
            return emailOtp;
        }

        public void setEmailOtp(String emailOtp) {
            this.emailOtp = emailOtp;
        }

        public Integer getMobileVerificationId() {
            return mobileVerificationId;
        }

        public void setMobileVerificationId(Integer mobileVerificationId) {
            this.mobileVerificationId = mobileVerificationId;
        }

        public Integer getEmailVerificationId() {
            return emailVerificationId;
        }

        public void setEmailVerificationId(Integer emailVerificationId) {
            this.emailVerificationId = emailVerificationId;
        }

        public Boolean getTermsAccepted() {
            return termsAccepted;
        }

        public void setTermsAccepted(Boolean termsAccepted) {
            this.termsAccepted = termsAccepted;
        }

        public String getEmployerType() {
            return employerType;
        }

        public void setEmployerType(String employerType) {
            this.employerType = employerType;
        }
    }
}
