package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteRegistrationService {
    private final ProgressDialog progressDialog;

    public interface onResponseCompleteRegistration {
        void sendCompleteRegistrationResponse(RegistrationResponseModel otpResponseModel);
    }

    public CompleteRegistrationService(Activity context, RegistrationResponseModel.RegistrationDataToSend dataReadyForSignUp,
                                       onResponseCompleteRegistration onResponseCompleteRegistrationCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<RegistrationResponseModel> call = service.registerCandidate(dataReadyForSignUp);
        call.enqueue(new Callback<RegistrationResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<RegistrationResponseModel> call, @NonNull Response<RegistrationResponseModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        Snackbar.make(context.findViewById(android.R.id.content), "" + response.body().getMessage(), Snackbar.LENGTH_SHORT).show();
                        onResponseCompleteRegistrationCallback.sendCompleteRegistrationResponse(response.body());
                    }
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(@NonNull Call<RegistrationResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
