package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViaEmailService {
    private final ProgressDialog progressDialog;


    public interface onResponseLoginViaEmail {
        void sendMobileOtpResponse(LoginResponseModel responseModel);
    }

    public LoginViaEmailService(Activity context, String email, String password,
                                onResponseLoginViaEmail onResponseLoginViaEmailCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        LoginResponseModel.LoginDataToSend dataToSend = new LoginResponseModel.LoginDataToSend();
        dataToSend.setCandidateEmail(email);
        dataToSend.setPasswords(password);
        Call<LoginResponseModel> call = service.loginViaEmail(dataToSend);
        call.enqueue(new Callback<LoginResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<LoginResponseModel> call,@NonNull Response<LoginResponseModel> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseLoginViaEmailCallback.sendMobileOtpResponse(response.body());
                    progressDialog.dismiss();
                } else {
                    onResponseLoginViaEmailCallback.sendMobileOtpResponse(null);
                    progressDialog.dismiss();
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<LoginResponseModel> call,@NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
