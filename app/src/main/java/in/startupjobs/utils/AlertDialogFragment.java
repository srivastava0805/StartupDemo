package in.startupjobs.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import in.startupjobs.R;


public class AlertDialogFragment extends DialogFragment {
    private static DialogInterface.OnClickListener mPositiveBtnListener;
    private static DialogInterface.OnClickListener mNegativeBtnListener;

    public static AlertDialogFragment newInstance(int titleId, String message,boolean isOneButton,
                                                  DialogInterface.OnClickListener positiveBtnListener,
                                                  DialogInterface.OnClickListener negativeBtnListener) {
        AlertDialogFragment fragment = new AlertDialogFragment();

        Bundle args = new Bundle();
        args.putInt("titleId", titleId);
        args.putString("message", message);
        args.putBoolean("isOneButton", isOneButton);
        mPositiveBtnListener = positiveBtnListener;
        mNegativeBtnListener = negativeBtnListener;
        fragment.setArguments(args);

        return fragment;
    }

    public AlertDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        assert getArguments() != null;
        int titleId = getArguments().getInt("titleId");
        String message = getArguments().getString("message");
        boolean isOneButton = getArguments().getBoolean("isOneButton");
//        If you want one dynamic and one static button action alert dialog
        if (mPositiveBtnListener != null && mNegativeBtnListener == null && !isOneButton)
            return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                    .setTitle(titleId)
                    .setMessage(message)
                    .setPositiveButton(R.string.cntinue, mPositiveBtnListener)
                    .setNegativeButton(R.string.cancel, (dialog, which) -> dismiss())
                    .create();
            //        If you want both dynamic action buttons alert dialog
        else if (mPositiveBtnListener != null && mNegativeBtnListener != null && !isOneButton)
            return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                    .setTitle(titleId)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, mPositiveBtnListener)
                    .setNegativeButton(R.string.resend, mNegativeBtnListener)
                    .create();
        else {
            return new AlertDialog.Builder(getActivity(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
                    .setTitle(titleId)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, mPositiveBtnListener)
                    .create();
        }

    }
}