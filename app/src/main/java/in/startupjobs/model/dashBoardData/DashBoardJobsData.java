
package in.startupjobs.model.dashBoardData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DashBoardJobsData {

    @SerializedName("counts")
    @Expose
    private Counts counts;

    public Counts getCounts() {
        return counts;
    }

    public void setCounts(Counts counts) {
        this.counts = counts;
    }

}
