package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOtpPressedService {
    private final ProgressDialog progressDialog;
    onResponseSendEmailOtp onResponseSendEmailOtp;
    onResponseSendMobileOtp onResponseSendMobileOtp;

    public interface onResponseSendEmailOtp {

        void sendEmailOtpResponse(OtpResponseModel otpResponseModel);
    }

    public interface onResponseSendMobileOtp {
        void sendMobileOtpResponse(OtpResponseModel otpResponseModel);
    }

    public SendOtpPressedService(Activity context, String type, String value,
                                 onResponseSendEmailOtp onResponseSendEmailOtpCallback, onResponseSendMobileOtp onResponseSendMobileOtpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        OtpResponseModel.OtpDataToSend otpData = new OtpResponseModel.OtpDataToSend();
        otpData.type = type;
        otpData.value = value;
        Call<OtpResponseModel> call = service.getOtp(otpData);
        call.enqueue(new Callback<OtpResponseModel>() {

            @Override
            public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (type.equalsIgnoreCase("email")) {
                        new SendOtpPressedService(context, "mobile", value
                                , onResponseSendEmailOtpCallback, onResponseSendMobileOtpCallback);
                        onResponseSendEmailOtpCallback.sendEmailOtpResponse(response.body());
                    } else {
                        onResponseSendMobileOtpCallback.sendMobileOtpResponse(response.body());
                        progressDialog.dismiss();
                    }
                    if (response.body().getMessage().equalsIgnoreCase("Otp Already Exists.")) {
                        Snackbar.make(context.findViewById(android.R.id.content), "OTP already sent to the provided email. Please check", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(context.findViewById(android.R.id.content), "OTP sent successfully", Snackbar.LENGTH_SHORT).show();
                    }

                } else {
                    onResponseSendMobileOtpCallback.sendMobileOtpResponse(null);
                    progressDialog.dismiss();
                    Snackbar.make(context.findViewById(android.R.id.content), "Call error with HTTP status code " + response.code() + "!", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OtpResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
