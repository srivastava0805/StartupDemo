package in.startupjobs.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

import in.startupjobs.R;
import in.startupjobs.activity.EditProfileDetailsActivity;
import in.startupjobs.adapter.WorkExperienceAdapter;
import in.startupjobs.model.basicDetails.BasicDetailsReponse;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import in.startupjobs.services.GetBasicDetailsService;
import in.startupjobs.services.GetProfileDetailsByIdService;
import in.startupjobs.services.GetWorkExperienceService;
import in.startupjobs.utils.AppConstants;

public class ProfileFragment extends Fragment {

    private Guideline mGuidelineVertical;
    private Guideline mGuidelineVertical2;
    private ShapeableImageView mProfileShapeivProfile;
    private TextView mProfileTvName;
    private TextView mProfileTvDesignation;
    private AppCompatTextView mProfileTvPlaceholderprofiletext;
    private AppCompatTextView mProfileTvProfilecompletionpercentage;
    private LinearProgressIndicator mProfileProgressindicatorProfilecompletionpercentage;
    private AppCompatTextView mProfileTvLastupdated;
    private MaterialCardView mProfileCardviewProfilepercentagelayout;
    private AppCompatTextView mProfileTvResumelastupdated;
    private AppCompatImageView mProfileIvResumeattach;
    private AppCompatImageView mProfileIvResumdownload;
    private AppCompatImageView mPersonaldetailsIvEdit;
    private AppCompatTextView mPersonaldetailsTvEmailidvalue;
    private AppCompatTextView mPersonaldetailsTvMobilenovalue;
    private AppCompatTextView mPersonaldetailsTvClocationvalue;
    private AppCompatTextView mPersonaldetailsTvDobvalue;
    private AppCompatTextView mPersonaldetailsTvGendervalue;
    private AppCompatImageView mProfessionalsummaryIvEdit;
    private AppCompatTextView mProfessionalsummaryTvProfiletitlevalue;
    private AppCompatTextView mProfessionalsummaryTvAnnualsalaryvalue;
    private AppCompatTextView mProfessionalsummaryTvTotalexpvalue;
    private AppCompatTextView mProfessionalsummaryTvTeamhvalue;
    private AppCompatTextView mProfessionalsummaryTvNoticeperiodvalue;
    private MaterialCardView mProfileCardviewWorkexplayout;
    private TextView mWorkexpIvEdit;
    private RecyclerView mWorkexpRecylerviewWorkexp;
    private PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(root);
        getDataForProfile();
        getDataWorkExperience();
        return root;
    }

    private void getDataForProfile() {
        new GetProfileDetailsByIdService(getActivity(), publicProfileDetailsByIDResponse -> setProfessionalDetailsFromResponse(publicProfileDetailsByIDResponse));

        new GetBasicDetailsService(getActivity(), basicDetailsResponse -> {
            setWorkRelatedData(basicDetailsResponse);
        });
    }

    private void setWorkRelatedData(BasicDetailsReponse basicDetailsResponse) {
        mProfessionalsummaryTvAnnualsalaryvalue.setText(basicDetailsResponse.getCurrentCTC());
        mProfessionalsummaryTvTotalexpvalue.setText(basicDetailsResponse.getTotalExperienceYears() + " Years");
        mProfessionalsummaryTvNoticeperiodvalue.setText(basicDetailsResponse.getNoticePeriod());
    }

    private void setProfessionalDetailsFromResponse(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse) {
        this.publicProfileDetailsByIDResponse = publicProfileDetailsByIDResponse;
        mProfileTvName.setText(publicProfileDetailsByIDResponse.getAccount().getName());
        mProfileTvDesignation.setText(publicProfileDetailsByIDResponse.getWorkExperiences().get(0).getDesignation());

        mPersonaldetailsTvEmailidvalue.setText(publicProfileDetailsByIDResponse.getContactDetails().getEmail());
        mPersonaldetailsTvMobilenovalue.setText(publicProfileDetailsByIDResponse.getContactDetails().getPhone());
        mProfessionalsummaryTvProfiletitlevalue.setText(publicProfileDetailsByIDResponse.getAccount().getCurrentDesignation());
        if (publicProfileDetailsByIDResponse.getAccount().getCurrentLocation() != null)
            mPersonaldetailsTvClocationvalue.setText(publicProfileDetailsByIDResponse.getAccount().getCurrentLocation().getDistrictName());
        if (publicProfileDetailsByIDResponse.getAccount().getAvatar() != null
                && !publicProfileDetailsByIDResponse.getAccount().getAvatar().isEmpty())
            Glide.with(getActivity())
                    .load(publicProfileDetailsByIDResponse.getAccount().getAvatar())
                    .into(mProfileShapeivProfile);
    }

    private void getDataWorkExperience() {
        new GetWorkExperienceService(getActivity(), new GetWorkExperienceService.onResponseGetWorkExperience() {
            @Override
            public void sendDashBoardJobsDataResponse(List<WorkExperienceResponse> workExperienceResponse) {
                WorkExperienceAdapter adapter = new WorkExperienceAdapter(workExperienceResponse, getActivity());
                mWorkexpRecylerviewWorkexp.setHasFixedSize(true);
                mWorkexpRecylerviewWorkexp.setLayoutManager(new LinearLayoutManager(getActivity()));
                mWorkexpRecylerviewWorkexp.setAdapter(adapter);
            }
        });
    }

    private void initView(View root) {
        mGuidelineVertical = root.findViewById(R.id.guidelineVertical);
        mGuidelineVertical2 = root.findViewById(R.id.guidelineVertical2);
        mProfileShapeivProfile = root.findViewById(R.id.profile_shapeiv_profile);
        mProfileTvName = root.findViewById(R.id.profile_textview_name);
        mProfileTvDesignation = root.findViewById(R.id.profile_textview_designation);
        mProfileTvPlaceholderprofiletext = root.findViewById(R.id.profile_textview_placeholderprofiletext);
        mProfileTvProfilecompletionpercentage = root.findViewById(R.id.profile_textview_profilecompletionpercentage);
        mProfileProgressindicatorProfilecompletionpercentage = root.findViewById(R.id.profile_progressindicator_profilecompletionpercentage);
        mProfileTvLastupdated = root.findViewById(R.id.profile_textview_lastupdated);
        mProfileProgressindicatorProfilecompletionpercentage.setProgress(60);
        mProfileCardviewProfilepercentagelayout = root.findViewById(R.id.profile_cardview_profilepercentagelayout);
        mProfileTvResumelastupdated = root.findViewById(R.id.profile_textview_resumelastupdated);
        mProfileIvResumeattach = root.findViewById(R.id.profile_iv_resumeattach);
        mProfileIvResumdownload = root.findViewById(R.id.profile_iv_resumdownload);
        mPersonaldetailsIvEdit = root.findViewById(R.id.personaldetails_iv_edit);
        mPersonaldetailsTvEmailidvalue = root.findViewById(R.id.personaldetails_textview_emailidvalue);
        mPersonaldetailsTvMobilenovalue = root.findViewById(R.id.personaldetails_textview_mobilenovalue);
        mPersonaldetailsTvClocationvalue = root.findViewById(R.id.personaldetails_textview_clocationvalue);
        mPersonaldetailsTvDobvalue = root.findViewById(R.id.personaldetails_textview_dobvalue);
        mPersonaldetailsTvGendervalue = root.findViewById(R.id.personaldetails_textview_gendervalue);
        mProfessionalsummaryIvEdit = root.findViewById(R.id.professionalsummary_iv_edit);
        mProfessionalsummaryTvProfiletitlevalue = root.findViewById(R.id.professionalsummary_textview_profiletitlevalue);
        mProfessionalsummaryTvAnnualsalaryvalue = root.findViewById(R.id.professionalsummary_textview_annualsalaryvalue);
        mProfessionalsummaryTvTotalexpvalue = root.findViewById(R.id.professionalsummary_textview_totalexpvalue);
        mProfessionalsummaryTvTeamhvalue = root.findViewById(R.id.professionalsummary_textview_teamhvalue);
        mProfessionalsummaryTvNoticeperiodvalue = root.findViewById(R.id.professionalsummary_textview_noticeperiodvalue);
        mProfileCardviewWorkexplayout = root.findViewById(R.id.profile_cardview_workexplayout);
        mWorkexpIvEdit = root.findViewById(R.id.workexp_textview_edit);
        mWorkexpRecylerviewWorkexp = root.findViewById(R.id.workexp_recylerview_workexp);
        setClick();
    }

    private void setClick() {
        mPersonaldetailsIvEdit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EditProfileDetailsActivity.class);
            intent.putExtra(AppConstants.PROFILE_HEADER, AppConstants.PERSONAL_DETAILS);
            intent.putExtra(AppConstants.ALLDATA, publicProfileDetailsByIDResponse);
            someActivityResultLauncher.launch(intent);
        });
        mProfessionalsummaryIvEdit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EditProfileDetailsActivity.class);
            intent.putExtra(AppConstants.PROFILE_HEADER, AppConstants.PROFESSIONAL_DETAILS);
            someActivityResultLauncher.launch(intent);
        });

        mWorkexpIvEdit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EditProfileDetailsActivity.class);
            intent.putExtra(AppConstants.PROFILE_HEADER, AppConstants.WORK_EXPERIENCE);
            someActivityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    getDataForProfile();
                    getDataWorkExperience();
                }
            });
}
