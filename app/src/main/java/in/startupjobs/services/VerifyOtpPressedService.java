package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpPressedService {
    private final ProgressDialog progressDialog;

    public interface onResponseVerifyEmailOtp {
        void sendEmailOtpResponse(OtpResponseModel otpResponseModel);
    }

    public interface onResponseVerifyMobileOtp {
        void sendMobileOtpResponse(OtpResponseModel otpResponseModel);
    }

    public VerifyOtpPressedService(Activity context, String type, String value,
                                   onResponseVerifyEmailOtp onResponseVerifyEmailOtpCallback, onResponseVerifyMobileOtp onResponseVerifyMobileOtpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        OtpResponseModel.VerifyOtpData otpData = new OtpResponseModel.VerifyOtpData();
        otpData.type = type;
        otpData.value = value;
        Call<OtpResponseModel> call = service.verifyOtp(otpData);
        call.enqueue(new Callback<OtpResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<OtpResponseModel> call,@NonNull Response<OtpResponseModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (type.equalsIgnoreCase("email")) {
                        new VerifyOtpPressedService(context, "mobile", value
                                , onResponseVerifyEmailOtpCallback, onResponseVerifyMobileOtpCallback);
                        onResponseVerifyEmailOtpCallback.sendEmailOtpResponse(response.body());
                    } else {
                        onResponseVerifyMobileOtpCallback.sendMobileOtpResponse(response.body());
                        progressDialog.dismiss();
                    }
                    if (response.body().getMessage().equalsIgnoreCase("Otp Already Exists.")) {
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
            public void onFailure(@NonNull Call<OtpResponseModel> call,@NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
