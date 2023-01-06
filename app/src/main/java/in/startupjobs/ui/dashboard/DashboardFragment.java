package in.startupjobs.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import in.startupjobs.R;
import in.startupjobs.activity.MainActivity;
import in.startupjobs.adapter.SearchedCompaniesResultsViewAdapter;
import in.startupjobs.adapter.SearchedJobsResultsViewAdapter;
import in.startupjobs.adapter.TopCompaniesDashboardAdapter;
import in.startupjobs.model.dashBoardData.DashBoardJobsData;
import in.startupjobs.model.serachedJobs.SearchedJobsResponse;
import in.startupjobs.services.GetDashBoardJobsRelatedData;
import in.startupjobs.services.GetRecommendedJobs;
import in.startupjobs.services.GetSearchedCompanies;
import in.startupjobs.services.GetSearchedJobs;
import in.startupjobs.utils.GlobalVariablesNMethods;
import in.startupjobs.utils.Preferences;

public class DashboardFragment extends Fragment implements GetSearchedJobs.onResponseSearchedJobs {

    private DashboardViewModel dashboardViewModel;
    CardView cardView;
    private ConstraintLayout mSearchLayout;
    private TextInputEditText mSearch;
    private AppCompatImageView mIvSearch;
    private ImageView mIvVoiceSearch;
    private CircularProgressIndicator mFdCpProfile;
    private TextView mTvFdProfilePercentage;
    private TextView mTvFdProfileName;
    private TextView mTvFdProfileLastupdated;
    private ImageView mIvDfArrow;
    private AppCompatCheckBox mCbDfAccountCreated;
    private AppCompatCheckBox mCbDfCvUpload;
    private AppCompatCheckBox mCbDfCompleteProfile;
    private AppCompatCheckBox mCbDfSetJobPref;
    private AppCompatCheckBox mCbDfApply4job;
    private Group mCardGroupDf;
    private TextView mTotaljobsTextviewValue;
    private TextView mIntreviewTextviewValue;
    private TextView mOfferlettersTextviewValue;
    private TextView mWithdrawnTextviewValue;
    private RecyclerView mHomeRecyclerviewFoundjobs;
    ConstraintLayout mCardProfile;
    ConstraintLayout mCardTotalJobsApplied;
    ConstraintLayout mCardInterviewed;
    ConstraintLayout mCardOfferLetter;
    ConstraintLayout mCardWithdrawn;
    private SearchedJobsResultsViewAdapter jobsFragmentViewAdapter;
    private AppCompatTextView mCompaniesTextviewViewall;
    private RecyclerView mCompaniesRecyclerviewCompanies;
    private TopCompaniesDashboardAdapter searchedCompanyAdapter;
    public onCompaniesViewAllClick onCompaniesViewAllClick;
    public onJobsViewAllClick onJobsViewAllClick;
    private AppCompatTextView mRecommendedjobsTextviewViewall;
    private RecyclerView mRecommendedjobsRecyclerviewRecommendedjobs;

    public interface onCompaniesViewAllClick {
        void performCompaniesViewAllClick();
    }

    public interface onJobsViewAllClick {
        void performJobsViewAllClick();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(root);
        getDashBoardRelatedData();

        onCompaniesViewAllClick = (MainActivity) getActivity();
        onJobsViewAllClick = (MainActivity) getActivity();
        getCompaniesDataRequest();
        getRecommendedJobsDataRequest();
        return root;
    }

    private void initView(View root) {
        cardView = root.findViewById(R.id.cardview_df_profile);
        mSearchLayout = root.findViewById(R.id.searchLayout);
        mSearch = root.findViewById(R.id.search_edtText_search);
        mIvSearch = root.findViewById(R.id.iv_search);
        mIvVoiceSearch = root.findViewById(R.id.iv_voice_search);
        mFdCpProfile = root.findViewById(R.id.fd_cp_profile);
        mTvFdProfilePercentage = root.findViewById(R.id.tv_fd_profile_percentage);
        mTvFdProfileName = root.findViewById(R.id.tv_fd_profile_name);
        mTvFdProfileLastupdated = root.findViewById(R.id.tv_fd_profile_lastupdated);
        mIvDfArrow = root.findViewById(R.id.iv_df_arrow);
        mCbDfAccountCreated = root.findViewById(R.id.cb_df_account_created);
        mCbDfCvUpload = root.findViewById(R.id.cb_df_cv_upload);
        mCbDfCompleteProfile = root.findViewById(R.id.cb_df_complete_profile);
        mCbDfSetJobPref = root.findViewById(R.id.cb_df_set_job_pref);
        mCbDfApply4job = root.findViewById(R.id.cb_df_apply4job);
        mCardGroupDf = root.findViewById(R.id.card_group_df);
        mTotaljobsTextviewValue = root.findViewById(R.id.totaljobs_textview_value);
        mIntreviewTextviewValue = root.findViewById(R.id.intreview_textview_value);
        mOfferlettersTextviewValue = root.findViewById(R.id.offerletters_textview_value);
        mWithdrawnTextviewValue = root.findViewById(R.id.withdrawn_textview_value);
        mHomeRecyclerviewFoundjobs = root.findViewById(R.id.home_recyclerview_foundjobs);
        mCardProfile = root.findViewById(R.id.layout_df_profile);
        mCardTotalJobsApplied = root.findViewById(R.id.layout_df_totlajobsappliedcard);
        mCardInterviewed = root.findViewById(R.id.layout_df_interviewedcard);
        mCardOfferLetter = root.findViewById(R.id.layout_df_offerlettercard);
        mCardWithdrawn = root.findViewById(R.id.layout_df_withdrawncard);
        mCompaniesTextviewViewall = root.findViewById(R.id.companies_textview_viewall);
        mCompaniesRecyclerviewCompanies = root.findViewById(R.id.companies_recyclerview_companies);
        mRecommendedjobsTextviewViewall = root.findViewById(R.id.recommendedjobs_textview_viewall);
        mRecommendedjobsRecyclerviewRecommendedjobs = root.findViewById(R.id.recommendedjobs__recyclerview_recommendedjobs_);
        setNameAndProfileDetails();
        setClicks();

    }

    private void getCompaniesDataRequest() {
        new GetSearchedCompanies(getActivity(), 5, "", getCompaniesResponse -> {
            if (getCompaniesResponse != null) {
                LinearLayoutManager HorizontalLayout
                        = new LinearLayoutManager(
                        getActivity(),
                        LinearLayoutManager.HORIZONTAL,
                        false);
                mCompaniesRecyclerviewCompanies.setLayoutManager(HorizontalLayout);
                searchedCompanyAdapter = new TopCompaniesDashboardAdapter(getActivity(), getCompaniesResponse, true);
                mCompaniesRecyclerviewCompanies.setAdapter(searchedCompanyAdapter);
            }
        });
    }

    private void getRecommendedJobsDataRequest() {
        new GetRecommendedJobs(getActivity(), "3", new GetRecommendedJobs.onResponseRecommendedJobs() {
            @Override
            public void sendRecommendedJobsResponse(SearchedJobsResponse recommendedJobs) {
                if (recommendedJobs.getResults().size() > 0) {
                    GlobalVariablesNMethods.closeKeyboard(getActivity());
                    jobsFragmentViewAdapter = new SearchedJobsResultsViewAdapter(getActivity(), recommendedJobs);
                    mRecommendedjobsRecyclerviewRecommendedjobs.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecommendedjobsRecyclerviewRecommendedjobs.setAdapter(jobsFragmentViewAdapter);
                } else {
                    mRecommendedjobsRecyclerviewRecommendedjobs.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setClicks() {
        mIvDfArrow.setOnClickListener(view -> {
            if (mCardGroupDf.getVisibility() == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                mCardGroupDf.setVisibility(View.GONE);
                mIvDfArrow.setImageResource(R.drawable.icon_arrow_down);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                mCardGroupDf.setVisibility(View.VISIBLE);
                mIvDfArrow.setImageResource(R.drawable.icon_arrow_up);
            }
        });
        mIvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mSearch.getText().toString().trim().isEmpty())
                    getCommaSeparatedString(mSearch.getText().toString().trim());
                else
                    Toast.makeText(getActivity(), "Enter something in search box", Toast.LENGTH_SHORT).show();

            }
        });
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() < 1)
                    setDashBoardDataLayoutVisible();
            }
        });
        mCompaniesTextviewViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCompaniesViewAllClick.performCompaniesViewAllClick();
            }
        });
        mRecommendedjobsTextviewViewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onJobsViewAllClick.performJobsViewAllClick();
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


    private void getDashBoardRelatedData() {

        new GetDashBoardJobsRelatedData(getActivity(), new GetDashBoardJobsRelatedData.onResponseGetJobRelatedDataForDashBoard() {
            @Override
            public void sendDashBoardJobsDataResponse(DashBoardJobsData dashBoardJobsData) {
                if (dashBoardJobsData != null) {
                    setDataForBlockCards(dashBoardJobsData);
                }
            }
        });
    }

    private void setNameAndProfileDetails() {
        mTvFdProfileName.setText(Preferences.readString(getActivity(), Preferences.NAME, ""));
    }

    private void setDataForBlockCards(DashBoardJobsData dashBoardJobsData) {
        mTotaljobsTextviewValue.setText(dashBoardJobsData.getCounts().getTotalApplications().toString());
        mIntreviewTextviewValue.setText(dashBoardJobsData.getCounts().getTotalInterviewed().toString());
        mOfferlettersTextviewValue.setText(dashBoardJobsData.getCounts().getTotalOfferLetterRecieved().toString());
        mWithdrawnTextviewValue.setText(dashBoardJobsData.getCounts().getTotalWithdrawn().toString());
    }

    @Override
    public void sendSearchedJobsResponse(SearchedJobsResponse appliedJobsResponseList) {

        mHomeRecyclerviewFoundjobs.setHasFixedSize(true);
        mHomeRecyclerviewFoundjobs.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (appliedJobsResponseList.getResults().size() > 0) {
            setRecyclerVewVisible();
            GlobalVariablesNMethods.closeKeyboard(getActivity());
            jobsFragmentViewAdapter = new SearchedJobsResultsViewAdapter(getActivity(), appliedJobsResponseList);
            mHomeRecyclerviewFoundjobs.setAdapter(jobsFragmentViewAdapter);
        } else {
            setDashBoardDataLayoutVisible();
            mHomeRecyclerviewFoundjobs.setVisibility(View.GONE);
            Toast.makeText(getContext(), "No Results Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setDashBoardDataLayoutVisible() {
        mCardProfile.setVisibility(View.VISIBLE);
        mCardInterviewed.setVisibility(View.VISIBLE);
        mCardOfferLetter.setVisibility(View.VISIBLE);
        mCardTotalJobsApplied.setVisibility(View.VISIBLE);
        mCardWithdrawn.setVisibility(View.VISIBLE);
        mHomeRecyclerviewFoundjobs.setVisibility(View.GONE);
    }

    private void setRecyclerVewVisible() {
        mCardProfile.setVisibility(View.GONE);
        mCardInterviewed.setVisibility(View.GONE);
        mCardOfferLetter.setVisibility(View.GONE);
        mCardTotalJobsApplied.setVisibility(View.GONE);
        mCardWithdrawn.setVisibility(View.GONE);
        mHomeRecyclerviewFoundjobs.setVisibility(View.VISIBLE);
    }
}