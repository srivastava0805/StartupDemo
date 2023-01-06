package in.startupjobs.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

import in.startupjobs.R;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.services.GetProfileDetailsByIdService;
import in.startupjobs.ui.dashboard.DashboardFragment;
import in.startupjobs.utils.AppConstants;

public class MainActivity extends AppCompatActivity implements DashboardFragment.onCompaniesViewAllClick, DashboardFragment.onJobsViewAllClick {

    private static final float END_SCALE = 0.85f;
    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavView;
    private CoordinatorLayout contentView;
    private ExtendedFloatingActionButton fabJobs;
    private TextView mTvUserName;
    private ShapeableImageView mIvUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initNavigation();


    }

    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    private void initNavigation() {

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavView = findViewById(R.id.bottom_nav_view);
        contentView = findViewById(R.id.content_view);
        View header = navigationView.getHeaderView(0);
        mTvUserName = header.findViewById(R.id.navdrawer_tv_name);
        mIvUserImage = header.findViewById(R.id.navdrawer_iv_user);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_blog, R.id.nav_aboutUs,
                R.id.nav_contactUs, R.id.nav_logout,
                R.id.bottom_companies,
                R.id.bottom_jobs, R.id.bottom_profile)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(bottomNavView, navController);

        mTvUserName.setText(AppConstants.mLoginData.getFullName());
        animateNavigationDrawer();
        getDataForPublicProfileById();

    }

    private void animateNavigationDrawer() {
        drawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        bottomNavView.getMenu().getItem(0).setChecked(true);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUserImage();

    }

    private void setUserImage() {
        if (AppConstants.publicProfileDetailsByIDResponse != null
                && AppConstants.publicProfileDetailsByIDResponse.getAccount() != null
                && AppConstants.publicProfileDetailsByIDResponse.getAccount().getAvatar() != null
                && !AppConstants.publicProfileDetailsByIDResponse.getAccount().getAvatar().isEmpty())
            Glide.with(this)
                    .load(AppConstants.publicProfileDetailsByIDResponse.getAccount().getAvatar())
                    .into(mIvUserImage);
    }

    private void getDataForPublicProfileById() {
        new GetProfileDetailsByIdService(this, new GetProfileDetailsByIdService.onResponseGetPublicProfileDetailsById() {
            @Override
            public void sendProfileDetailsByIdResponse(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse) {
                AppConstants.publicProfileDetailsByIDResponse = publicProfileDetailsByIDResponse;
                setUserImage();
            }
        });
    }

    @Override
    public void performCompaniesViewAllClick() {
        View view = bottomNavView.findViewById(R.id.bottom_companies);
        view.performClick();
    }

    @Override
    public void performJobsViewAllClick() {
        View view = bottomNavView.findViewById(R.id.bottom_jobs);
        view.performClick();
    }
}
