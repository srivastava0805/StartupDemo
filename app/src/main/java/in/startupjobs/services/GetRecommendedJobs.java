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

public class GetRecommendedJobs {
    private final ProgressDialog progressDialog;


    public interface onResponseRecommendedJobs {
        void sendRecommendedJobsResponse(SearchedJobsResponse appliedJobsResponseList);
    }

    public GetRecommendedJobs(Activity context, String limit, onResponseRecommendedJobs onResponseRecommendedJobsCallback) {
        Call<SearchedJobsResponse> call;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        call = service.getRecommendedJons(Integer.parseInt(limit));
        call.enqueue(new Callback<SearchedJobsResponse>() {

            @Override
            public void onResponse(@NonNull Call<SearchedJobsResponse> call, @NonNull Response<SearchedJobsResponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    onResponseRecommendedJobsCallback.sendRecommendedJobsResponse(response.body());
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
