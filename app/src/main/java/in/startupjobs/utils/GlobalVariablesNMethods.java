package in.startupjobs.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import in.startupjobs.model.applyJob.AddUpdateCandidateDetail;

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

    public static void closeKeyboard(@NonNull Activity activity)
    {
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
    public static String convertDate(String rawFormat,String newFormat){
        String formattedDate="";
        DateTimeFormatter inputFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(newFormat, Locale.ENGLISH);
            LocalDate date = LocalDate.parse(rawFormat, inputFormatter);
           formattedDate = outputFormatter.format(date);
            System.out.println(formattedDate);
        }
        else {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat(newFormat);
            Date date = null;
            try {
                date = inputFormat.parse(rawFormat);
            } catch (ParseException e) {
                e.printStackTrace();
            }
             formattedDate = outputFormat.format(date);
            System.out.println(formattedDate);
        }
        return formattedDate;
    }

    public static boolean isStringValid(String value){
        return value != null && !value.isEmpty();
    }
}
