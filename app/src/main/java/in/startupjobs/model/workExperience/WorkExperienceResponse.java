
package in.startupjobs.model.workExperience;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WorkExperienceResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("currentCtcLakhs")
    @Expose
    private Integer currentCtcLakhs;
    @SerializedName("currentCtcThousands")
    @Expose
    private Integer currentCtcThousands;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("roleDescription")
    @Expose
    private String roleDescription;
    @SerializedName("currentlyWorking")
    @Expose
    private Boolean currentlyWorking;
    @SerializedName("lastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("company")
    @Expose
    private Company company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getCurrentCtcLakhs() {
        return currentCtcLakhs;
    }

    public void setCurrentCtcLakhs(Integer currentCtcLakhs) {
        this.currentCtcLakhs = currentCtcLakhs;
    }

    public Integer getCurrentCtcThousands() {
        return currentCtcThousands;
    }

    public void setCurrentCtcThousands(Integer currentCtcThousands) {
        this.currentCtcThousands = currentCtcThousands;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Boolean getCurrentlyWorking() {
        return currentlyWorking;
    }

    public void setCurrentlyWorking(Boolean currentlyWorking) {
        this.currentlyWorking = currentlyWorking;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
