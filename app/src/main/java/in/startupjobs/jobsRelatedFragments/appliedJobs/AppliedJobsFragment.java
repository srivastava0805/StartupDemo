package in.startupjobs.jobsRelatedFragments.appliedJobs;

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
import java.util.List;

import in.startupjobs.R;
import in.startupjobs.adapter.JobsFragmentViewAdapter;
import in.startupjobs.model.Product;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;
import in.startupjobs.services.GetAppliedJobs;

public class AppliedJobsFragment extends Fragment {

    private AppliedJobsViewModel appliedJobsViewModel;

    private Context context;
    private RecyclerView recyclerView;
    JobsFragmentViewAdapter jobsFragmentViewAdapter;
    private List<AppliedJobsResponse> jobList = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        appliedJobsViewModel =
                ViewModelProviders.of(this).get(AppliedJobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        this.context = getContext();
        recyclerView = root.findViewById(R.id.recyclerview_fragment_recommendedjobs);

        new GetAppliedJobs(getActivity(), new GetAppliedJobs.onResponseAppliedJobs() {
            @Override
            public void sendAppliedJobsResponse(List<AppliedJobsResponse> appliedJobsResponseList) {
                jobsFragmentViewAdapter = new JobsFragmentViewAdapter(context, appliedJobsResponseList);
                recyclerView.setAdapter(jobsFragmentViewAdapter);
            }
        });


        return root;
    }

}