
package in.startupjobs.model.serachedJobs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("total_records")
    @Expose
    private Integer totalRecords;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("next_page")
    @Expose
    private Object nextPage;
    @SerializedName("has_more_data")
    @Expose
    private Boolean hasMoreData;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Object getNextPage() {
        return nextPage;
    }

    public void setNextPage(Object nextPage) {
        this.nextPage = nextPage;
    }

    public Boolean getHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(Boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

}
