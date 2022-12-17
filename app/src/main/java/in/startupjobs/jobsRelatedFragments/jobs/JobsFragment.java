package in.startupjobs.jobsRelatedFragments.jobs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import in.startupjobs.R;
import in.startupjobs.adapter.JobsFragmentTabAdapter;
import in.startupjobs.adapter.SearchedJobsResultsViewAdapter;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;
import in.startupjobs.model.serachedJobs.SearchedJobsResponse;
import in.startupjobs.services.GetAppliedJobs;
import in.startupjobs.services.GetSearchedJobs;
import in.startupjobs.utils.GlobalVariablesNMethods;

public class JobsFragment extends Fragment implements GetSearchedJobs.onResponseSearchedJobs {

    private JobsViewModel jobsViewModel;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private JobsFragmentTabAdapter adapter;
    private TextInputEditText mSearchEdtTextSearch;
    private AppCompatImageView mIvSearch;
    private RecyclerView mHomeRecyclerviewFoundjobs;
    private SearchedJobsResultsViewAdapter jobsFragmentViewAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        jobsViewModel =
                ViewModelProviders.of(this).get(JobsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        initView(root);

        createTabFragment();
        return root;
    }

    private void createTabFragment() {
        adapter = new JobsFragmentTabAdapter(getChildFragmentManager(), tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView(View root) {
        viewPager = root.findViewById(R.id.viewpager_jf);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = root.findViewById(R.id.tabLayout_jf);
        mSearchEdtTextSearch = root.findViewById(R.id.search_edtText_search);
        mIvSearch = root.findViewById(R.id.iv_search);
        mHomeRecyclerviewFoundjobs = root.findViewById(R.id.home_recyclerview_foundjobs);


        setsClick();

    }

    private void setsClick() {
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSearchEdtTextSearch.getText().toString().trim().isEmpty())
                    getCommaSeparatedString(mSearchEdtTextSearch.getText().toString().trim());

            }
        });
    }

    private void getCommaSeparatedString(String searchString) {
        String[] keywordArray = searchString.split(",");
        if (keywordArray.length == 3) {
            new GetSearchedJobs(getActivity(), keywordArray[0], keywordArray[1], keywordArray[2], this);
        } else if (keywordArray.length == 2) {
            new GetSearchedJobs(getActivity(), keywordArray[0], keywordArray[1], "", this);
        } else {
            new GetSearchedJobs(getActivity(), keywordArray[0], "", "", this);
        }
    }

    @Override
    public void sendSearchedJobsResponse(SearchedJobsResponse appliedJobsResponseList) {

        mHomeRecyclerviewFoundjobs.setHasFixedSize(true);
        mHomeRecyclerviewFoundjobs.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (appliedJobsResponseList.getResults().size() > 0) {
            viewPager.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            mHomeRecyclerviewFoundjobs.setVisibility(View.VISIBLE);
            GlobalVariablesNMethods.closeKeyboard(getActivity());
            jobsFragmentViewAdapter = new SearchedJobsResultsViewAdapter(getActivity(), appliedJobsResponseList);
            mHomeRecyclerviewFoundjobs.setAdapter(jobsFragmentViewAdapter);
        } else {
            viewPager.setVisibility(View.VISIBLE);
            tabLayout.setVisibility(View.VISIBLE);
            mHomeRecyclerviewFoundjobs.setVisibility(View.GONE);
            Toast.makeText(getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
        }
    }
}


