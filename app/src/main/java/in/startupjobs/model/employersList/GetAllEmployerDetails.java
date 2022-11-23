package in.startupjobs.model.employersList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllEmployerDetails {

    @SerializedName("EMPID")
    @Expose
    private int empid;
    @SerializedName("EMPLOYERID")
    @Expose
    private String employerid;
    @SerializedName("EMPLOYERMAILID")
    @Expose
    private String employermailid;
    @SerializedName("EMPLOYERPASSWORD")
    @Expose
    private String employerpassword;
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
    private float employerfunds;
    @SerializedName("CITY")
    @Expose
    private String city;
    @SerializedName("STATE")
    @Expose
    private String state;
    @SerializedName("COUNTRY")
    @Expose
    private String country;
    @SerializedName("PINCODE")
    @Expose
    private String pincode;
    @SerializedName("ADDRESS")
    @Expose
    private String address;
    @SerializedName("companyBradingURL")
    @Expose
    private Object companyBradingURL;

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getEmployerid() {
        return employerid;
    }

    public void setEmployerid(String employerid) {
        this.employerid = employerid;
    }

    public String getEmployermailid() {
        return employermailid;
    }

    public void setEmployermailid(String employermailid) {
        this.employermailid = employermailid;
    }

    public String getEmployerpassword() {
        return employerpassword;
    }

    public void setEmployerpassword(String employerpassword) {
        this.employerpassword = employerpassword;
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

    public float getEmployerfunds() {
        return employerfunds;
    }

    public void setEmployerfunds(int employerfunds) {
        this.employerfunds = employerfunds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getCompanyBradingURL() {
        return companyBradingURL;
    }

    public void setCompanyBradingURL(Object companyBradingURL) {
        this.companyBradingURL = companyBradingURL;
    }

}
