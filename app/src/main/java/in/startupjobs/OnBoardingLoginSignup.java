package in.startupjobs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;



public class OnBoardingLoginSignup extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_login_signup);
        MaterialButton btnLogin= findViewById(in.startupjobs.R.id.btn_login);



        MaterialButton btnSignup = findViewById(R.id.btn_signup);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(OnBoardingLoginSignup.this, SignUpActivityDemo.class);
                startActivity(intent);
            }
        });


    }
}