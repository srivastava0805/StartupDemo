package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.companies.GetCompaniesResponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSearchedCompanies {
    private final ProgressDialog progressDialog;


    public interface onResponseSearchedCompanies {
        void sendSearchedCompaniesResponse(GetCompaniesResponse getCompaniesResponse);
    }

    public GetSearchedCompanies(Activity context, int limit,String keyword, onResponseSearchedCompanies onResponseSearchedCompaniesCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<GetCompaniesResponse> call = service.getSearchedCompaniesByKeyword(limit,keyword);
        call.enqueue(new Callback<GetCompaniesResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetCompaniesResponse> call, @NonNull Response<GetCompaniesResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    onResponseSearchedCompaniesCallback.sendSearchedCompaniesResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<GetCompaniesResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
