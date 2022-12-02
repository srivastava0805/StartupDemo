package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.utils.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComleteRegistrationService {
    private final ProgressDialog progressDialog;
    onResponseCompleteRegistration onResponseCompleteRegistration;

    public interface onResponseCompleteRegistration {
        void senCompleteRegistrationResponse(RegistrationResponseModel otpResponseModel);
    }

    public ComleteRegistrationService(Activity context, RegistrationResponseModel.RegistrationDataToSend dataReadyForSignUp,
                                      onResponseCompleteRegistration onResponseCompleteRegistrationCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<RegistrationResponseModel> call = service.registerCandidate(dataReadyForSignUp);
        call.enqueue(new Callback<RegistrationResponseModel>() {

            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        Snackbar.make(context.findViewById(android.R.id.content), "" + response.body().getMessage(), Snackbar.LENGTH_SHORT).show();
                        onResponseCompleteRegistrationCallback.senCompleteRegistrationResponse(response.body());
                    }
                } else {
//                    Todo remove the lne below, only for testing
                    onResponseCompleteRegistrationCallback.senCompleteRegistrationResponse(null);
                    progressDialog.dismiss();
                    Snackbar.make(context.findViewById(android.R.id.content), "Call error with HTTP status code " + response.code() + "!", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}