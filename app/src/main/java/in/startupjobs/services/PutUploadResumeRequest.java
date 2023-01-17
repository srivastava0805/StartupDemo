package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;

import in.startupjobs.R;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.model.uploadResume.UploadResumeResponse;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PutUploadResumeRequest {
    private final ProgressDialog progressDialog;

    public interface onResponseResumeUpload {
        void sendUploadResumeStatusResponse(RegistrationResponseModel otpResponseModel);
    }

    public PutUploadResumeRequest(Activity context, File mediaPath,
                                  onResponseResumeUpload onResponseResumeUploadCallback) {

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), mediaPath);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", mediaPath.getName(), requestBody);
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<UploadResumeResponse> call = service.uploadResume(fileToUpload);
        call.enqueue(new Callback<UploadResumeResponse>() {

            @Override
            public void onResponse(@NonNull Call<UploadResumeResponse> call, @NonNull Response<UploadResumeResponse> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        Snackbar.make(context.findViewById(android.R.id.content), context.getString(R.string.success), Snackbar.LENGTH_SHORT).show();
                        onResponseResumeUploadCallback.sendUploadResumeStatusResponse(null);
                    }
                } else {
                    progressDialog.dismiss();
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<UploadResumeResponse> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
