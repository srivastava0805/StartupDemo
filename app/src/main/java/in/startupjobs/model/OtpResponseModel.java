package in.startupjobs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpResponseModel {
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("TOKEN")
    @Expose
    private String token;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("OTP")
    @Expose
    private String otp;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }


    public static class OtpDataToSend {
        public String type = "";
        public String value = "";
    }

    public static class VerifyOtpData {
        public String type = "";
        public String value = "";
        public int code;
    }
}



