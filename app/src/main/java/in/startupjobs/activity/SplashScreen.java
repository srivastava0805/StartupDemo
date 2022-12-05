package in.startupjobs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import in.startupjobs.R;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.services.GetCsrfTokenService;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.Preferences;


public class SplashScreen extends AppCompatActivity {
    private MaterialButton btnLetsGo;

    /**
     * Called when the activity is first created.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        btnLetsGo = findViewById(R.id.activity_splash_btn_letsgo);

        if (!Preferences.readString(this, Preferences.TOKEN, "").isEmpty())
            sendToNextActivity(MainActivity.class);

        btnLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetCsrfTokenService(SplashScreen.this, new GetCsrfTokenService.onResponseCsrfTokenRequest() {
                    @Override
                    public void sendCsrfTokenResponse(String response) {
                        if (response != null && response.equalsIgnoreCase(getString(R.string.success)))
                            sendToNextActivity(OnBoardingLoginSignup.class);
                    }
                });

            }
        });
    }

    private void sendToNextActivity(Class name) {
        setDataToLocal();
        Intent intent;
        intent = new Intent(SplashScreen.this, name);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void setDataToLocal() {
        LoginResponseModel newModal = new LoginResponseModel();
        newModal.setToken(Preferences.getPreferences(this).getString(Preferences.TOKEN, "xyz@xyz.com"));
        newModal.setEmail(Preferences.getPreferences(this).getString(Preferences.EMAIL, "xyz@xyz.com"));
        newModal.setUserId(Preferences.getPreferences(this).getString(Preferences.USER_ID, "xyz@xyz.com"));
        newModal.setFullName(Preferences.getPreferences(this).getString(Preferences.NAME, "xyz@xyz.com"));
        newModal.setUserType(Preferences.getPreferences(this).getString(Preferences.USER_TYPE, "xyz@xyz.com"));
        AppConstants.mLoginData = newModal;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}