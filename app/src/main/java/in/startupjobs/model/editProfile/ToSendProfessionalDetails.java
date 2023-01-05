
package in.startupjobs.model.editProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToSendProfessionalDetails {

    @SerializedName("currentLocation")
    @Expose
    private Integer currentLocation;
    @SerializedName("companyId")
    @Expose
    private Integer companyId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("currentDesignation")
    @Expose
    private String currentDesignation;
    @SerializedName("currentCTC")
    @Expose
    private String currentCTC;
    @SerializedName("totalExperienceYears")
    @Expose
    private Integer totalExperienceYears;
    @SerializedName("professionalSummary")
    @Expose
    private String professionalSummary;

    public Integer getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Integer currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public String getCurrentCTC() {
        return currentCTC;
    }

    public void setCurrentCTC(String currentCTC) {
        this.currentCTC = currentCTC;
    }

    public Integer getTotalExperienceYears() {
        return totalExperienceYears;
    }

    public void setTotalExperienceYears(Integer totalExperienceYears) {
        this.totalExperienceYears = totalExperienceYears;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

}
