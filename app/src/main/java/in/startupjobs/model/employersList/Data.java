
package in.startupjobs.model.employersList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("getAllEmployerDetailsList")
    @Expose
    private List<GetAllEmployerDetails> getAllEmployerDetailsList = null;

    public List<GetAllEmployerDetails> getGetAllEmployerDetailsList() {
        return getAllEmployerDetailsList;
    }

    public void setGetAllEmployerDetailsList(List<GetAllEmployerDetails> getAllEmployerDetailsList) {
        this.getAllEmployerDetailsList = getAllEmployerDetailsList;
    }

}
