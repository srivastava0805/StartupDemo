
package in.startupjobs.model.applyJob;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("addUpdateCandidateDetails")
    @Expose
    private List<AddUpdateCandidateDetail> addUpdateCandidateDetails = null;

    public List<AddUpdateCandidateDetail> getAddUpdateCandidateDetails() {
        return addUpdateCandidateDetails;
    }

    public void setAddUpdateCandidateDetails(List<AddUpdateCandidateDetail> addUpdateCandidateDetails) {
        this.addUpdateCandidateDetails = addUpdateCandidateDetails;
    }

}
