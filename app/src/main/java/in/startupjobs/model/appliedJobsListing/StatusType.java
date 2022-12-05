
package in.startupjobs.model.appliedJobsListing;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StatusType {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("entityType")
    @Expose
    private String entityType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
