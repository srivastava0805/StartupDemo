package in.startupjobs.model.applyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("candidateEmail")
    @Expose
    private String candidateEmail;
    @SerializedName("jobId")
    @Expose
    private Integer jobId;
    @SerializedName("EmpId")
    @Expose
    private Integer empId;
    @SerializedName("Accepted")
    @Expose
    private Object accepted;
    @SerializedName("JOBTITLE")
    @Expose
    private String jobtitle;
    @SerializedName("LOCATION")
    @Expose
    private String location;
    @SerializedName("EXPERIENCERANGE")
    @Expose
    private String experiencerange;
    @SerializedName("JOBDESCRIPTION")
    @Expose
    private String jobdescription;
    @SerializedName("CTCBUDGET")
    @Expose
    private String ctcbudget;
    @SerializedName("JOBPOSITIONS")
    @Expose
    private Integer jobpositions;
    @SerializedName("INDUSTRY")
    @Expose
    private String industry;
    @SerializedName("JOBPOSTDATE")
    @Expose
    private String jobpostdate;
    @SerializedName("EMPLOYERMAILID")
    @Expose
    private String employermailid;
    @SerializedName("EMPLOYERNAME")
    @Expose
    private String employername;
    @SerializedName("EMPLOYERADDRESS")
    @Expose
    private String employeraddress;

    public String getCandidateEmail() {
        return candidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        this.candidateEmail = candidateEmail;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Object getAccepted() {
        return accepted;
    }

    public void setAccepted(Object accepted) {
        this.accepted = accepted;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExperiencerange() {
        return experiencerange;
    }

    public void setExperiencerange(String experiencerange) {
        this.experiencerange = experiencerange;
    }

    public String getJobdescription() {
        return jobdescription;
    }

    public void setJobdescription(String jobdescription) {
        this.jobdescription = jobdescription;
    }

    public String getCtcbudget() {
        return ctcbudget;
    }

    public void setCtcbudget(String ctcbudget) {
        this.ctcbudget = ctcbudget;
    }

    public Integer getJobpositions() {
        return jobpositions;
    }

    public void setJobpositions(Integer jobpositions) {
        this.jobpositions = jobpositions;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getJobpostdate() {
        return jobpostdate;
    }

    public void setJobpostdate(String jobpostdate) {
        this.jobpostdate = jobpostdate;
    }

    public String getEmployermailid() {
        return employermailid;
    }

    public void setEmployermailid(String employermailid) {
        this.employermailid = employermailid;
    }

    public String getEmployername() {
        return employername;
    }

    public void setEmployername(String employername) {
        this.employername = employername;
    }

    public String getEmployeraddress() {
        return employeraddress;
    }

    public void setEmployeraddress(String employeraddress) {
        this.employeraddress = employeraddress;
    }

}
