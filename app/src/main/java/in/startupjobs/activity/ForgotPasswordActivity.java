package in.startupjobs.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kevinschildhorn.otpview.OTPView;

import java.util.Objects;

import in.startupjobs.R;
import in.startupjobs.services.ChangePasswordService;
import in.startupjobs.services.SendOtpPressedService;
import in.startupjobs.utils.AppConstants;

public class ForgotPasswordActivity extends AppCompatActivity {
    private TextInputEditText mActivityForgotpasswordEdtEmail;
    private ConstraintLayout mActivityForgotpasswordEnterotplayout;
    private OTPView mActivityForgotpasswordOtpviewEnteremailotp;
    private ConstraintLayout mActivityForgotpasswordOrlinelayout;
    private TextInputEditText mActivityForgotpasswordEdtMobileno;
    private MaterialButton mActivityForgotpasswordBtnSubmit;
    private String textFromEmailField;
    private String textFromMobileField;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        initView();
    }

    private void initView() {
        mActivityForgotpasswordEdtEmail = findViewById(R.id.activity_forgotpassword_edt_email);
        mActivityForgotpasswordEnterotplayout = findViewById(R.id.activity_forgotpassword_enterotplayout);
        mActivityForgotpasswordOtpviewEnteremailotp = findViewById(R.id.activity_forgotpassword_otpview_enteremailotp);
        mActivityForgotpasswordOrlinelayout = findViewById(R.id.activity_forgotpassword_orlinelayout);
        mActivityForgotpasswordEdtMobileno = findViewById(R.id.activity_forgotpassword_edt_mobileno);
        mActivityForgotpasswordBtnSubmit = findViewById(R.id.activity_forgotpassword_btn_submit);
        setClicks();
    }

    private void setClicks() {
        mActivityForgotpasswordBtnSubmit.setOnClickListener(view -> {
            if (mActivityForgotpasswordEdtMobileno.getVisibility() == View.VISIBLE)
                doGetDesiredOtpProcess();
            else doGetChangePasswordProcess();
        });
    }


    private void doGetDesiredOtpProcess() {
        textFromEmailField = Objects.requireNonNull(mActivityForgotpasswordEdtEmail.getText()).toString().trim();
        textFromMobileField = Objects.requireNonNull(mActivityForgotpasswordEdtMobileno.getText()).toString().trim();
        if (!textFromEmailField.isEmpty())
            new SendOtpPressedService(ForgotPasswordActivity.this, "email", textFromEmailField, otpResponseModel -> makeOtpPageVisible(), null);
        else if (!textFromMobileField.isEmpty())
            new SendOtpPressedService(ForgotPasswordActivity.this, "mobile", textFromMobileField, null,
                    otpResponseModel -> makeOtpPageVisible());
        else
            Toast.makeText(this, "Please fill least one of the above fields..", Toast.LENGTH_SHORT).show();
    }

    private void doGetChangePasswordProcess() {
        String newPasswordText = Objects.requireNonNull(mActivityForgotpasswordEdtEmail.getText()).toString().trim();
        if (!newPasswordText.isEmpty())
            if (!textFromEmailField.isEmpty())
                new ChangePasswordService(this, "email", textFromEmailField,
                        newPasswordText,
                        Long.parseLong(mActivityForgotpasswordOtpviewEnteremailotp.getStringFromFields()),
                        otpResponseModel -> {
                            if (otpResponseModel.toString().equalsIgnoreCase(AppConstants.PASSWORD_RESET_SUCCESSFUL)) {
                                Toast.makeText(ForgotPasswordActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                            }

                        });
            else if (!textFromMobileField.isEmpty())
                new ChangePasswordService(this, "mobile", textFromMobileField,
                        newPasswordText,
                        Long.parseLong(mActivityForgotpasswordOtpviewEnteremailotp.getStringFromFields()),
                        otpResponseModel -> {

                        });
            else
                Toast.makeText(this, "Please fill the new password field", Toast.LENGTH_SHORT).show();
    }

    private void makeOtpPageVisible() {
        mActivityForgotpasswordEnterotplayout.setVisibility(View.VISIBLE);
        mActivityForgotpasswordOrlinelayout.setVisibility(View.INVISIBLE);
        mActivityForgotpasswordEdtMobileno.setVisibility(View.INVISIBLE);
        mActivityForgotpasswordEdtEmail.setHint(R.string.enter_new_password_here);
        Objects.requireNonNull(mActivityForgotpasswordEdtEmail.getText()).clear();
        setFieldToPasswordType(mActivityForgotpasswordEdtEmail);
    }

    private void setFieldToPasswordType(TextInputEditText edtField) {
        edtField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(15);
        edtField.setFilters(FilterArray);
    }
}
