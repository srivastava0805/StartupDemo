
package in.startupjobs.model.uploadResume;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreferredIndustry {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("industryName")
    @Expose
    private String industryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

}
