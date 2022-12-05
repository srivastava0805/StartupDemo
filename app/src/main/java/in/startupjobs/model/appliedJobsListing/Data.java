
package in.startupjobs.model.appliedJobsListing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("getAllJobPostDetailsList")
    @Expose
    private List<GetAllJobPostDetails> getAllJobPostDetailsList = null;

    public List<GetAllJobPostDetails> getGetAllJobPostDetailsList() {
        return getAllJobPostDetailsList;
    }

    public void setGetAllJobPostDetailsList(List<GetAllJobPostDetails> getAllJobPostDetailsList) {
        this.getAllJobPostDetailsList = getAllJobPostDetailsList;
    }

}
