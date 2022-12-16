
package in.startupjobs.model.applyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateAppliedJobsResponse {

    @SerializedName("result")
    @Expose
    private List<List<Result>> result = null;

    public List<List<Result>> getResult() {
        return result;
    }

    public void setResult(List<List<Result>> result) {
        this.result = result;
    }

}
