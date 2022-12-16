package in.startupjobs.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import in.startupjobs.R;
import in.startupjobs.adapters.JobsFragmentTabAdapter;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;
import in.startupjobs.services.GetAppliedJobs;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    private JobsFragmentTabAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        new GetAppliedJobs(getActivity(), new GetAppliedJobs.onResponseAppliedJobs() {
            @Override
            public void sendAppliedJobsResponse(List<AppliedJobsResponse> appliedJobsResponseList) {
                Toast.makeText(getActivity(), "Got Apple", Toast.LENGTH_SHORT).show();
            }
        });

        viewPager = (ViewPager) root.findViewById(R.id.viewpager_jf);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = (TabLayout) root.findViewById(R.id.tabLayout_jf);
        createTabFragment();

        return root;
    }

    private void createTabFragment() {
        adapter = new JobsFragmentTabAdapter(getChildFragmentManager(), tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        requireActivity().onBackPressed();
//        return true;
//    }
}