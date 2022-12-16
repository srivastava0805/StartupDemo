package in.startupjobs.jobsRelatedFragments.savedJobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import in.startupjobs.R;

public class SavedJobsFragment extends Fragment {

    private SavedJobsViewModel savedJobsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        savedJobsViewModel =
                ViewModelProviders.of(this).get(SavedJobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_company, container, false);
        savedJobsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }
}