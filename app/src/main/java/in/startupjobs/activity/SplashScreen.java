package in.startupjobs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import in.startupjobs.R;
import in.startupjobs.services.GetCsrfTokenService;


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


        btnLetsGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetCsrfTokenService(SplashScreen.this, new GetCsrfTokenService.onResponseCsrfTokenRequest() {
                    @Override
                    public void sendCsrfTokenResponse(String response) {
                        if (response != null && response.equalsIgnoreCase(getString(R.string.success)))
                            sendToNextActivity();
                    }
                });

            }
        });
    }

    private void sendToNextActivity() {
        Intent intent;
        intent = new Intent(SplashScreen.this, OnBoardingLoginSignup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}