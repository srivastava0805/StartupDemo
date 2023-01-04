
package in.startupjobs.model.basicPublicProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("currentLocation")
    @Expose
    private CurrentLocation currentLocation;
    @SerializedName("currentDesignation")
    @Expose
    private String currentDesignation;
    @SerializedName("currentCompany")
    @Expose
    private CurrentCompany currentCompany;
    @SerializedName("currentCompanyName")
    @Expose
    private String currentCompanyName;
    @SerializedName("preferredJobSearchStatus")
    @Expose
    private String preferredJobSearchStatus;
    @SerializedName("professionalSummary")
    @Expose
    private String professionalSummary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public CurrentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public CurrentCompany getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(CurrentCompany currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
    }

    public String getPreferredJobSearchStatus() {
        return preferredJobSearchStatus;
    }

    public void setPreferredJobSearchStatus(String preferredJobSearchStatus) {
        this.preferredJobSearchStatus = preferredJobSearchStatus;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

}
