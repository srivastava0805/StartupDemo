
package in.startupjobs.model.dashBoardData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Counts {

    @SerializedName("totalApplications")
    @Expose
    private Integer totalApplications;
    @SerializedName("totalShortlisted")
    @Expose
    private Integer totalShortlisted;
    @SerializedName("totalInterviewed")
    @Expose
    private Integer totalInterviewed;
    @SerializedName("totalOfferLetterRecieved")
    @Expose
    private Integer totalOfferLetterRecieved;
    @SerializedName("totalSelected")
    @Expose
    private Integer totalSelected;
    @SerializedName("totalWithdrawn")
    @Expose
    private Integer totalWithdrawn;

    public Integer getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(Integer totalApplications) {
        this.totalApplications = totalApplications;
    }

    public Integer getTotalShortlisted() {
        return totalShortlisted;
    }

    public void setTotalShortlisted(Integer totalShortlisted) {
        this.totalShortlisted = totalShortlisted;
    }

    public Integer getTotalInterviewed() {
        return totalInterviewed;
    }

    public void setTotalInterviewed(Integer totalInterviewed) {
        this.totalInterviewed = totalInterviewed;
    }

    public Integer getTotalOfferLetterRecieved() {
        return totalOfferLetterRecieved;
    }

    public void setTotalOfferLetterRecieved(Integer totalOfferLetterRecieved) {
        this.totalOfferLetterRecieved = totalOfferLetterRecieved;
    }

    public Integer getTotalSelected() {
        return totalSelected;
    }

    public void setTotalSelected(Integer totalSelected) {
        this.totalSelected = totalSelected;
    }

    public Integer getTotalWithdrawn() {
        return totalWithdrawn;
    }

    public void setTotalWithdrawn(Integer totalWithdrawn) {
        this.totalWithdrawn = totalWithdrawn;
    }

}
