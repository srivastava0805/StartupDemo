
package in.startupjobs.model.editProfile.Professional;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CurrentCompany implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("locations")
    @Expose
    private List<Integer> locations = null;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("contactEmail")
    @Expose
    private String contactEmail;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("productsServices")
    @Expose
    private String productsServices;
    @SerializedName("socialFacebook")
    @Expose
    private Object socialFacebook;
    @SerializedName("socialInstagram")
    @Expose
    private Object socialInstagram;
    @SerializedName("socialTwitter")
    @Expose
    private Object socialTwitter;
    @SerializedName("socialYoutube")
    @Expose
    private Object socialYoutube;
    @SerializedName("socialLinkedIn")
    @Expose
    private Object socialLinkedIn;
    @SerializedName("incorporatedDate")
    @Expose
    private String incorporatedDate;
    @SerializedName("employeeStrength")
    @Expose
    private String employeeStrength;
    @SerializedName("businessType")
    @Expose
    private String businessType;
    @SerializedName("fundingStage")
    @Expose
    private String fundingStage;
    @SerializedName("fundingRaised")
    @Expose
    private String fundingRaised;
    @SerializedName("revenueStatus")
    @Expose
    private String revenueStatus;
    @SerializedName("founders")
    @Expose
    private List<Founder> founders = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("industry")
    @Expose
    private Industry industry;
    private final static long serialVersionUID = 6741907245425208460L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getLocations() {
        return locations;
    }

    public void setLocations(List<Integer> locations) {
        this.locations = locations;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductsServices() {
        return productsServices;
    }

    public void setProductsServices(String productsServices) {
        this.productsServices = productsServices;
    }

    public Object getSocialFacebook() {
        return socialFacebook;
    }

    public void setSocialFacebook(Object socialFacebook) {
        this.socialFacebook = socialFacebook;
    }

    public Object getSocialInstagram() {
        return socialInstagram;
    }

    public void setSocialInstagram(Object socialInstagram) {
        this.socialInstagram = socialInstagram;
    }

    public Object getSocialTwitter() {
        return socialTwitter;
    }

    public void setSocialTwitter(Object socialTwitter) {
        this.socialTwitter = socialTwitter;
    }

    public Object getSocialYoutube() {
        return socialYoutube;
    }

    public void setSocialYoutube(Object socialYoutube) {
        this.socialYoutube = socialYoutube;
    }

    public Object getSocialLinkedIn() {
        return socialLinkedIn;
    }

    public void setSocialLinkedIn(Object socialLinkedIn) {
        this.socialLinkedIn = socialLinkedIn;
    }

    public String getIncorporatedDate() {
        return incorporatedDate;
    }

    public void setIncorporatedDate(String incorporatedDate) {
        this.incorporatedDate = incorporatedDate;
    }

    public String getEmployeeStrength() {
        return employeeStrength;
    }

    public void setEmployeeStrength(String employeeStrength) {
        this.employeeStrength = employeeStrength;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getFundingStage() {
        return fundingStage;
    }

    public void setFundingStage(String fundingStage) {
        this.fundingStage = fundingStage;
    }

    public String getFundingRaised() {
        return fundingRaised;
    }

    public void setFundingRaised(String fundingRaised) {
        this.fundingRaised = fundingRaised;
    }

    public String getRevenueStatus() {
        return revenueStatus;
    }

    public void setRevenueStatus(String revenueStatus) {
        this.revenueStatus = revenueStatus;
    }

    public List<Founder> getFounders() {
        return founders;
    }

    public void setFounders(List<Founder> founders) {
        this.founders = founders;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

}
