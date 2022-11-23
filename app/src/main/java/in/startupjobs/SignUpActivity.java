package in.startupjobs;

import static in.startupjobs.utils.GlobalVariablesNMethods.isNetworkAvailable;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.services.ApiInterface;
import in.startupjobs.utils.ApiClient;
import in.startupjobs.utils.CredentialsValidation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnFocusChangeListener {

    Toolbar toolbar;
    CredentialsValidation validator;
    private ProgressDialog progressDialog;
    private OtpResponseModel otpResponse;
    private boolean firstNameFilled = false, lastNameFilled = false, emailFilled = false, passwordFilled = false, confirmPasswordFilled = false;

    TextInputLayout fullNameLayout;
    TextInputLayout emailLayout;
    TextInputLayout mobileLayout;
    TextInputLayout passwordLayout;
    TextInputEditText edtFullName;
    TextInputEditText edtEmail;
    TextInputEditText edtMobile;
    TextInputEditText edtPassword;
    MaterialButton btnSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void init() {

        fullNameLayout = findViewById(R.id.activity_signup_fullname_layout);
        emailLayout = findViewById(R.id.activity_signup_email_layout);
        mobileLayout = findViewById(R.id.activity_signup_mobile_layout);
        passwordLayout = findViewById(R.id.activity_signup_password_layout);

        edtFullName = findViewById(R.id.activity_signup_edt_fullname);
        edtEmail = findViewById(R.id.activity_signup_edt_email);
        edtMobile = findViewById(R.id.activity_signup_edt_mobile);
        edtPassword = findViewById(R.id.activity_signup_edt_password);

        btnSignUp = findViewById(R.id.activity_signup_btn_signup);

        validator = new CredentialsValidation();

        edtFullName.addTextChangedListener(new ValidationTextWatcher(edtFullName));
        edtFullName.setOnFocusChangeListener(this);


        edtEmail.addTextChangedListener(new ValidationTextWatcher(edtEmail));
        edtEmail.setOnFocusChangeListener(this);

        edtMobile.addTextChangedListener(new ValidationTextWatcher(edtMobile));
        edtMobile.setOnFocusChangeListener(this);

        edtPassword.addTextChangedListener(new ValidationTextWatcher(edtPassword));
        edtPassword.setOnFocusChangeListener(this);

//            btnSignUp.setEnabled(false);

        btnSignUp.setOnClickListener(v -> {
            if (isNetworkAvailable(this))
                this.onSendOTPPressed(Objects.requireNonNull(edtEmail.getText()).toString());
            else
                Snackbar.make(findViewById(android.R.id.content), "Internet not available.Please check your internet connection!", Snackbar.LENGTH_SHORT).show();

        });

//            btnSignUp.setOnClickListener(v -> {
//                if (isNetworkAvailable(this))
//                    this.onSignUpPressed(getDataReadyForSignUp());
//                else
//                    Snackbar.make(findViewById(android.R.id.content),
//                            "Internet not available.Please check your internet connection!",
//                            Snackbar.LENGTH_SHORT).show();
//            });
    }


    private RegistrationResponseModel.RegistrationDataToSend getDataReadyForSignUp() {
        RegistrationResponseModel.RegistrationDataToSend data = new RegistrationResponseModel.RegistrationDataToSend();
        data.setCandidateemailid(Objects.requireNonNull(edtEmail.getText()).toString());
        data.setCandidatename(Objects.requireNonNull(edtFullName.getText()).toString());
        data.setCandidatepassword(Objects.requireNonNull(edtPassword.getText()).toString());
//            data.setOtp(Objects.requireNonNull(edtOTP.getText()).toString());
        data.setToken(otpResponse.getToken());
        return data;

    }

    private void onSendOTPPressed(String emailOTP) {
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        OtpResponseModel.OtpDataToSend otpData = new OtpResponseModel.OtpDataToSend();
        otpData.CANDIDATEEMAILID = emailOTP;
        Call<OtpResponseModel> call = service.getOtp(otpData);
        call.enqueue(new Callback<OtpResponseModel>() {

            @Override
            public void onResponse(Call<OtpResponseModel> call, Response<OtpResponseModel> response) {

                if (response.isSuccessful()) {
//                    Log.e("DDDD", response.body().toString());
                    Toast.makeText(SignUpActivity.this, "" + response.body().getOtp(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    assert response.body() != null;
                    if (response.body().getMessage().equalsIgnoreCase("Otp Already Exists.")) {
                        Snackbar.make(findViewById(android.R.id.content), "OTP already sent to the provided email. Please check", Snackbar.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), "OTP sent successfully", Snackbar.LENGTH_SHORT).show();
                    }
//                        otpLayout.setVisibility(View.VISIBLE);
                    makeEditTextUnEditable();
                    otpResponse = response.body();
                    setResendOTPButtonState();
//                    btnSignUp.setEnabled(true);
                } else {
                    progressDialog.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), "Call error with HTTP status code " + response.code() + "!", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<OtpResponseModel> call, Throwable t) {
//                Log.e("DDDD", t.getMessage());
                progressDialog.dismiss();
//                    otpLayout.setVisibility(View.GONE);
                Snackbar.make(findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();

            }
        });
    }

    private void onSignUpPressed(RegistrationResponseModel.RegistrationDataToSend dataReadyForSignUp) {
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        Call<RegistrationResponseModel> call = service.registerCandidate(dataReadyForSignUp);
        call.enqueue(new Callback<RegistrationResponseModel>() {

            @Override
            public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    if (response.body() != null) {
                        Snackbar.make(findViewById(android.R.id.content), "" + response.body().getMessage(), Snackbar.LENGTH_SHORT).show();
                        //TODO Container Profile Manage

//                            Intent intent = new Intent(SignUpActivity.this,SearchNApplyJob.class);
//                            startActivity(intent);
                        finish();
                    }
                } else {
                    progressDialog.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), "Call error with HTTP status code " + response.code() + "!", Snackbar.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
                progressDialog.dismiss();
                Snackbar.make(findViewById(android.R.id.content),
                        // Throwable will let us find the error if the call failed.
                        "Call failed! " + t.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
//                    Toast.makeText(SignUpActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void makeEditTextUnEditable() {
        edtFullName.setInputType(InputType.TYPE_NULL);
        edtEmail.setInputType(InputType.TYPE_NULL);
        edtPassword.setInputType(InputType.TYPE_NULL);
        edtMobile.setInputType(InputType.TYPE_NULL);
    }

    private class ValidationTextWatcher implements TextWatcher {
        private View view;

        private ValidationTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @SuppressLint("NonConstantResourceId")
        public void afterTextChanged(Editable editable) {
//                switch (view.getId()) {
//                    case R.id.activity_signup_edt_fullname:
//                        firstNameFilled = validator.validateName(firstNameLayout);
//                        break;
//                    case R.id.activity_signup_edt_lastname:
//                        lastNameFilled = validator.validateName(lastNameLayout);
//                        break;
//                    case R.id.activity_signup_edt_email:
//                        emailFilled = validator.validateEmail(emailLayout, SignUpActivity.this);
//                        break;
//                    case R.id.activity_signup_edt_password:
//                        passwordFilled = validator.validatePassword(passwordLayout, SignUpActivity.this);
//                        break;
//                    case R.id.activity_signup_edt_confirm_password:
//                        confirmPasswordFilled = validator.confirmPassword(passwordLayout, confirmPasswordLayout);
//                        break;
//                    case R.id.activity_signup_edt_otp:
//                        btnSignUp.setEnabled(validator.validateOTP(otpLayout));
//                        break;
//                }
//                setOTPButtonState();

        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
//                case R.id.activity_signup_edt_fullname:
//                    if (!hasFocus)
//                        validator.validateName(firstNameLayout);
//                    break;
//                case R.id.activity_signup_edt_lastname:
//                    if (!hasFocus)
//                        validator.validateName(lastNameLayout);
//                    break;
//                case R.id.activity_signup_edt_email:
//                    if (!hasFocus)
//                        validator.validateEmail(emailLayout);
//                    break;
//                case R.id.activity_signup_edt_password:
//                    if (!hasFocus)
//                        validator.validatePassword(passwordLayout);
//                    break;
//                case R.id.activity_signup_edt_confirm_password:
//                    if (!hasFocus)
//                        validator.confirmPassword(passwordLayout, confirmPasswordLayout);
//                    break;
        }
    }

//        public void setOTPButtonState() {
//            btnSendOTP.setEnabled(firstNameFilled && lastNameFilled && emailFilled && passwordFilled && confirmPasswordFilled);
//        }

    public void setResendOTPButtonState() {
//            if (otpLayout.getVisibility() == View.VISIBLE) {
//                btnSendOTP.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.color_white));
//                btnSendOTP.setStrokeColor(ContextCompat.getColorStateList(this, R.color.color_orange_btn_enabled));
//                btnSendOTP.setStrokeWidth(4);
//                btnSendOTP.setTextColor(ContextCompat.getColorStateList(this, R.color.color_orange_btn_enabled));
//                btnSendOTP.setText(R.string.string_action_resend_otp);
//            }
    }

}



