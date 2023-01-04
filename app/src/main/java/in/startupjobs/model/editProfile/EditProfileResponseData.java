
package in.startupjobs.model.editProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditProfileResponseData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("employerType")
    @Expose
    private Object employerType;
    @SerializedName("designation")
    @Expose
    private Object designation;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("socialAccType")
    @Expose
    private Object socialAccType;
    @SerializedName("socialAccId")
    @Expose
    private Object socialAccId;
    @SerializedName("isEmailAddressVerified")
    @Expose
    private Boolean isEmailAddressVerified;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("isMobileVerified")
    @Expose
    private Boolean isMobileVerified;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("deviceType")
    @Expose
    private Object deviceType;
    @SerializedName("deviceId")
    @Expose
    private Object deviceId;
    @SerializedName("pushToken")
    @Expose
    private Object pushToken;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("socialFacebook")
    @Expose
    private Object socialFacebook;
    @SerializedName("socialInstagram")
    @Expose
    private Object socialInstagram;
    @SerializedName("socialTwitter")
    @Expose
    private Object socialTwitter;
    @SerializedName("socialWebsite")
    @Expose
    private Object socialWebsite;
    @SerializedName("socialLinkedIn")
    @Expose
    private String socialLinkedIn;
    @SerializedName("languages")
    @Expose
    private Object languages;
    @SerializedName("notificationSettings")
    @Expose
    private NotificationSettings notificationSettings;
    @SerializedName("ip")
    @Expose
    private Object ip;
    @SerializedName("lastLoggedIn")
    @Expose
    private String lastLoggedIn;
    @SerializedName("isActive")
    @Expose
    private Boolean isActive;
    @SerializedName("isBlocked")
    @Expose
    private Boolean isBlocked;
    @SerializedName("createdOn")
    @Expose
    private String createdOn;
    @SerializedName("termsAccepted")
    @Expose
    private String termsAccepted;
    @SerializedName("refreshToken")
    @Expose
    private Object refreshToken;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Object getEmployerType() {
        return employerType;
    }

    public void setEmployerType(Object employerType) {
        this.employerType = employerType;
    }

    public Object getDesignation() {
        return designation;
    }

    public void setDesignation(Object designation) {
        this.designation = designation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getSocialAccType() {
        return socialAccType;
    }

    public void setSocialAccType(Object socialAccType) {
        this.socialAccType = socialAccType;
    }

    public Object getSocialAccId() {
        return socialAccId;
    }

    public void setSocialAccId(Object socialAccId) {
        this.socialAccId = socialAccId;
    }

    public Boolean getIsEmailAddressVerified() {
        return isEmailAddressVerified;
    }

    public void setIsEmailAddressVerified(Boolean isEmailAddressVerified) {
        this.isEmailAddressVerified = isEmailAddressVerified;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getIsMobileVerified() {
        return isMobileVerified;
    }

    public void setIsMobileVerified(Boolean isMobileVerified) {
        this.isMobileVerified = isMobileVerified;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Object deviceType) {
        this.deviceType = deviceType;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Object getPushToken() {
        return pushToken;
    }

    public void setPushToken(Object pushToken) {
        this.pushToken = pushToken;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
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

    public Object getSocialWebsite() {
        return socialWebsite;
    }

    public void setSocialWebsite(Object socialWebsite) {
        this.socialWebsite = socialWebsite;
    }

    public String getSocialLinkedIn() {
        return socialLinkedIn;
    }

    public void setSocialLinkedIn(String socialLinkedIn) {
        this.socialLinkedIn = socialLinkedIn;
    }

    public Object getLanguages() {
        return languages;
    }

    public void setLanguages(Object languages) {
        this.languages = languages;
    }

    public NotificationSettings getNotificationSettings() {
        return notificationSettings;
    }

    public void setNotificationSettings(NotificationSettings notificationSettings) {
        this.notificationSettings = notificationSettings;
    }

    public Object getIp() {
        return ip;
    }

    public void setIp(Object ip) {
        this.ip = ip;
    }

    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(String lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getTermsAccepted() {
        return termsAccepted;
    }

    public void setTermsAccepted(String termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public Object getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(Object refreshToken) {
        this.refreshToken = refreshToken;
    }

}
