package in.startupjobs.services;

import android.app.Activity;
import android.app.ProgressDialog;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import in.startupjobs.R;
import in.startupjobs.utils.APIError;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteWorkExpRequest {
    private final ProgressDialog progressDialog;

    public interface onDeleteWorkExpResponse {
        void sendDeleteWorkExpResponse(boolean isDeleted);
    }

    public DeleteWorkExpRequest(Activity context, int id,
                                onDeleteWorkExpResponse onDeleteWorkExpResponseCallback) {

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<Object> call = service.deleteWorkExp(id);
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        try {
                            JSONObject tokenObj = new JSONObject(response.body().toString());
                            if (tokenObj.has("deleted")) {
                                if (tokenObj.getBoolean("deleted")) {
                                    Snackbar.make(context.findViewById(android.R.id.content), "" + context.getString(R.string.success), Snackbar.LENGTH_SHORT).show();
                                    onDeleteWorkExpResponseCallback.sendDeleteWorkExpResponse(true);
                                    return;
                                }
                            }
                            Snackbar.make(context.findViewById(android.R.id.content), "" + context.getString(R.string.error), Snackbar.LENGTH_SHORT).show();
                            onDeleteWorkExpResponseCallback.sendDeleteWorkExpResponse(false);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                } else {
                    progressDialog.dismiss();
                    APIError error = ErrorUtils.parseError(response);
                    Snackbar.make(context.findViewById(android.R.id.content), error.messages.get(0).toString(), Snackbar.LENGTH_SHORT).show();
                    onDeleteWorkExpResponseCallback.sendDeleteWorkExpResponse(false);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                onDeleteWorkExpResponseCallback.sendDeleteWorkExpResponse(false);
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
