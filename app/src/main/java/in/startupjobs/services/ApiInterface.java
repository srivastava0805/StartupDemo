package in.startupjobs.services;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.model.applyJob.ApplyJobResponseModel;
import in.startupjobs.model.applyJob.CandidateAppliedJobsResponse;
import in.startupjobs.model.employersList.EmployersListResponse;
import in.startupjobs.model.jobListing.JobListingResponseModel;
import in.startupjobs.model.login.LoginResponseModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {
    @POST("generateotp")
    Call<OtpResponseModel> getOtp(
            @Body OtpResponseModel.OtpDataToSend OtpData);

    @POST("registercandidate")
    Call<RegistrationResponseModel> registerCandidate(
            @Body RegistrationResponseModel.RegistrationDataToSend registrationData);

    @POST("getAllJobPostDetailsList")
    Call<JobListingResponseModel> getAllJobs(
            @Body JobListingResponseModel.JobListingDataToSend jobListingData);

    @POST("getcandidatelogin")
    Call<LoginResponseModel> loginCandidate(
            @Body LoginResponseModel.LoginDataToSend loginData);

    @POST("candidatelogin")
    Call<LoginResponseModel> loginCandidateWithProvider(
            @Body LoginResponseModel.LoginDataToSend loginData);

    @Multipart
    @POST("addUpdateCandidateDetails")
    Call<ApplyJobResponseModel> applyForJob(@Part MultipartBody.Part CANDIDATECVFILE,
                                            @Part("fullName") RequestBody fullName,
                                            @Part("Email") RequestBody Email,
                                            @Part("mobile") RequestBody mobile,
                                            @Part("address") RequestBody address,
                                            @Part("candidateSummary") RequestBody candidateSummary,
                                            @Part("CandidateCurrentEmp") RequestBody CandidateCurrentEmp,
                                            @Part("CandidateEduBackup") RequestBody CandidateEduBackup,
                                            @Part("ID") RequestBody ID,
                                            @Part("EMPID") RequestBody EMPID,
                                            @Part("JOBID") RequestBody JOBID);

    @GET("getAllEmployerDetailsList")
    Call<EmployersListResponse> getEmployersList();

    @FormUrlEncoded
    @POST("getAppliedJobByCandidateEmail")
    Call<CandidateAppliedJobsResponse> getAppliedJobs(
            @Field("candidateEmail") String candidateEmail);
}
