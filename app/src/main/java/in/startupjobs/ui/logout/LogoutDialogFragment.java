package in.startupjobs.ui.logout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import in.startupjobs.R;
import in.startupjobs.utils.Preferences;

public class LogoutDialogFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle(R.string.logout)
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                    Preferences.clearPref(getActivity());
                    getActivity().finish();
                })
                .setNegativeButton(getString(R.string.no), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    getActivity().onBackPressed();
                });
        alertDialog.create().show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public static String TAG = "PurchaseConfirmationDialog";
}

