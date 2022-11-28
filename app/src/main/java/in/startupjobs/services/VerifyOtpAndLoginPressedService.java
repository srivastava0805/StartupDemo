package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.login.LoginDataForMobile;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpAndLoginPressedService {
    private final ProgressDialog progressDialog;
    onResponseVerifyMobileOtp onResponseVerifyMobileOtp;

    public interface onResponseVerifyMobileOtp {
        void sendMobileOtpResponse(LoginResponseModel otpResponseModel);
    }

    public VerifyOtpAndLoginPressedService(Activity context, String mobileNO, String code,
                                           onResponseVerifyMobileOtp onResponseVerifyMobileOtpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        LoginDataForMobile otpData = new LoginDataForMobile();
        otpData.setMobileNumber(Long.parseLong(mobileNO));
        otpData.setCode(Long.parseLong(code));
        Call<LoginResponseModel> call = service.loginViaMobile(otpData);
        call.enqueue(new Callback<LoginResponseModel>() {

            @Override
            public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseVerifyMobileOtpCallback.sendMobileOtpResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
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
