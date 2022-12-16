package in.startupjobs.services;

import java.util.List;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;
import in.startupjobs.model.appliedJobsListing.JobListingResponseModel;
import in.startupjobs.model.applyJob.ApplyJobResponseModel;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.companies.GetCompaniesResponse;
import in.startupjobs.model.dashBoardData.DashBoardJobsData;
import in.startupjobs.model.forgotPassword.ChangePasswordModel;
import in.startupjobs.model.login.LoginDataForMobile;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.model.serachedJobs.SearchedJobsResponse;
import in.startupjobs.model.uploadResume.UploadResumeResponse;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("csrf/token")
    Call<Object> getCsrfToken();

    @POST("verifications/send")
    Call<OtpResponseModel> getOtp(
            @Body OtpResponseModel.OtpDataToSend OtpData);

    @POST("verifications/verify")
    Call<OtpResponseModel.VerifyOtpResponseData> verifyOtp(
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
    @PUT("resume/upload")
    Call<UploadResumeResponse> uploadResume(@Part MultipartBody.Part file);

    @GET("jobs/candidate/appliedJobs")
    Call<List<AppliedJobsResponse>> getAppliedJobs();

    @GET("auth/dashboard/candidate")
    Call<DashBoardJobsData> getDashBoardJobsData();

    @GET("resume/work_experience")
    Call<List<WorkExperienceResponse>> getWorkExperience();

    @GET("resume/public/{id}")
    Call<PublicProfileDetailsByIDResponse>
    getBasicPublicProfileDetailsById(@Path("id") String userId);

    @GET("jobs/search?limit=10&")
    Call<SearchedJobsResponse>
    getSearchedJobsByKeyword(@Query("keyword") String tag);

    @GET("companies/search?")
    Call<GetCompaniesResponse>
    getSearchedCompaniesByKeyword(@Query("limit") int count, @Query("keyword") String tag);

    @POST("jobs/apply/{id}")
    Call<ApplyJobResponseModel>
    applyJob(@Path("id") String userId, @Body ApplyJobResponseModel.ApplyJobDataToSend jobDataToSend);

}
