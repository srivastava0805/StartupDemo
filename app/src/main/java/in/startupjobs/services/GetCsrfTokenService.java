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
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.ErrorUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetCsrfTokenService {
    private final ProgressDialog progressDialog;


    public interface onResponseCsrfTokenRequest {
        void sendCsrfTokenResponse(String response);
    }

    public GetCsrfTokenService(Activity context,
                               onResponseCsrfTokenRequest onResponseCsrfTokenRequestCallback) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient(null).create(ApiInterface.class);
        Call<Object> call = service.getCsrfToken();
        call.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(@NonNull Call<Object> call,@NonNull Response<Object> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    try {
                        JSONObject tokenObj = new JSONObject(response.body().toString());
                        if (tokenObj.has("token")) {
                            AppConstants.XCSRF_TOKEN = tokenObj.getString("token");
                            onResponseCsrfTokenRequestCallback.sendCsrfTokenResponse(context.getString(R.string.success));
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    if (response.errorBody() != null) {
                        onResponseCsrfTokenRequestCallback.sendCsrfTokenResponse(context.getString(R.string.success));
                        APIError error = ErrorUtils.parseError(response);
                        Snackbar.make(context.findViewById(android.R.id.content), "" + error.messages.get(0), Snackbar.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Object> call,@NonNull Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(context.findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}
