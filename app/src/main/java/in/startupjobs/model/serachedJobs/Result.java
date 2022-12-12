
package in.startupjobs.model.serachedJobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("locations")
    @Expose
    private List<String> locations = null;
    @SerializedName("workType")
    @Expose
    private String workType;
    @SerializedName("jobDescription")
    @Expose
    private String jobDescription;
    @SerializedName("videoLink")
    @Expose
    private Object videoLink;
    @SerializedName("positionOpen")
    @Expose
    private Integer positionOpen;
    @SerializedName("skills")
    @Expose
    private List<String> skills = null;
    @SerializedName("jobFullPartTime")
    @Expose
    private String jobFullPartTime;
    @SerializedName("salaryMin")
    @Expose
    private Integer salaryMin;
    @SerializedName("salaryMax")
    @Expose
    private Integer salaryMax;
    @SerializedName("showSalaryOnJobPost")
    @Expose
    private Boolean showSalaryOnJobPost;
    @SerializedName("salaryTenure")
    @Expose
    private String salaryTenure;
    @SerializedName("experienceMin")
    @Expose
    private Integer experienceMin;
    @SerializedName("experienceMax")
    @Expose
    private Integer experienceMax;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("company")
    @Expose
    private Company company;
    @SerializedName("functionalArea")
    @Expose
    private FunctionalArea functionalArea;
    @SerializedName("industry")
    @Expose
    private Industry industry;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("jobType")
    @Expose
    private JobType jobType;
    @SerializedName("locationNames")
    @Expose
    private List<LocationName> locationNames = null;
    @SerializedName("skillNames")
    @Expose
    private List<SkillName> skillNames = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Object getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(Object videoLink) {
        this.videoLink = videoLink;
    }

    public Integer getPositionOpen() {
        return positionOpen;
    }

    public void setPositionOpen(Integer positionOpen) {
        this.positionOpen = positionOpen;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getJobFullPartTime() {
        return jobFullPartTime;
    }

    public void setJobFullPartTime(String jobFullPartTime) {
        this.jobFullPartTime = jobFullPartTime;
    }

    public Integer getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Integer salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Integer getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Integer salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Boolean getShowSalaryOnJobPost() {
        return showSalaryOnJobPost;
    }

    public void setShowSalaryOnJobPost(Boolean showSalaryOnJobPost) {
        this.showSalaryOnJobPost = showSalaryOnJobPost;
    }

    public String getSalaryTenure() {
        return salaryTenure;
    }

    public void setSalaryTenure(String salaryTenure) {
        this.salaryTenure = salaryTenure;
    }

    public Integer getExperienceMin() {
        return experienceMin;
    }

    public void setExperienceMin(Integer experienceMin) {
        this.experienceMin = experienceMin;
    }

    public Integer getExperienceMax() {
        return experienceMax;
    }

    public void setExperienceMax(Integer experienceMax) {
        this.experienceMax = experienceMax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public FunctionalArea getFunctionalArea() {
        return functionalArea;
    }

    public void setFunctionalArea(FunctionalArea functionalArea) {
        this.functionalArea = functionalArea;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public List<LocationName> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<LocationName> locationNames) {
        this.locationNames = locationNames;
    }

    public List<SkillName> getSkillNames() {
        return skillNames;
    }

    public void setSkillNames(List<SkillName> skillNames) {
        this.skillNames = skillNames;
    }

}
