package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.basicDetails.BasicDetailsReponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetBasicDetailsService {
    private final ProgressDialog progressDialog;


    public interface onResponseGetBasicDetails {
        void sendBasicDetailsResponse(BasicDetailsReponse basicDetailsReponse);
    }

    public GetBasicDetailsService(Activity context,
                                  onResponseGetBasicDetails onResponseGetBasicDetailsCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<BasicDetailsReponse> call = service.getBasicDetails();
        call.enqueue(new Callback<BasicDetailsReponse>() {

            @Override
            public void onResponse(@NonNull Call<BasicDetailsReponse> call, @NonNull Response<BasicDetailsReponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseGetBasicDetailsCallback.sendBasicDetailsResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<BasicDetailsReponse> call, @NonNull Throwable t) {
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
