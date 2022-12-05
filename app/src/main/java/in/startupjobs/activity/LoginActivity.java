package in.startupjobs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kevinschildhorn.otpview.OTPView;

import java.util.Objects;

import in.startupjobs.R;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.services.FacebookLogin;
import in.startupjobs.services.LoginViaEmailService;
import in.startupjobs.services.SendOtpPressedService;
import in.startupjobs.services.VerifyOtpAndLoginPressedService;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.GlobalVariablesNMethods;
import in.startupjobs.utils.Preferences;

public class LoginActivity extends AppCompatActivity {


    private TextInputEditText mActivityLoginEdtEmail;
    private TextInputEditText mActivityLoginEdtPassword;
    private MaterialButton mActivityLoginBtnLogin;
    private TextView mTvForgotPassword;
    private MaterialButton mBtnFacebook;
    private MaterialButton mBtnGoogle;
    private MaterialButton mBtnLinkedin;
    private AppCompatButton mActivityLoginBtnChangelogintype;
    private ConstraintLayout mActivityLoginAlldataLayout;
    private ConstraintLayout mActivityLoginAlldataLayoutotp;
    private OTPView mActivityLoginOtpviewEntermobileotp;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        initView();

    }

    private void initView() {
        mActivityLoginEdtEmail = findViewById(R.id.activity_login_edt_email);
        mActivityLoginEdtPassword = findViewById(R.id.activity_login_edt_password);
        mActivityLoginBtnLogin = findViewById(R.id.activity_login_btn_login);
        mTvForgotPassword = findViewById(R.id.tv_forgot_password);
        mBtnFacebook = findViewById(R.id.btn_facebook);
        mBtnGoogle = findViewById(R.id.btn_google);
        mBtnLinkedin = findViewById(R.id.btn_linkedin);
        mActivityLoginBtnChangelogintype = findViewById(R.id.activity_login_btn_changelogintype);
        mActivityLoginAlldataLayout = findViewById(R.id.activity_login_alldata_layout);
        mActivityLoginAlldataLayoutotp = findViewById(R.id.activity_login_alldata_layoutotp);
        mActivityLoginOtpviewEntermobileotp = findViewById(R.id.activity_login_otpview_entermobileotp);
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
        setupClicks();
    }

    private void setupClicks() {
        mActivityLoginBtnChangelogintype.setOnClickListener(view -> {
            mActivityLoginEdtEmail.requestFocus();
            if (mActivityLoginBtnChangelogintype.getText().equals(AppConstants.USE_MOBILE)) {
                mActivityLoginBtnChangelogintype.setText(AppConstants.USE_EMAIL);
                mActivityLoginEdtPassword.setVisibility(View.INVISIBLE);
                setMobileFieldConstraintsForEditText(mActivityLoginEdtEmail, 10, R.string.enter_10digit_mobileno);
                mActivityLoginBtnLogin.setText(R.string.send_otp);
                Objects.requireNonNull(mActivityLoginEdtEmail.getText()).clear();
            } else {
                mActivityLoginBtnChangelogintype.setText(AppConstants.USE_MOBILE);
                mActivityLoginEdtPassword.setVisibility(View.VISIBLE);
                setMobileFieldConstraintsForEditText(mActivityLoginEdtEmail, 50, R.string.entered_registered_email);
                mActivityLoginBtnLogin.setText(R.string.log_in);
                Objects.requireNonNull(mActivityLoginEdtEmail.getText()).clear();
            }
        });

        mActivityLoginBtnLogin.setOnClickListener(view -> {
            String btnText = mActivityLoginBtnLogin.getText().toString();
            String btnTextLogin = getResources().getString(R.string.log_in);
            String btnTextSendOtp = getResources().getString(R.string.send_otp);
            String btnTextVerifyAndContinue = getResources().getString(R.string.verify_and_continue);
            if (btnText.equalsIgnoreCase(btnTextLogin))
                onLoginPressed();
            else if (btnText.equalsIgnoreCase(btnTextSendOtp))
                onSendOtpPressed();
            else if (btnText.equalsIgnoreCase(btnTextVerifyAndContinue))
                onOtpVerifyPressed();
        });

        mBtnFacebook.setOnClickListener(view -> new FacebookLogin(LoginActivity.this, loginManager, callbackManager, responseModel -> {

        }));

        mTvForgotPassword.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
        setActionOnViews();
    }

    private void setActionOnViews() {
        mActivityLoginOtpviewEntermobileotp.setOnFinishListener(s -> {
            mActivityLoginOtpviewEntermobileotp.clearFocus();
            GlobalVariablesNMethods.closeKeyboard(LoginActivity.this);
            return null;
        });
    }

    private void onOtpVerifyPressed() {
        new VerifyOtpAndLoginPressedService(this, Objects.requireNonNull(mActivityLoginEdtEmail.getText()).toString().trim(), mActivityLoginOtpviewEntermobileotp.getStringFromFields(),
                otpResponseModel ->   sendToActivity(otpResponseModel));
    }

    private void onLoginPressed() {
        new LoginViaEmailService(this, Objects.requireNonNull(mActivityLoginEdtEmail.getText()).toString().trim(),
                Objects.requireNonNull(mActivityLoginEdtPassword.getText()).toString().trim(), responseModel -> {
            if (responseModel != null) {
                sendToActivity(responseModel);

            }
        });
    }

    private void sendToActivity(LoginResponseModel responseModel) {
        AppConstants.mLoginData = responseModel;
        SaveDataToPrefs(responseModel);
        startActivity(new Intent(this, MainActivity.class));
        finishAffinity();
    }

    private void SaveDataToPrefs(LoginResponseModel responseModel) {
        Preferences.writeString(this,Preferences.TOKEN,responseModel.getToken());
        Preferences.writeString(this,Preferences.EMAIL,responseModel.getEmail());
        Preferences.writeString(this,Preferences.NAME,responseModel.getFullName());
        Preferences.writeString(this,Preferences.USER_ID,responseModel.getUserId());
        Preferences.writeString(this,Preferences.USER_TYPE,responseModel.getUserType());
    }

    private void onSendOtpPressed() {
        new SendOtpPressedService(this, "mobile", Objects.requireNonNull(mActivityLoginEdtEmail.getText()).toString()
                , null, otpResponseModel -> makeFillOtpPageVisible());
    }

    private void makeFillOtpPageVisible() {
        mActivityLoginAlldataLayout.setVisibility(View.INVISIBLE);
        mActivityLoginAlldataLayoutotp.setVisibility(View.VISIBLE);
        mActivityLoginBtnLogin.setText(R.string.verify_and_continue);
    }

    private void setMobileFieldConstraintsForEditText(TextInputEditText mActivityLoginEdtEmail, int maxLength, int hintText) {
        mActivityLoginEdtEmail.setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        mActivityLoginEdtEmail.setFilters(FilterArray);
        mActivityLoginEdtEmail.setHint(hintText);
    }
}