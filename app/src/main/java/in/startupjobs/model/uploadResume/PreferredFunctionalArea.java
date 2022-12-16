
package in.startupjobs.model.uploadResume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferredFunctionalArea {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("areaName")
    @Expose
    private String areaName;

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
