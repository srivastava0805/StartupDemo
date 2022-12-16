package in.startupjobs.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpResponseModel {
    public static class OtpDataToSend {
        public String type = "";
        public String value = "";
    }

    public static class VerifyOtpData {
        public String type = "";
        public String value = "";
        public long code;
    }

    public static class VerifyOtpResponseData {
        public String message = "";
        public int verificationId;
    }
}



