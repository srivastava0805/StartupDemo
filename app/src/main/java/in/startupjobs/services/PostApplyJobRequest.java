package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.model.applyJob.ApplyJobResponseModel;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostApplyJobRequest {
    private final ProgressDialog progressDialog;

    public interface onApplyJobResponse {
        void sendApplyJobResponse(ApplyJobResponseModel applyJobResponseModel);
    }

    public PostApplyJobRequest(Activity context, String id, ApplyJobResponseModel.ApplyJobDataToSend jobDataToSend,
                               onApplyJobResponse onApplyJobResponseCallback) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<ApplyJobResponseModel> call = service.applyJob(id, jobDataToSend);
        call.enqueue(new Callback<ApplyJobResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<ApplyJobResponseModel> call, @NonNull Response<ApplyJobResponseModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
//                        Snackbar.make(context.findViewById(android.R.id.content), "" + response.body().getResumeFiles().get(0), Snackbar.LENGTH_SHORT).show();
                        onApplyJobResponseCallback.sendApplyJobResponse(null);
                    }
                } else {
                    progressDialog.dismiss();
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ApplyJobResponseModel> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
