package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import in.startupjobs.model.editProfile.EditProfileResponseData;
import in.startupjobs.model.editProfile.ToSendEditProfileData;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEditPersonalDetailsService {
    private final ProgressDialog progressDialog;

    public interface onResponseFromEditProfilePersonal {

        void sendEditProfileResponse(EditProfileResponseData profileResponseData);
    }


    public PostEditPersonalDetailsService(Activity context, ToSendEditProfileData toSendEditProfileData,
                                          onResponseFromEditProfilePersonal onResponseFromEditProfilePersonalCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<EditProfileResponseData> call = service.editPersonalDetails(toSendEditProfileData);
        call.enqueue(new Callback<EditProfileResponseData>() {

            @Override
            public void onResponse(@NonNull Call<EditProfileResponseData> call, @NonNull Response<EditProfileResponseData> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    onResponseFromEditProfilePersonalCallback.sendEditProfileResponse(response.body());
                } else {
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<EditProfileResponseData> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
