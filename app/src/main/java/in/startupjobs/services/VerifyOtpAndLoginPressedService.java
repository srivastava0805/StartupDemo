package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.login.LoginDataForMobile;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpAndLoginPressedService {
    private final ProgressDialog progressDialog;
    onResponseVerifyMobileOtp onResponseVerifyMobileOtp;

    public interface onResponseVerifyMobileOtp {
        void sendMobileOtpResponse(LoginResponseModel otpResponseModel);
    }

    public VerifyOtpAndLoginPressedService(Activity context, String value,
                                           onResponseVerifyMobileOtp onResponseVerifyMobileOtpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        LoginDataForMobile otpData = new LoginDataForMobile();
        otpData.setToken(value);
        Call<LoginResponseModel> call = service.loginViaMobile(otpData);
        call.enqueue(new Callback<LoginResponseModel>() {

            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseVerifyMobileOtpCallback.sendMobileOtpResponse(response.body());
                    progressDialog.dismiss();

                    if (response.body().getEmail().equalsIgnoreCase("Otp Already Exists.")) {
                        Snackbar.make(context.findViewById(android.R.id.content), "OTP already sent to the provided email. Please check", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(context.findViewById(android.R.id.content), "OTP sent successfully", Snackbar.LENGTH_SHORT).show();
                    }

                } else {
                    onResponseVerifyMobileOtpCallback.sendMobileOtpResponse(null);
                    progressDialog.dismiss();
                    Snackbar.make(context.findViewById(android.R.id.content), "Call error with HTTP status code " + response.code() + "!", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
