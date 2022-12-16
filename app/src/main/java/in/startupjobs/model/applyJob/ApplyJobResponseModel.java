
package in.startupjobs.model.applyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyJobResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;

    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("message")
    @Expose
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ApplyJobDataToSend {


            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("resumeFile")
            @Expose
            private String resumeFile;
            @SerializedName("currentDesignation")
            @Expose
            private String currentDesignation;
            @SerializedName("mobileNumber")
            @Expose
            private Long mobileNumber;

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

            public String getResumeFile() {
                return resumeFile;
            }

            public void setResumeFile(String resumeFile) {
                this.resumeFile = resumeFile;
            }

            public String getCurrentDesignation() {
                return currentDesignation;
            }

            public void setCurrentDesignation(String currentDesignation) {
                this.currentDesignation = currentDesignation;
            }

            public Long getMobileNumber() {
                return mobileNumber;
            }

            public void setMobileNumber(Long mobileNumber) {
                this.mobileNumber = mobileNumber;
            }

        }
    }

