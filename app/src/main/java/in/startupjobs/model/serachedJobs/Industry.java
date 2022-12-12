
package in.startupjobs.model.serachedJobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Industry implements Serializable {

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
