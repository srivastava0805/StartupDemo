package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfileDetailsByIdService {
    private final ProgressDialog progressDialog;


    public interface onResponseGetPublicProfileDetailsById {
        void sendProfileDetailsByIdResponse(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse);
    }

    public GetProfileDetailsByIdService(Activity context,
                                        onResponseGetPublicProfileDetailsById onResponseGetPublicProfileDetailsByIdCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<PublicProfileDetailsByIDResponse> call = service.getBasicPublicProfileDetailsById(AppConstants.mLoginData.getUserId());
        call.enqueue(new Callback<PublicProfileDetailsByIDResponse>() {

            @Override
            public void onResponse(@NonNull Call<PublicProfileDetailsByIDResponse> call, @NonNull Response<PublicProfileDetailsByIDResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseGetPublicProfileDetailsByIdCallback.sendProfileDetailsByIdResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<PublicProfileDetailsByIDResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
