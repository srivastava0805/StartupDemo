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
        @SerializedName("ID")
        @Expose
        private String id = "";
        @SerializedName("CANDIDATENAME")
        @Expose
        private String candidatename;
        @SerializedName("CANDIDATEEMAILID")
        @Expose
        private String candidateemailid;
        @SerializedName("CANDIDATEPHONENO")
        @Expose
        private String candidatephoneno = "7838817754";
        @SerializedName("candidatepassword")
        @Expose
        private String candidatepassword;
        @SerializedName("TOKEN")
        @Expose
        private String token;
        @SerializedName("OTP")
        @Expose
        private String otp;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCandidatename() {
            return candidatename;
        }

        public void setCandidatename(String candidatename) {
            this.candidatename = candidatename;
        }

        public String getCandidateemailid() {
            return candidateemailid;
        }

        public void setCandidateemailid(String candidateemailid) {
            this.candidateemailid = candidateemailid;
        }

        public String getCandidatephoneno() {
            return candidatephoneno;
        }

        public void setCandidatephoneno(String candidatephoneno) {
            this.candidatephoneno = candidatephoneno;
        }

        public String getCandidatepassword() {
            return candidatepassword;
        }

        public void setCandidatepassword(String candidatepassword) {
            this.candidatepassword = candidatepassword;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }


    }
}
