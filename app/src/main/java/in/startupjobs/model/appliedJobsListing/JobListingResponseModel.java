
package in.startupjobs.model.appliedJobsListing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JobListingResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class JobListingDataToSend {

        @SerializedName("JOBTYPEID")
        @Expose
        private String jobtypeid = "";
        @SerializedName("JOBTITLE")
        @Expose
        private String jobtitle = "";
        @SerializedName("LOCATION")
        @Expose
        private String location = "";

        public String getJobtypeid() {
            return jobtypeid;
        }

        public void setJobtypeid(String jobtypeid) {
            this.jobtypeid = jobtypeid;
        }

        public String getJobtitle() {
            return jobtitle;
        }

        public void setJobtitle(String jobtitle) {
            this.jobtitle = jobtitle;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

    }

}
