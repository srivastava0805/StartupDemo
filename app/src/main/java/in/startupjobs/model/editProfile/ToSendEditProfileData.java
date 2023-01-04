
package in.startupjobs.model.editProfile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ToSendEditProfileData {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("gender")
    @Expose
    private String gender;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

}
