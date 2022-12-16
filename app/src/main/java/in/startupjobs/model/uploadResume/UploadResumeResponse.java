
package in.startupjobs.model.uploadResume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UploadResumeResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("totalExperienceYears")
    @Expose
    private Integer totalExperienceYears;
    @SerializedName("currentCompanyName")
    @Expose
    private String currentCompanyName;
    @SerializedName("currentDesignation")
    @Expose
    private String currentDesignation;
    @SerializedName("currentCTC")
    @Expose
    private String currentCTC;
    @SerializedName("professionalSummary")
    @Expose
    private String professionalSummary;
    @SerializedName("skills")
    @Expose
    private List<Integer> skills = null;
    @SerializedName("achievements")
    @Expose
    private String achievements;
    @SerializedName("resumeFiles")
    @Expose
    private List<String> resumeFiles = null;
    @SerializedName("videoLink")
    @Expose
    private Object videoLink;
    @SerializedName("preferredJobSearchStatus")
    @Expose
    private String preferredJobSearchStatus;
    @SerializedName("preferredJobTypes")
    @Expose
    private Object preferredJobTypes;
    @SerializedName("preferedLocations")
    @Expose
    private List<Integer> preferedLocations = null;
    @SerializedName("canWorkRemotely")
    @Expose
    private Boolean canWorkRemotely;
    @SerializedName("expectedCtcLakhs")
    @Expose
    private Integer expectedCtcLakhs;
    @SerializedName("expectedCtcThousands")
    @Expose
    private Integer expectedCtcThousands;
    @SerializedName("isCTCNegotiable")
    @Expose
    private Boolean isCTCNegotiable;
    @SerializedName("noticePeriod")
    @Expose
    private String noticePeriod;
    @SerializedName("currentLocation")
    @Expose
    private CurrentLocation currentLocation;
    @SerializedName("preferredFunctionalArea")
    @Expose
    private PreferredFunctionalArea preferredFunctionalArea;
    @SerializedName("preferredIndustry")
    @Expose
    private PreferredIndustry preferredIndustry;
    @SerializedName("currentCompanyDetails")
    @Expose
    private CurrentCompanyDetails currentCompanyDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalExperienceYears() {
        return totalExperienceYears;
    }

    public void setTotalExperienceYears(Integer totalExperienceYears) {
        this.totalExperienceYears = totalExperienceYears;
    }

    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
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

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public List<String> getResumeFiles() {
        return resumeFiles;
    }

    public void setResumeFiles(List<String> resumeFiles) {
        this.resumeFiles = resumeFiles;
    }

    public Object getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(Object videoLink) {
        this.videoLink = videoLink;
    }

    public String getPreferredJobSearchStatus() {
        return preferredJobSearchStatus;
    }

    public void setPreferredJobSearchStatus(String preferredJobSearchStatus) {
        this.preferredJobSearchStatus = preferredJobSearchStatus;
    }

    public Object getPreferredJobTypes() {
        return preferredJobTypes;
    }

    public void setPreferredJobTypes(Object preferredJobTypes) {
        this.preferredJobTypes = preferredJobTypes;
    }

    public List<Integer> getPreferedLocations() {
        return preferedLocations;
    }

    public void setPreferedLocations(List<Integer> preferedLocations) {
        this.preferedLocations = preferedLocations;
    }

    public Boolean getCanWorkRemotely() {
        return canWorkRemotely;
    }

    public void setCanWorkRemotely(Boolean canWorkRemotely) {
        this.canWorkRemotely = canWorkRemotely;
    }

    public Integer getExpectedCtcLakhs() {
        return expectedCtcLakhs;
    }

    public void setExpectedCtcLakhs(Integer expectedCtcLakhs) {
        this.expectedCtcLakhs = expectedCtcLakhs;
    }

    public Integer getExpectedCtcThousands() {
        return expectedCtcThousands;
    }

    public void setExpectedCtcThousands(Integer expectedCtcThousands) {
        this.expectedCtcThousands = expectedCtcThousands;
    }

    public Boolean getIsCTCNegotiable() {
        return isCTCNegotiable;
    }

    public void setIsCTCNegotiable(Boolean isCTCNegotiable) {
        this.isCTCNegotiable = isCTCNegotiable;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public CurrentLocation getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(CurrentLocation currentLocation) {
        this.currentLocation = currentLocation;
    }

    public PreferredFunctionalArea getPreferredFunctionalArea() {
        return preferredFunctionalArea;
    }

    public void setPreferredFunctionalArea(PreferredFunctionalArea preferredFunctionalArea) {
        this.preferredFunctionalArea = preferredFunctionalArea;
    }

    public PreferredIndustry getPreferredIndustry() {
        return preferredIndustry;
    }

    public void setPreferredIndustry(PreferredIndustry preferredIndustry) {
        this.preferredIndustry = preferredIndustry;
    }

    public CurrentCompanyDetails getCurrentCompanyDetails() {
        return currentCompanyDetails;
    }

    public void setCurrentCompanyDetails(CurrentCompanyDetails currentCompanyDetails) {
        this.currentCompanyDetails = currentCompanyDetails;
    }

}
