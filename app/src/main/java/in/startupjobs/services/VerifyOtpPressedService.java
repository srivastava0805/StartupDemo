package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpPressedService {
    private final ProgressDialog progressDialog;

    public interface onResponseVerifyEmailOtp {
        void sendEmailOtpVerifyResponse(OtpResponseModel.VerifyOtpResponseData otpResponseModel);
    }

    public interface onResponseVerifyMobileOtp {
        void sendMobileOtpVerifyResponse(OtpResponseModel.VerifyOtpResponseData otpResponseModel);
    }

    public VerifyOtpPressedService(Activity context, String type, String code, String value,
                                   onResponseVerifyEmailOtp onResponseVerifyEmailOtpCallback, onResponseVerifyMobileOtp onResponseVerifyMobileOtpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        OtpResponseModel.VerifyOtpData otpData = new OtpResponseModel.VerifyOtpData();
        otpData.type = type;
        otpData.value = value;
        if (code != null)
            if (code.contains("null"))
                otpData.code = Long.parseLong(code);
        Call<OtpResponseModel.VerifyOtpResponseData> call = service.verifyOtp(otpData);
        call.enqueue(new Callback<OtpResponseModel.VerifyOtpResponseData>() {

            @Override
            public void onResponse(@NonNull Call<OtpResponseModel.VerifyOtpResponseData> call, @NonNull Response<OtpResponseModel.VerifyOtpResponseData> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (type.equalsIgnoreCase("email")) {
                        onResponseVerifyEmailOtpCallback.sendEmailOtpVerifyResponse(response.body());
                    } else {
                        onResponseVerifyMobileOtpCallback.sendMobileOtpVerifyResponse(response.body());

                    }
                } else {
                    progressDialog.dismiss();
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<OtpResponseModel.VerifyOtpResponseData> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
