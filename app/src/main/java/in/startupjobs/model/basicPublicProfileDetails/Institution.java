
package in.startupjobs.model.basicPublicProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Institution implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("collegeName")
    @Expose
    private String collegeName;
    @SerializedName("university")
    @Expose
    private University university;
    @SerializedName("district")
    @Expose
    private District district;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

}
