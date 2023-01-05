package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import in.startupjobs.model.editProfile.SearchCitiesResponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllCitiesServiceService {
    private final ProgressDialog progressDialog;

    public interface onResponseFromGetCities {

        void sendGetCitiesResponse(List<SearchCitiesResponse> otpResponseModel);
    }


    public GetAllCitiesServiceService(Activity context,String keyword,
                                      onResponseFromGetCities onResponseFromGetCitiesCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<List<SearchCitiesResponse>> call = service.getSearchedCitiesByKeyword();
        call.enqueue(new Callback<List<SearchCitiesResponse>>() {

            @Override
            public void onResponse(@NonNull Call<List<SearchCitiesResponse>> call, @NonNull Response<List<SearchCitiesResponse>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    onResponseFromGetCitiesCallback.sendGetCitiesResponse(response.body());
                    ApiClient.retrofit = null;
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<SearchCitiesResponse>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
