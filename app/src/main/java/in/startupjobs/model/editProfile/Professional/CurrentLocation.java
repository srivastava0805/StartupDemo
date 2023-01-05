
package in.startupjobs.model.editProfile.Professional;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CurrentLocation implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("districtName")
    @Expose
    private String districtName;
    private final static long serialVersionUID = -7881228681113017483L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

}
