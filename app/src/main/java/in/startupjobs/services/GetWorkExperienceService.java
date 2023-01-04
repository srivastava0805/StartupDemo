package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import in.startupjobs.model.workExperience.WorkExperienceResponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetWorkExperienceService {
    private final ProgressDialog progressDialog;


    public interface onResponseGetWorkExperience {
        void sendDashBoardJobsDataResponse(List<WorkExperienceResponse> workExperienceResponseList);
    }

    public GetWorkExperienceService(Activity context,
                                    onResponseGetWorkExperience onResponseGetWorkExperienceCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<List<WorkExperienceResponse>> call = service.getWorkExperience();
        call.enqueue(new Callback<List<WorkExperienceResponse>>() {

            @Override
            public void onResponse(@NonNull Call<List<WorkExperienceResponse>> call, @NonNull Response<List<WorkExperienceResponse>> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseGetWorkExperienceCallback.sendDashBoardJobsDataResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<WorkExperienceResponse>> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
