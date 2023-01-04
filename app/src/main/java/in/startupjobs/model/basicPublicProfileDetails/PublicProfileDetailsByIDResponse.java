
package in.startupjobs.model.basicPublicProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PublicProfileDetailsByIDResponse implements Serializable {

    @SerializedName("account")
    @Expose
    private Account account;
    @SerializedName("contactDetails")
    @Expose
    private ContactDetails contactDetails;
    @SerializedName("resumes")
    @Expose
    private List<String> resumes = null;
    @SerializedName("socalLinks")
    @Expose
    private SocalLinks socalLinks;
    @SerializedName("achievements")
    @Expose
    private String achievements;
    @SerializedName("workExperiences")
    @Expose
    private List<WorkExperience> workExperiences = null;
    @SerializedName("educationQualifications")
    @Expose
    private List<EducationQualification> educationQualifications = null;
    @SerializedName("skills")
    @Expose
    private List<Skill> skills = null;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

    public List<String> getResumes() {
        return resumes;
    }

    public void setResumes(List<String> resumes) {
        this.resumes = resumes;
    }

    public SocalLinks getSocalLinks() {
        return socalLinks;
    }

    public void setSocalLinks(SocalLinks socalLinks) {
        this.socalLinks = socalLinks;
    }

    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) {
        this.achievements = achievements;
    }

    public List<WorkExperience> getWorkExperiences() {
        return workExperiences;
    }

    public void setWorkExperiences(List<WorkExperience> workExperiences) {
        this.workExperiences = workExperiences;
    }

    public List<EducationQualification> getEducationQualifications() {
        return educationQualifications;
    }

    public void setEducationQualifications(List<EducationQualification> educationQualifications) {
        this.educationQualifications = educationQualifications;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

}
