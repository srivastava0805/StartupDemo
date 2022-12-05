
package in.startupjobs.model.basicPublicProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EducationQualification {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("institutionName")
    @Expose
    private String institutionName;
    @SerializedName("completionYear")
    @Expose
    private String completionYear;
    @SerializedName("educationLevel")
    @Expose
    private String educationLevel;
    @SerializedName("degreeName")
    @Expose
    private String degreeName;
    @SerializedName("degreeSpecialization")
    @Expose
    private String degreeSpecialization;
    @SerializedName("courseMode")
    @Expose
    private String courseMode;
    @SerializedName("roleDescription")
    @Expose
    private String roleDescription;
    @SerializedName("currentlyStudying")
    @Expose
    private Boolean currentlyStudying;
    @SerializedName("institution")
    @Expose
    private Institution institution;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getCompletionYear() {
        return completionYear;
    }

    public void setCompletionYear(String completionYear) {
        this.completionYear = completionYear;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public String getDegreeSpecialization() {
        return degreeSpecialization;
    }

    public void setDegreeSpecialization(String degreeSpecialization) {
        this.degreeSpecialization = degreeSpecialization;
    }

    public String getCourseMode() {
        return courseMode;
    }

    public void setCourseMode(String courseMode) {
        this.courseMode = courseMode;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public Boolean getCurrentlyStudying() {
        return currentlyStudying;
    }

    public void setCurrentlyStudying(Boolean currentlyStudying) {
        this.currentlyStudying = currentlyStudying;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

}
