
package in.startupjobs.model.editProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationSettings {

    @SerializedName("jobApplications")
    @Expose
    private Boolean jobApplications;
    @SerializedName("jobRecommendations")
    @Expose
    private Boolean jobRecommendations;
    @SerializedName("whatsappNotifications")
    @Expose
    private Boolean whatsappNotifications;

    public Boolean getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(Boolean jobApplications) {
        this.jobApplications = jobApplications;
    }

    public Boolean getJobRecommendations() {
        return jobRecommendations;
    }

    public void setJobRecommendations(Boolean jobRecommendations) {
        this.jobRecommendations = jobRecommendations;
    }

    public Boolean getWhatsappNotifications() {
        return whatsappNotifications;
    }

    public void setWhatsappNotifications(Boolean whatsappNotifications) {
        this.whatsappNotifications = whatsappNotifications;
    }

}
