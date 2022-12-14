
package in.startupjobs.model.basicPublicProfileDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class University {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("universityName")
    @Expose
    private String universityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

}
