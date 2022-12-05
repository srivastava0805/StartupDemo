
package in.startupjobs.model.appliedJobsListing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetAllJobPostDetails implements Serializable {

    @SerializedName("JOBID")
    @Expose
    private int jobid;
    @SerializedName("JOBTITLE")
    @Expose
    private String jobtitle;
    @SerializedName("LOCATION")
    @Expose
    private String location;
    @SerializedName("JOBTYPEID")
    @Expose
    private int jobtypeid;
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
    private int jobpositions;
    @SerializedName("INDUSTRY")
    @Expose
    private String industry;
    @SerializedName("FUNCTIONS")
    @Expose
    private String functions;
    @SerializedName("JOBPOSTDATE")
    @Expose
    private String jobpostdate;
    @SerializedName("EMPLOYERID")
    @Expose
    private String employerid;
    @SerializedName("JOBCITY")
    @Expose
    private Object jobcity;
    @SerializedName("JOBSTATE")
    @Expose
    private Object jobstate;
    @SerializedName("JOBCOUNTRY")
    @Expose
    private Object jobcountry;
    @SerializedName("JOBPINCODE")
    @Expose
    private Object jobpincode;
    @SerializedName("JOBADDRESS")
    @Expose
    private Object jobaddress;
    @SerializedName("PROVIDENEWADDRESS")
    @Expose
    private Object providenewaddress;
    @SerializedName("MAXAMOUNT")
    @Expose
    private Object maxamount;
    @SerializedName("MAXYEAREXP")
    @Expose
    private Object maxyearexp;
    @SerializedName("EMPID")
    @Expose
    private int empid;
    @SerializedName("EMPLOYERMAILID")
    @Expose
    private String employermailid;
    @SerializedName("EMPLOYERNAME")
    @Expose
    private String employername;
    @SerializedName("EMPLOYERURL")
    @Expose
    private String employerurl;
    @SerializedName("EMPLOYERADDRESS")
    @Expose
    private String employeraddress;
    @SerializedName("EMPLOYERLOGOFILE")
    @Expose
    private String employerlogofile;
    @SerializedName("EMPLOYERDESCRIPTION")
    @Expose
    private String employerdescription;
    @SerializedName("EMPLOYERSTRENGTH")
    @Expose
    private int employerstrength;
    @SerializedName("EMPLOYERYEARINC")
    @Expose
    private int employeryearinc;
    @SerializedName("EMPLOYERFUNDS")
    @Expose
    private int employerfunds;
    @SerializedName("CITY")
    @Expose
    private Object city;
    @SerializedName("STATE")
    @Expose
    private Object state;
    @SerializedName("COUNTRY")
    @Expose
    private Object country;
    @SerializedName("PINCODE")
    @Expose
    private Object pincode;
    @SerializedName("ADDRESS")
    @Expose
    private Object address;
    @SerializedName("companyBradingURL")
    @Expose
    private Object companyBradingURL;
    @SerializedName("STATUS")
    @Expose
    private String status;

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
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

    public int getJobtypeid() {
        return jobtypeid;
    }

    public void setJobtypeid(int jobtypeid) {
        this.jobtypeid = jobtypeid;
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

    public int getJobpositions() {
        return jobpositions;
    }

    public void setJobpositions(int jobpositions) {
        this.jobpositions = jobpositions;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getJobpostdate() {
        return jobpostdate;
    }

    public void setJobpostdate(String jobpostdate) {
        this.jobpostdate = jobpostdate;
    }

    public String getEmployerid() {
        return employerid;
    }

    public void setEmployerid(String employerid) {
        this.employerid = employerid;
    }

    public Object getJobcity() {
        return jobcity;
    }

    public void setJobcity(Object jobcity) {
        this.jobcity = jobcity;
    }

    public Object getJobstate() {
        return jobstate;
    }

    public void setJobstate(Object jobstate) {
        this.jobstate = jobstate;
    }

    public Object getJobcountry() {
        return jobcountry;
    }

    public void setJobcountry(Object jobcountry) {
        this.jobcountry = jobcountry;
    }

    public Object getJobpincode() {
        return jobpincode;
    }

    public void setJobpincode(Object jobpincode) {
        this.jobpincode = jobpincode;
    }

    public Object getJobaddress() {
        return jobaddress;
    }

    public void setJobaddress(Object jobaddress) {
        this.jobaddress = jobaddress;
    }

    public Object getProvidenewaddress() {
        return providenewaddress;
    }

    public void setProvidenewaddress(Object providenewaddress) {
        this.providenewaddress = providenewaddress;
    }

    public Object getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(Object maxamount) {
        this.maxamount = maxamount;
    }

    public Object getMaxyearexp() {
        return maxyearexp;
    }

    public void setMaxyearexp(Object maxyearexp) {
        this.maxyearexp = maxyearexp;
    }

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
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

    public String getEmployerurl() {
        return employerurl;
    }

    public void setEmployerurl(String employerurl) {
        this.employerurl = employerurl;
    }

    public String getEmployeraddress() {
        return employeraddress;
    }

    public void setEmployeraddress(String employeraddress) {
        this.employeraddress = employeraddress;
    }

    public String getEmployerlogofile() {
        return employerlogofile;
    }

    public void setEmployerlogofile(String employerlogofile) {
        this.employerlogofile = employerlogofile;
    }

    public String getEmployerdescription() {
        return employerdescription;
    }

    public void setEmployerdescription(String employerdescription) {
        this.employerdescription = employerdescription;
    }

    public int getEmployerstrength() {
        return employerstrength;
    }

    public void setEmployerstrength(int employerstrength) {
        this.employerstrength = employerstrength;
    }

    public int getEmployeryearinc() {
        return employeryearinc;
    }

    public void setEmployeryearinc(int employeryearinc) {
        this.employeryearinc = employeryearinc;
    }

    public int getEmployerfunds() {
        return employerfunds;
    }

    public void setEmployerfunds(int employerfunds) {
        this.employerfunds = employerfunds;
    }

    public Object getCity() {
        return city;
    }

    public void setCity(Object city) {
        this.city = city;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public Object getPincode() {
        return pincode;
    }

    public void setPincode(Object pincode) {
        this.pincode = pincode;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCompanyBradingURL() {
        return companyBradingURL;
    }

    public void setCompanyBradingURL(Object companyBradingURL) {
        this.companyBradingURL = companyBradingURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
