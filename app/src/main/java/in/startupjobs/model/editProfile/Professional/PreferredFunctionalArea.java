
package in.startupjobs.model.editProfile.Professional;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PreferredFunctionalArea implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("areaName")
    @Expose
    private String areaName;
    private final static long serialVersionUID = 4407365429305031073L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
