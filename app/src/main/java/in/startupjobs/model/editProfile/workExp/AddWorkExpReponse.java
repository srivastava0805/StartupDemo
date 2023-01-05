
package in.startupjobs.model.editProfile.workExp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddWorkExpReponse implements Serializable {

    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("roleDescription")
    @Expose
    private String roleDescription;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("currentlyWorking")
    @Expose
    private Boolean currentlyWorking;
    @SerializedName("currentCtcLakhs")
    @Expose
    private Integer currentCtcLakhs;
    @SerializedName("currentCtcThousands")
    @Expose
    private Integer currentCtcThousands;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("lastUpdatedOn")
    @Expose
    private String lastUpdatedOn;
    @SerializedName("id")
    @Expose
    private Integer id;
    private final static long serialVersionUID = 7351604950026535367L;

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Boolean getCurrentlyWorking() {
        return currentlyWorking;
    }

    public void setCurrentlyWorking(Boolean currentlyWorking) {
        this.currentlyWorking = currentlyWorking;
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

    public Object getEndDate() {
        return endDate;
    }

    public void setEndDate(Object endDate) {
        this.endDate = endDate;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
