package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.serachedJobs.SearchedJobsResponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetSearchedJobs {
    private final ProgressDialog progressDialog;


    public interface onResponseSearchedJobs {
        void sendSearchedJobsResponse(SearchedJobsResponse appliedJobsResponseList);
    }

    public GetSearchedJobs(Activity context, String skill, String location, String exp, onResponseSearchedJobs onResponseSearchedJobsCallback) {
        Call<SearchedJobsResponse> call;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        if (exp.isEmpty()) {
            if (location.isEmpty())
                call = service.getSearchedJobsByKeyword(skill);
            else call = service.getSearchedJobsByKeyword(skill + "&" + location);
        } else call = service.getSearchedJobsByKeyword(skill + "&" + location + "&" + exp);
        call.enqueue(new Callback<SearchedJobsResponse>() {

            @Override
            public void onResponse(@NonNull Call<SearchedJobsResponse> call, @NonNull Response<SearchedJobsResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    onResponseSearchedJobsCallback.sendSearchedJobsResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchedJobsResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
