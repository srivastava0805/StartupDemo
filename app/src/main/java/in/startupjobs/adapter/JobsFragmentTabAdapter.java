package in.startupjobs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import in.startupjobs.jobsRelatedFragments.appliedJobs.AppliedJobsFragment;
import in.startupjobs.jobsRelatedFragments.recommendedJobs.RecommendedJobsFragment;
import in.startupjobs.jobsRelatedFragments.savedJobs.SavedJobsFragment;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;


public class JobsFragmentTabAdapter extends FragmentStatePagerAdapter {
    TabLayout tabLayout;
    FragmentManager fm;
    List<AppliedJobsResponse> appliedJobsResponseList;
    public JobsFragmentTabAdapter(FragmentManager fm, TabLayout _tabLayout, List<AppliedJobsResponse> appliedJobsResponseList) {
        super(fm);
        this.tabLayout = _tabLayout;
        this.appliedJobsResponseList = appliedJobsResponseList;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new RecommendedJobsFragment();
        }
        else if (position == 1)
        {
            fragment = new AppliedJobsFragment(appliedJobsResponseList);

        }
        else if (position == 2)
        {
            fragment = new SavedJobsFragment();

        }
        return fragment;
    }
    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Recommended Jobs";
        }
        else if (position == 1)
        {
            title = "Applied Jobs";
        }
        else if (position == 2)
        {
            title = "Saved Jobs";
        }
        return title;
    }
}
