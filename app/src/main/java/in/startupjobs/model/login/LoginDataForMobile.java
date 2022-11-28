package in.startupjobs.model.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginDataForMobile {

    @SerializedName("code")
    @Expose
    private long code;
    @SerializedName("mobileNumber")
    @Expose
    private long mobileNumber;


    public void setCode(long code) {
        this.code = code;
    }


    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
