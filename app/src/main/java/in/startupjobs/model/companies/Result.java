
package in.startupjobs.model.companies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import in.startupjobs.model.serachedJobs.Industry;
import in.startupjobs.model.serachedJobs.LocationName;

public class Result {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("locations")
    @Expose
    private List<String> locations = null;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("productsServices")
    @Expose
    private Object productsServices;
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
    private Object incorporatedDate;
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
    private Object founders;
    @SerializedName("industry")
    @Expose
    private Industry industry;
    @SerializedName("locationNames")
    @Expose
    private List<LocationName> locationNames = null;
    @SerializedName("totalJobs")
    @Expose
    private Integer totalJobs;

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

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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

    public Object getProductsServices() {
        return productsServices;
    }

    public void setProductsServices(Object productsServices) {
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

    public Object getIncorporatedDate() {
        return incorporatedDate;
    }

    public void setIncorporatedDate(Object incorporatedDate) {
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

    public Object getFounders() {
        return founders;
    }

    public void setFounders(Object founders) {
        this.founders = founders;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public List<LocationName> getLocationNames() {
        return locationNames;
    }

    public void setLocationNames(List<LocationName> locationNames) {
        this.locationNames = locationNames;
    }

    public Integer getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(Integer totalJobs) {
        this.totalJobs = totalJobs;
    }

}