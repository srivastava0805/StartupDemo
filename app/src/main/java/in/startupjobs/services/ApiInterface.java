package in.startupjobs.services;

import java.util.List;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;
import in.startupjobs.model.appliedJobsListing.JobListingResponseModel;
import in.startupjobs.model.applyJob.ApplyJobResponseModel;
import in.startupjobs.model.applyJob.CandidateAppliedJobsResponse;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.dashBoardData.DashBoardJobsData;
import in.startupjobs.model.employersList.EmployersListResponse;
import in.startupjobs.model.forgotPassword.ChangePasswordModel;
import in.startupjobs.model.login.LoginDataForMobile;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("csrf/token")
    Call<Object> getCsrfToken();

    @POST("verifications/send")
    Call<OtpResponseModel> getOtp(
            @Body OtpResponseModel.OtpDataToSend OtpData);

    @POST("verifications/verify")
    Call<OtpResponseModel> verifyOtp(
            @Body OtpResponseModel.VerifyOtpData OtpData);

    @POST("auth/password/reset")
    Call<Object> postChangePassword(@Body ChangePasswordModel.OtpDataToSend otpData);

    @POST("auth/register/candidate")
    Call<RegistrationResponseModel> registerCandidate(
            @Body RegistrationResponseModel.RegistrationDataToSend registrationData);

    @POST("getAllJobPostDetailsList")
    Call<JobListingResponseModel> getAllJobs(
            @Body JobListingResponseModel.JobListingDataToSend jobListingData);

    @POST("auth/login/mobile/verify")
    Call<LoginResponseModel> loginViaMobile(
            @Body LoginDataForMobile loginDataForMobile);

    @POST("auth/login")
    Call<LoginResponseModel> loginViaEmail(
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

    @GET("jobs/candidate/appliedJobs")
    Call<List<AppliedJobsResponse>> getAppliedJobs();

    @GET("auth/dashboard/candidate")
    Call<DashBoardJobsData> getDashBoardJobsData();

    @GET("resume/work_experience")
    Call<List<WorkExperienceResponse>> getWorkExperience();

    @GET("resume/public/{id}")
    Call<PublicProfileDetailsByIDResponse>
    getBasicPublicProfileDetailsById(@Path("id") String userId);

    @FormUrlEncoded
    @POST("getAppliedJobByCandidateEmail")
    Call<CandidateAppliedJobsResponse> getAppliedJobs(
            @Field("candidateEmail") String candidateEmail);


}
