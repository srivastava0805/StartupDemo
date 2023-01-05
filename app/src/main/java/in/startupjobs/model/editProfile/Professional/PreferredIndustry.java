
package in.startupjobs.model.editProfile.Professional;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PreferredIndustry implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("industryName")
    @Expose
    private String industryName;
    private final static long serialVersionUID = -6127354219670518703L;

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
