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

public class SendOtpPressedService {
    private final ProgressDialog progressDialog;

    public interface onResponseSendEmailOtp {

        void sendEmailOtpResponse(OtpResponseModel otpResponseModel);
    }

    public interface onResponseSendMobileOtp {
        void sendMobileOtpResponse(OtpResponseModel otpResponseModel);
    }

    public SendOtpPressedService(Activity context, String type, String value,
                                 onResponseSendEmailOtp onResponseSendEmailOtpCallback,
                                 onResponseSendMobileOtp onResponseSendMobileOtpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        if (!progressDialog.isShowing())
            progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        OtpResponseModel.OtpDataToSend otpData = new OtpResponseModel.OtpDataToSend();
        otpData.type = type;
        otpData.value = value;
        Call<OtpResponseModel> call = service.getOtp(otpData);
        call.enqueue(new Callback<OtpResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<OtpResponseModel> call, @NonNull Response<OtpResponseModel> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (type.equalsIgnoreCase("email")) {
                        onResponseSendEmailOtpCallback.sendEmailOtpResponse(response.body());
                    } else {

                        onResponseSendMobileOtpCallback.sendMobileOtpResponse(response.body());
                    }
                    if (response.body().getMessage().equalsIgnoreCase("Otp Already Exists.")) {
                        Snackbar.make(context.findViewById(android.R.id.content), "OTP already sent to the provided email. Please check", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(context.findViewById(android.R.id.content), "OTP sent successfully", Snackbar.LENGTH_SHORT).show();
                    }

                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<OtpResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
