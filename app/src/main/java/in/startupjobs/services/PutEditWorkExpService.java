package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.editProfile.ToSendWorkExpData;
import in.startupjobs.model.editProfile.workExp.AddWorkExpReponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutEditWorkExpService {
    private final ProgressDialog progressDialog;

    public interface onResponseFromAddWorkExp {

        void sendEditProfileResponse(AddWorkExpReponse addWorkExpReponse);
    }


    public PutEditWorkExpService(Activity context, ToSendWorkExpData toSendWorkExpData,
                                 int id,
                                 onResponseFromAddWorkExp onResponseFromAddWorkExpCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<AddWorkExpReponse> call = service.editWorkExperience(id,toSendWorkExpData);
        call.enqueue(new Callback<AddWorkExpReponse>() {

            @Override
            public void onResponse(@NonNull Call<AddWorkExpReponse> call, @NonNull Response<AddWorkExpReponse> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    onResponseFromAddWorkExpCallback.sendEditProfileResponse(response.body());
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<AddWorkExpReponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
