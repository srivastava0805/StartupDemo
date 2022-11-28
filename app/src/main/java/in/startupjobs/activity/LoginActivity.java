package in.startupjobs.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.ImageView;
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
import in.startupjobs.model.OtpResponseModel;
import in.startupjobs.model.login.LoginResponseModel;
import in.startupjobs.services.FacebookLogin;
import in.startupjobs.services.LoginViaEmailService;
import in.startupjobs.services.SendOtpPressedService;
import in.startupjobs.services.VerifyOtpAndLoginPressedService;
import in.startupjobs.utils.AppConstants;

public class LoginActivity extends AppCompatActivity {


    private ImageView mIvOnboardingStartuptitle;
    private ImageView mIvStartuplogo;
    private TextView mTvWelcometitle;
    private TextInputEditText mActivityLoginEdtEmail;
    private TextInputEditText mActivityLoginEdtPassword;
    private MaterialButton mActivityLoginBtnLogin;
    private TextView mTvForgotPassword;
    private MaterialButton mBtnFacebook;
    private MaterialButton mBtnGoogle;
    private MaterialButton mBtnLinkedin;
    private ImageView mIvStartuptitle;
    private AppCompatButton mActivityLoginBtnChangelogintype;
    private ConstraintLayout mActivityLoginAlldataLayout;
    private ConstraintLayout mActivityLoginEmailConstraintlayout;
    private ConstraintLayout mActivityLoginAlldataLayoutotp;
    private OTPView mActivityLoginOtpviewEntermobileotp;
    private TextView mActivityLoginTextviewResendmobileotp;
    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        initView();
    }

    private void initView() {
        mIvOnboardingStartuptitle = findViewById(R.id.iv_onboarding_startuptitle);
        mIvStartuplogo = findViewById(R.id.iv_startuplogo);
        mTvWelcometitle = findViewById(R.id.tv_welcometitle);
        mActivityLoginEdtEmail = findViewById(R.id.activity_login_edt_email);
        mActivityLoginEdtPassword = findViewById(R.id.activity_login_edt_password);
        mActivityLoginBtnLogin = findViewById(R.id.activity_login_btn_login);
        mTvForgotPassword = findViewById(R.id.tv_forgot_password);
        mBtnFacebook = findViewById(R.id.btn_facebook);
        mBtnGoogle = findViewById(R.id.btn_google);
        mBtnLinkedin = findViewById(R.id.btn_linkedin);
        mIvStartuptitle = findViewById(R.id.iv_startuptitle);
        mActivityLoginBtnChangelogintype = findViewById(R.id.activity_login_btn_changelogintype);
        mActivityLoginAlldataLayout = findViewById(R.id.activity_login_alldata_layout);
        mActivityLoginEmailConstraintlayout = findViewById(R.id.activity_login_email_constraintlayout);
        mActivityLoginAlldataLayoutotp = findViewById(R.id.activity_login_alldata_layoutotp);
        mActivityLoginOtpviewEntermobileotp = findViewById(R.id.activity_login_otpview_entermobileotp);
        mActivityLoginTextviewResendmobileotp = findViewById(R.id.activity_login_textview_resendmobileotp);
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
        setupClicks();
    }

    private void setupClicks() {
        mActivityLoginBtnChangelogintype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        mActivityLoginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        mBtnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FacebookLogin(LoginActivity.this, loginManager, callbackManager, new FacebookLogin.onResponseLoginViaFacebook() {
                    @Override
                    public void sendFacebookLoginResponse(LoginResponseModel responseModel) {

                    }
                });
            }
        });
    }

    private void onOtpVerifyPressed() {
        new VerifyOtpAndLoginPressedService(this, mActivityLoginEdtEmail.getText().toString().trim(),mActivityLoginOtpviewEntermobileotp.getStringFromFields(),
                new VerifyOtpAndLoginPressedService.onResponseVerifyMobileOtp() {
                    @Override
                    public void sendMobileOtpResponse(LoginResponseModel otpResponseModel) {
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void onLoginPressed() {
        new LoginViaEmailService(this, mActivityLoginEdtEmail.getText().toString().trim(),
                mActivityLoginEdtPassword.getText().toString().trim(), new LoginViaEmailService.onResponseLoginViaEmail() {
            @Override
            public void sendMobileOtpResponse(LoginResponseModel responseModel) {
                if (responseModel != null) {
                    AppConstants.mLoginData = responseModel;
                    Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onSendOtpPressed() {
        new SendOtpPressedService(this, "mobile", mActivityLoginEdtEmail.getText().toString()
                , null, new SendOtpPressedService.onResponseSendMobileOtp() {
            @Override
            public void sendMobileOtpResponse(OtpResponseModel otpResponseModel) {
                makeFillOtpPageVisible();
            }
        });
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