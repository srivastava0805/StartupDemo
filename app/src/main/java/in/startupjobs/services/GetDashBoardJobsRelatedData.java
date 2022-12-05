package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.dashBoardData.DashBoardJobsData;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDashBoardJobsRelatedData {
    private final ProgressDialog progressDialog;


    public interface onResponseGetJobRelatedDataForDashBoard {
        void sendDashBoardJobsDataResponse(DashBoardJobsData dashBoardJobsData);
    }

    public GetDashBoardJobsRelatedData(Activity context,
                                       onResponseGetJobRelatedDataForDashBoard onResponseGetJobRelatedDataForDashBoardCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<DashBoardJobsData> call = service.getDashBoardJobsData();
        call.enqueue(new Callback<DashBoardJobsData>() {

            @Override
            public void onResponse(@NonNull Call<DashBoardJobsData> call, @NonNull Response<DashBoardJobsData> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    onResponseGetJobRelatedDataForDashBoardCallback.sendDashBoardJobsDataResponse(response.body());
                } else {
                    if (response.errorBody() != null) {
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<DashBoardJobsData> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
