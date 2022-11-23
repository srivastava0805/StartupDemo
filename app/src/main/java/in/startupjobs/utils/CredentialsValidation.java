package in.startupjobs.utils;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.util.Objects;

public class CredentialsValidation {

    public Boolean validateName(TextInputLayout regName) {
        String val = Objects.requireNonNull(regName.getEditText()).getText().toString();

        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        } else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateEmail(TextInputLayout regEmail, Activity activity) {

//        Objects.requireNonNull(regEmail.getEditText()).setOnFocusChangeListener(this);

        String val = Objects.requireNonNull(regEmail.getEditText()).getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            requestFocus(regEmail.getEditText(), activity);
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            requestFocus(regEmail.getEditText(), activity);
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateEmail(TextInputLayout regEmail) {

        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validatePassword(TextInputLayout regPassword, Activity activity) {
//        Objects.requireNonNull(regPassword.getEditText()).setOnFocusChangeListener(this);
        String val = Objects.requireNonNull(regPassword.getEditText()).getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,15}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            requestFocus(regPassword.getEditText(), activity);
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            requestFocus(regPassword.getEditText(), activity);
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


    public Boolean validatePassword(TextInputLayout regPassword) {
        String val = Objects.requireNonNull(regPassword.getEditText()).getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
//                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,15}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }


    public Boolean validatePhoneNo(TextInputLayout regPhoneNo) {
        String val = Objects.requireNonNull(regPhoneNo.getEditText()).getText().toString();
//        String regex = "(0/91)?[7-9][0-9]{9}";
        String regex = "\\d{10}";
        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(regex)) {
            regPhoneNo.setError("Please provide a valid phone number");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateUsername(TextInputLayout regUsername) {
        String val = Objects.requireNonNull(regUsername.getEditText()).getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        } else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean confirmPassword(TextInputLayout password, TextInputLayout cPassword) {
        String passwordVal = Objects.requireNonNull(password.getEditText()).getText().toString();
        String cPasswordVal = Objects.requireNonNull(cPassword.getEditText()).getText().toString();

        if (cPasswordVal.isEmpty()) {
            cPassword.setError("Field cannot be empty");
            return false;
        } else if (!passwordVal.equals(cPasswordVal)) {
            password.setError("Passwords do not match");
            cPassword.setError("Passwords do not match");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            cPassword.setError(null);
            cPassword.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean validateOTP(TextInputLayout otpLayout) {
        int otpValue = Objects.requireNonNull(otpLayout.getEditText()).getText().length();
        if (otpLayout.getVisibility() == View.VISIBLE && otpValue >= 4 && otpValue <= 6) {
            otpLayout.setError(null);
            otpLayout.setErrorEnabled(false);
            return true;
        } else {
            otpLayout.setError("Please enter a valid OTP");
            return false;
        }
    }

    private void requestFocus(View view, Activity activity) {
        if (view.requestFocus()) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public Boolean validateJobType(TextInputLayout jobTypeSpinnerLayout) {
        AutoCompleteTextView autoCompleteTextView;
        autoCompleteTextView = (AutoCompleteTextView) jobTypeSpinnerLayout.getEditText();
        String jobTypeText = String.valueOf(autoCompleteTextView.getText());
//        Log.e("YYYYYYYYDDDDDDD", jobTypeText);
        if (jobTypeText.equalsIgnoreCase("")) {
            jobTypeSpinnerLayout.setError("Please select Job type");
            jobTypeSpinnerLayout.setErrorEnabled(true);
            return false;
        } else {
            jobTypeSpinnerLayout.setError(null);
            jobTypeSpinnerLayout.setErrorEnabled(false);
            return true;
        }
    }

    public Boolean isResumeUploaded(Uri uri, TextView errorMsg) {
        if (uri == null) {
            errorMsg.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorMsg.setVisibility(View.GONE);
            return true;
        }
    }

    public Boolean isResumeUploaded(File file, TextView errorMsg) {
        if (file == null) {
            errorMsg.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorMsg.setVisibility(View.GONE);
            return true;
        }
    }

    public Boolean isResumeUploaded(File file, Uri uri, TextView errorMsg) {
        if (file == null && uri == null) {
            errorMsg.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorMsg.setVisibility(View.GONE);
            return true;
        }
    }

    public static Boolean isUserAuthenticated(Context context){
        String token= Preferences.readString(context, Preferences.TOKEN,
                "");
        return token != null && !token.isEmpty();
    }
}
