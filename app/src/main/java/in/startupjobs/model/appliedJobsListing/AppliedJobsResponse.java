
package in.startupjobs.model.appliedJobsListing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppliedJobsResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("cvFile")
    @Expose
    private String cvFile;
    @SerializedName("candidateName")
    @Expose
    private String candidateName;
    @SerializedName("candidateEmail")
    @Expose
    private String candidateEmail;
    @SerializedName("candidateCreatedOn")
    @Expose
    private String candidateCreatedOn;
    @SerializedName("currentDesignation")
    @Expose
    private String currentDesignation;
    @SerializedName("candidateMobile")
    @Expose
    private String candidateMobile;
    @SerializedName("employerName")
    @Expose
    private String employerName;
    @SerializedName("employerUserType")
    @Expose
    private String employerUserType;
    @SerializedName("employerAvatar")
    @Expose
    private String employerAvatar;
    @SerializedName("employerMobileNumber")
    @Expose
    private String employerMobileNumber;
    @SerializedName("employerEmail")
    @Expose
    private String employerEmail;
    @SerializedName("jobTitle")
    @Expose
    private String jobTitle;
    @SerializedName("jobCompanyId")
    @Expose
    private Integer jobCompanyId;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("companyLogo")
    @Expose
    private String companyLogo;
    @SerializedName("applicationStatuses")
    @Expose
    private List<ApplicationStatus> applicationStatuses = null;
    @SerializedName("applicationStatusTypes")
    @Expose
    private List<ApplicationStatusType> applicationStatusTypes = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getCvFile() {
        return cvFile;
    }

    public void setCvFile(String cvFile) {
        this.cvFile = cvFile;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public String getCandidateCreatedOn() {
        return candidateCreatedOn;
    }

    public void setCandidateCreatedOn(String candidateCreatedOn) {
        this.candidateCreatedOn = candidateCreatedOn;
    }

    public String getCurrentDesignation() {
        return currentDesignation;
    }

    public void setCurrentDesignation(String currentDesignation) {
        this.currentDesignation = currentDesignation;
    }

    public String getCandidateMobile() {
        return candidateMobile;
    }

    public void setCandidateMobile(String candidateMobile) {
        this.candidateMobile = candidateMobile;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerUserType() {
        return employerUserType;
    }

    public void setEmployerUserType(String employerUserType) {
        this.employerUserType = employerUserType;
    }

    public String getEmployerAvatar() {
        return employerAvatar;
    }

    public void setEmployerAvatar(String employerAvatar) {
        this.employerAvatar = employerAvatar;
    }

    public String getEmployerMobileNumber() {
        return employerMobileNumber;
    }

    public void setEmployerMobileNumber(String employerMobileNumber) {
        this.employerMobileNumber = employerMobileNumber;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(Integer jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public List<ApplicationStatus> getApplicationStatuses() {
        return applicationStatuses;
    }

    public void setApplicationStatuses(List<ApplicationStatus> applicationStatuses) {
        this.applicationStatuses = applicationStatuses;
    }

    public List<ApplicationStatusType> getApplicationStatusTypes() {
        return applicationStatusTypes;
    }

    public void setApplicationStatusTypes(List<ApplicationStatusType> applicationStatusTypes) {
        this.applicationStatusTypes = applicationStatusTypes;
    }

}
