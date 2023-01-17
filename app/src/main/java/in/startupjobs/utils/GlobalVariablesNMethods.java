package in.startupjobs.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalVariablesNMethods {

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void progressDialog(String msg, Context context, boolean status) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        if (status) progressDialog.show();
        else progressDialog.dismiss();
    }

    public static void closeKeyboard(@NonNull Activity activity) {
        // this will give us the view
        // which is currently focus
        // in this layout
        View view = activity.getCurrentFocus();

        // if nothing is currently
        // focus then this will protect
        // the app from crash
        if (view != null) {

            // now assign the system
            // service to InputMethodManager
            InputMethodManager manager
                    = (InputMethodManager)
                    activity.getSystemService(
                            Context.INPUT_METHOD_SERVICE);
            manager
                    .hideSoftInputFromWindow(
                            view.getWindowToken(), 0);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String convertDate(Context ctx, String givenDate, String inputFormat, String outputFormat) {
        if (inputFormat == null)
            inputFormat = "yyyy-MM-dd";
        else if (inputFormat.isEmpty()) {
            inputFormat = "yyyy-MM-dd";
        }
        SimpleDateFormat spf = new SimpleDateFormat(inputFormat);
        Date newDate = null;
        try {
            newDate = spf.parse(givenDate);
            spf = new SimpleDateFormat(outputFormat);
            String date = spf.format(newDate);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


    }
    public static boolean checkPermission(String permission, Activity act) {
        int result = ContextCompat.checkSelfPermission(act, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean isStringValid(String value) {
        return value != null && !value.isEmpty();
    }
}
