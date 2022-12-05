
package in.startupjobs.model.appliedJobsListing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplicationStatus {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("interviewDate")
    @Expose
    private Object interviewDate;
    @SerializedName("interviewTime")
    @Expose
    private Object interviewTime;
    @SerializedName("interviewLocation")
    @Expose
    private Object interviewLocation;
    @SerializedName("interviewStatus")
    @Expose
    private Object interviewStatus;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("statusType")
    @Expose
    private StatusType statusType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Object getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Object interviewDate) {
        this.interviewDate = interviewDate;
    }

    public Object getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Object interviewTime) {
        this.interviewTime = interviewTime;
    }

    public Object getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(Object interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public Object getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(Object interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

}
