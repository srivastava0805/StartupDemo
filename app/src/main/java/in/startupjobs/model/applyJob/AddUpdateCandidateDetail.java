
package in.startupjobs.model.applyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddUpdateCandidateDetail {

    @SerializedName("@o_status")
    @Expose
    private int oStatus;

    public int getOStatus() {
        return oStatus;
    }

    public void setOStatus(int oStatus) {
        this.oStatus = oStatus;
    }

}
