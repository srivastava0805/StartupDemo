package in.startupjobs.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import in.startupjobs.R;
import in.startupjobs.model.dashBoardData.DashBoardJobsData;
import in.startupjobs.services.GetDashBoardJobsRelatedData;
import in.startupjobs.utils.Preferences;

public class DashboardFragment extends Fragment {

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initView(root);
        getDashBoardRelatedData();
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
        setNameAndProfileDetails();
        setClicks();
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
}