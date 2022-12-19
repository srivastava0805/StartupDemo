package in.startupjobs.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import in.startupjobs.jobsRelatedFragments.appliedJobs.AppliedJobsFragment;
import in.startupjobs.jobsRelatedFragments.recommendedJobs.RecommendedJobsFragment;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;


public class JobsFragmentTabAdapter extends FragmentStatePagerAdapter {
    TabLayout tabLayout;

    public JobsFragmentTabAdapter(FragmentManager fm, TabLayout _tabLayout) {
        super(fm);
        this.tabLayout = _tabLayout;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new RecommendedJobsFragment();
        } else if (position == 1) {
            fragment = new AppliedJobsFragment();

        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Recommended Jobs";
        } else if (position == 1) {
            title = "Applied Jobs";
        }

        return title;
    }
}
