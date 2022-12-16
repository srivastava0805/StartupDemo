package in.startupjobs.adapters;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import in.startupjobs.ui.slideshow.SlideshowFragment;
import in.startupjobs.ui.tools.ToolsFragment;

public class JobsFragmentTabAdapter extends FragmentStatePagerAdapter {
    TabLayout tabLayout;
    FragmentManager fm;
    public JobsFragmentTabAdapter(FragmentManager fm, TabLayout _tabLayout) {
        super(fm);
        this.tabLayout = _tabLayout;
    }
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0)
        {
            fragment = new ToolsFragment();
//            Toast.makeText(getActivity(), "Tab 1", Toast.LENGTH_SHORT).show();
        }
        else if (position == 1)
        {
            fragment = new SlideshowFragment();
//            Toast.makeText(getActivity(), "Tab 2", Toast.LENGTH_SHORT).show();

        }
        else if (position == 2)
        {
            fragment = new SlideshowFragment();
//            Toast.makeText(getActivity(), "Tab 2", Toast.LENGTH_SHORT).show();

        }
        return fragment;
    }
    @Override
    public int getCount() {
//           return tabLayout.getTabCount() > 0 ? tabLayout.getTabCount() : 3;
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
