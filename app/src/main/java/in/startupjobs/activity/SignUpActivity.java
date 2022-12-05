package in.startupjobs.activity;

import static android.content.ContentValues.TAG;
import static in.startupjobs.utils.GlobalVariablesNMethods.isNetworkAvailable;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.kevinschildhorn.otpview.OTPView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import in.startupjobs.R;
import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.services.CompleteRegistrationService;
import in.startupjobs.services.SendOtpPressedService;
import in.startupjobs.services.VerifyOtpPressedService;
import in.startupjobs.utils.CredentialsValidation;
import in.startupjobs.utils.GetFilePathUtil;

public class SignUpActivity extends AppCompatActivity {

    Toolbar toolbar;
    CredentialsValidation validator;
    private ProgressDialog progressDialog;
    private OtpResponseModel otpResponse;
    private boolean fullNameFilled = false, lastNameFilled = false, emailFilled = false, passwordFilled = false, mobileFilled = false;

    TextInputLayout fullNameLayout;
    TextInputLayout emailLayout;
    TextInputLayout mobileLayout;
    TextInputLayout passwordLayout;
    TextInputEditText edtFullName;
    TextInputEditText edtEmail;
    TextInputEditText edtMobile;
    TextInputEditText edtPassword;
    TextView resendMobileOtp;
    MaterialButton btnSignUp;
    private ConstraintLayout mActivitySignupAlldataLayoutotp;
    private OTPView mActivitySignupOtpviewEntermobileotp;
    private OTPView mActivitySignupOtpviewEnteremailotp;
    private ConstraintLayout mActivitySignupAlldataUploadresumelayout;
    private TextView mActivitySignupTextviewUploadresumeactionbox;
    private SwitchCompat mActivitySignupSwitchAgree;
    private ConstraintLayout mActivitySignupAlldataLayout;
    private TextView resendEmailOtp;
    private static final int PICKFILE_RESULT_CODE = 101;
    private File fileResumeForAndroid11;
    private Uri realUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private RegistrationResponseModel.RegistrationDataToSend getDataReadyForSignUp() {
        RegistrationResponseModel.RegistrationDataToSend data = new RegistrationResponseModel.RegistrationDataToSend();
        data.setCandidateemailid(edtEmail.getText().toString().trim());
        data.setCandidatename(edtFullName.getText().toString().trim());
        data.setCandidatephoneno(edtMobile.getText().toString().trim());
        data.setCandidatepassword(edtPassword.getText().toString().trim());
        data.setTermsAccepted(true);
        return data;

    }

    private void makeFillOtpPageVisible() {
        mActivitySignupAlldataLayout.setVisibility(View.GONE);
        mActivitySignupAlldataLayoutotp.setVisibility(View.VISIBLE);
        btnSignUp.setText(R.string.verify_and_continue);
    }

    public void makeEditTextUnEditable() {
        edtFullName.setInputType(InputType.TYPE_NULL);
        edtEmail.setInputType(InputType.TYPE_NULL);
        edtPassword.setInputType(InputType.TYPE_NULL);
        edtMobile.setInputType(InputType.TYPE_NULL);
    }

    private void initView() {
        mActivitySignupAlldataLayoutotp = findViewById(R.id.activity_signup_alldata_layoutotp);
        mActivitySignupOtpviewEntermobileotp = findViewById(R.id.activity_signup_otpview_entermobileotp);
        mActivitySignupOtpviewEnteremailotp = findViewById(R.id.activity_signup_otpview_enteremailotp);
        mActivitySignupAlldataUploadresumelayout = findViewById(R.id.activity_signup_alldata_uploadresumelayout);
        mActivitySignupTextviewUploadresumeactionbox = findViewById(R.id.activity_signup_textview_uploadresumeactionbox);
        mActivitySignupSwitchAgree = findViewById(R.id.activity_signup_switch_agree);
        fullNameLayout = findViewById(R.id.activity_signup_fullname_layout);
        emailLayout = findViewById(R.id.activity_signup_email_layout);
        mobileLayout = findViewById(R.id.activity_signup_mobile_layout);
        passwordLayout = findViewById(R.id.activity_signup_password_layout);

        edtFullName = findViewById(R.id.activity_signup_edt_fullname);
        edtEmail = findViewById(R.id.activity_signup_edt_email);
        edtMobile = findViewById(R.id.activity_signup_edt_mobile);
        edtPassword = findViewById(R.id.activity_signup_edt_password);

        btnSignUp = findViewById(R.id.activity_signup_btn_signup);
        resendMobileOtp = findViewById(R.id.activity_signup_textview_resendmobileotp);
        resendEmailOtp = findViewById(R.id.activity_signup_textview_enteremailotp);

        validator = new CredentialsValidation();

        edtFullName.addTextChangedListener(new ValidationTextWatcher(edtFullName));
        edtEmail.addTextChangedListener(new ValidationTextWatcher(edtEmail));
        edtMobile.addTextChangedListener(new ValidationTextWatcher(edtMobile));
        edtPassword.addTextChangedListener(new ValidationTextWatcher(edtPassword));

        mActivitySignupAlldataLayout = findViewById(R.id.activity_signup_alldata_layout);
        mActivitySignupAlldataLayout.setVisibility(View.VISIBLE);
        mActivitySignupAlldataLayoutotp.setVisibility(View.GONE);
        mActivitySignupAlldataUploadresumelayout.setVisibility(View.GONE);

        setOnClicks();
    }


    private void setOnClicks() {
        btnSignUp.setOnClickListener(v -> {
            if (isNetworkAvailable(this)) {
                RegistrationResponseModel.RegistrationDataToSend dataToSend = getDataReadyForSignUp();
                String btnText = btnSignUp.getText().toString();
                String btnTextSave = getResources().getString(R.string.save_and_continue);
                String btnTextVerify = getResources().getString(R.string.verify_and_continue);
                String btnTextComplete = getResources().getString(R.string.complete_registration);
                if (btnText.equals(btnTextSave))
                    doSendOtpProcess();
                else if (btnText.equals(btnTextVerify))
                    doVerifyOtpProcess();
                else if (btnText.equals(btnTextComplete))
                    doRegistrationProcess();
            } else
                Snackbar.make(findViewById(android.R.id.content), "Internet not available.Please check your internet connection!", Snackbar.LENGTH_SHORT).show();

        });

        resendMobileOtp.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content),
                    "Internet not available.Please check your internet connection!",
                    Snackbar.LENGTH_SHORT).show();
        });
        resendEmailOtp.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content),
                    "Internet not available.Please check your internet connection!",
                    Snackbar.LENGTH_SHORT).show();
        });

        mActivitySignupTextviewUploadresumeactionbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
                chooseFile.setType("*/*");
                Intent intent = Intent.createChooser(chooseFile, "Choose a file");
                startActivityForResult(intent, PICKFILE_RESULT_CODE);
            }
        });
    }

    private void doRegistrationProcess() {
       new CompleteRegistrationService(this, getDataReadyForSignUp(),
                new CompleteRegistrationService.onResponseCompleteRegistration() {
                    @Override
                    public void sendCompleteRegistrationResponse(RegistrationResponseModel otpResponseModel) {
                        Toast.makeText(SignUpActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void doVerifyOtpProcess() {
        new VerifyOtpPressedService(this, "email",
                mActivitySignupOtpviewEnteremailotp.getStringFromFields(),
                new VerifyOtpPressedService.onResponseVerifyEmailOtp() {
                    @Override
                    public void sendEmailOtpResponse(OtpResponseModel otpResponseModel) {

                    }
                }, new VerifyOtpPressedService.onResponseVerifyMobileOtp() {
            @Override
            public void sendMobileOtpResponse(OtpResponseModel otpResponseModel) {
                makeUploadResumePageVisible();
            }
        });

    }

    private void doSendOtpProcess() {
        new SendOtpPressedService(this, "email",
                mActivitySignupOtpviewEnteremailotp.getStringFromFields(),
                new SendOtpPressedService.onResponseSendEmailOtp() {
                    @Override
                    public void sendEmailOtpResponse(OtpResponseModel otpResponseModel) {

                    }
                }, new SendOtpPressedService.onResponseSendMobileOtp() {
            @Override
            public void sendMobileOtpResponse(OtpResponseModel otpResponseModel) {
                makeFillOtpPageVisible();
            }
        });

    }

    private void makeUploadResumePageVisible() {
        mActivitySignupAlldataLayout.setVisibility(View.GONE);
        mActivitySignupAlldataLayoutotp.setVisibility(View.GONE);
        mActivitySignupAlldataUploadresumelayout.setVisibility(View.VISIBLE);
        btnSignUp.setText(R.string.complete_registration);
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
            switch (view.getId()) {
                case R.id.activity_signup_edt_fullname:
                    fullNameFilled = validator.validateName(fullNameLayout);
                    break;
                case R.id.activity_signup_edt_email:
                    emailFilled = validator.validateEmail(emailLayout);
                    break;
                case R.id.activity_signup_edt_mobile:
                    mobileFilled =validator.validatePhoneNo(mobileLayout);
                    break;
                case R.id.activity_signup_edt_password:
                    passwordFilled = validator.validatePassword(passwordLayout, SignUpActivity.this);
                    break;

            }
            setOTPButtonState();

        }
    }

    public void setOTPButtonState() {
        btnSignUp.setEnabled(fullNameFilled && emailFilled  && mobileFilled && passwordFilled);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICKFILE_RESULT_CODE && resultCode == Activity.RESULT_OK) {
            try {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                    Uri myUri = data.getData();
                    openPath(myUri);
                } else {
                    assert data != null;
                    realUri = Uri.parse(GetFilePathUtil.getPath(this, data.getData()));
                }

                if (GetFilePathUtil.getFileName() != null && !GetFilePathUtil.getFileName().isEmpty())
                    mActivitySignupTextviewUploadresumeactionbox.setText(GetFilePathUtil.getFileName());
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void openPath(Uri uri) {
        InputStream is = null;
        try {
            is = getContentResolver().openInputStream(uri);
            try {
                fileResumeForAndroid11 = new File(getCacheDir(), "cacheFileAppeal.srl");
                try (OutputStream output = new FileOutputStream(fileResumeForAndroid11)) {
                    byte[] buffer = new byte[4 * 1024]; // or other buffer size
                    int read;

                    while ((read = is.read(buffer)) != -1) {
                        output.write(buffer, 0, read);
                    }

                    output.flush();
                }
            } finally {
                is.close();
            }
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



