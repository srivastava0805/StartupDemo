package in.startupjobs.jobsRelatedFragments.recommendedJobs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.startupjobs.R;
import in.startupjobs.adapter.JobsFragmentViewAdapter;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;

public class RecommendedJobsFragment extends Fragment {
    private RecommendedJobsViewModel recommendedJobsViewModel;
    private Context context;
    private RecyclerView recyclerView;
    private JobsFragmentViewAdapter jobsFragmentViewAdapter;
    private ArrayList<AppliedJobsResponse> jobsResponseArrayList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recommendedJobsViewModel =
                ViewModelProviders.of(this).get(RecommendedJobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        this.context = getContext();
        recyclerView = root.findViewById(R.id.recyclerview_fragment_appliedjobs);
        jobsFragmentViewAdapter = new JobsFragmentViewAdapter(context, jobsResponseArrayList);
        recyclerView.setAdapter(jobsFragmentViewAdapter);

        return root;
    }


}