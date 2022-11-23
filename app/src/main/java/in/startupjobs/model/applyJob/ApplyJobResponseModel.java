
package in.startupjobs.model.applyJob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyJobResponseModel {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("message")
    @Expose
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class ApplyJobDataToSend {

        @SerializedName("ID")
        @Expose
        private String id;
        @SerializedName("fullName")
        @Expose
        private String fullName;
        @SerializedName("Email")
        @Expose
        private String email;
        @SerializedName("mobile")
        @Expose
        private String mobile;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("candidateSummary")
        @Expose
        private String candidateSummary;
        @SerializedName("CandidateCurrentEmp")
        @Expose
        private String candidateCurrentEmp;
        @SerializedName("CandidateEduBackup")
        @Expose
        private String candidateEduBackup;
        @SerializedName("EMPID")
        @Expose
        private String empid;
        @SerializedName("JOBID")
        @Expose
        private String jobid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCandidateSummary() {
            return candidateSummary;
        }

        public void setCandidateSummary(String candidateSummary) {
            this.candidateSummary = candidateSummary;
        }

        public String getCandidateCurrentEmp() {
            return candidateCurrentEmp;
        }

        public void setCandidateCurrentEmp(String candidateCurrentEmp) {
            this.candidateCurrentEmp = candidateCurrentEmp;
        }

        public String getCandidateEduBackup() {
            return candidateEduBackup;
        }

        public void setCandidateEduBackup(String candidateEduBackup) {
            this.candidateEduBackup = candidateEduBackup;
        }

        public String getEmpid() {
            return empid;
        }

        public void setEmpid(String empid) {
            this.empid = empid;
        }

        public String getJobid() {
            return jobid;
        }

        public void setJobid(String jobid) {
            this.jobid = jobid;
        }
    }


}
