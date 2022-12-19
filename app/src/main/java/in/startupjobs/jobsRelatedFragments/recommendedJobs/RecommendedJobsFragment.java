package in.startupjobs.jobsRelatedFragments.recommendedJobs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import in.startupjobs.R;
import in.startupjobs.adapter.SearchedJobsResultsViewAdapter;
import in.startupjobs.model.serachedJobs.SearchedJobsResponse;
import in.startupjobs.services.GetRecommendedJobs;
import in.startupjobs.utils.GlobalVariablesNMethods;

public class RecommendedJobsFragment extends Fragment {
    private RecommendedJobsViewModel recommendedJobsViewModel;
    private Context context;
    private RecyclerView recyclerView;
    private SearchedJobsResultsViewAdapter jobsFragmentViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        recommendedJobsViewModel =
                ViewModelProviders.of(this).get(RecommendedJobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);

        this.context = getContext();
        recyclerView = root.findViewById(R.id.recyclerview_fragment_appliedjobs);

        new GetRecommendedJobs(getActivity(), "10", new GetRecommendedJobs.onResponseRecommendedJobs() {
            @Override
            public void sendRecommendedJobsResponse(SearchedJobsResponse recommendedJobs) {
                if (recommendedJobs.getResults().size() > 0) {
                    GlobalVariablesNMethods.closeKeyboard(getActivity());
                    jobsFragmentViewAdapter = new SearchedJobsResultsViewAdapter(getActivity(), recommendedJobs);
                    recyclerView.setAdapter(jobsFragmentViewAdapter);
                } else {
                    recyclerView.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }


}