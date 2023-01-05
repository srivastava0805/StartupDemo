
package in.startupjobs.model.editProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ToSendWorkExpData implements Serializable {

    @SerializedName("companyId")
    @Expose
    private Object companyId;
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
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("currentlyEmployed")
    @Expose
    private Boolean currentlyEmployed;
    private final static long serialVersionUID = -4792941210173807780L;

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public void setCurrentlyEmployed(Boolean currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

}
