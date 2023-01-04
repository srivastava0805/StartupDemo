
package in.startupjobs.model.basicPublicProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SocalLinks implements Serializable {

    @SerializedName("facebook")
    @Expose
    private Object facebook;
    @SerializedName("instagram")
    @Expose
    private Object instagram;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("linkedIn")
    @Expose
    private String linkedIn;

    public Object getFacebook() {
        return facebook;
    }

    public void setFacebook(Object facebook) {
        this.facebook = facebook;
    }

    public Object getInstagram() {
        return instagram;
    }

    public void setInstagram(Object instagram) {
        this.instagram = instagram;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

}
