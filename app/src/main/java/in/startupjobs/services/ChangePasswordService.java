package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import in.startupjobs.R;
import in.startupjobs.model.forgotPassword.ChangePasswordModel;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordService {
    private final ProgressDialog progressDialog;

    public interface onResponseFromChangePassword {

        void sendEmailOtpResponse(Object otpResponseModel);
    }


    public ChangePasswordService(Activity context, String type, String value, String pass, long code,
                                 onResponseFromChangePassword onResponseFromChangePasswordCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        ChangePasswordModel.OtpDataToSend otpData = new ChangePasswordModel.OtpDataToSend();
        if (type.equalsIgnoreCase("email"))
            otpData.setEmail(value);
        else
            otpData.setMobileNumber(Long.valueOf(value));

        otpData.setCode(code);
        otpData.setPassword(pass);
        Call<Object> call = service.postChangePassword(otpData);
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().toString());
                        if (jsonObject.has("message"))
                            if (jsonObject.getString("message").equalsIgnoreCase(AppConstants.PASSWORD_RESET_SUCCESSFUL)) {
                                Snackbar.make(context.findViewById(android.R.id.content), context.getString(R.string.pass_reset_success), Snackbar.LENGTH_SHORT).show();
                                onResponseFromChangePasswordCallback.sendEmailOtpResponse(AppConstants.PASSWORD_RESET_SUCCESSFUL);
                            }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                    onResponseFromChangePasswordCallback.sendEmailOtpResponse(response.body());

                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
