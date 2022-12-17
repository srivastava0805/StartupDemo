package in.startupjobs.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import in.startupjobs.adapter.WorkExperienceAdapter;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import in.startupjobs.services.GetProfileDetailsByIdService;
import in.startupjobs.services.GetWorkExperienceService;

public class ProfileFragment extends Fragment {

    private Guideline mGuidelineVertical;
    private Guideline mGuidelineVertical2;
    private ShapeableImageView mProfileShapeivProfile;
    private TextView mProfileTextviewName;
    private TextView mProfileTextviewDesignation;
    private AppCompatTextView mProfileTextviewPlaceholderprofiletext;
    private AppCompatTextView mProfileTextviewProfilecompletionpercentage;
    private LinearProgressIndicator mProfileProgressindicatorProfilecompletionpercentage;
    private AppCompatTextView mProfileTextviewLastupdated;
    private MaterialCardView mProfileCardviewProfilepercentagelayout;
    private AppCompatTextView mProfileTextviewResumelastupdated;
    private AppCompatImageView mProfileIvResumeattach;
    private AppCompatImageView mProfileIvResumdownload;
    private AppCompatImageView mPersonaldetailsIvEdit;
    private AppCompatTextView mPersonaldetailsTextviewEmailidvalue;
    private AppCompatTextView mPersonaldetailsTextviewMobilenovalue;
    private AppCompatTextView mPersonaldetailsTextviewClocationvalue;
    private AppCompatTextView mPersonaldetailsTextviewDobvalue;
    private AppCompatTextView mPersonaldetailsTextviewGendervalue;
    private AppCompatImageView mProfessionalsummaryIvEdit;
    private AppCompatTextView mProfessionalsummaryTextviewProfiletitlevalue;
    private AppCompatTextView mProfessionalsummaryTextviewAnnualsalaryvalue;
    private AppCompatTextView mProfessionalsummaryTextviewTotalexpvalue;
    private AppCompatTextView mProfessionalsummaryTextviewTeamhvalue;
    private AppCompatTextView mProfessionalsummaryTextviewNoticeperiodvalue;
    private MaterialCardView mProfileCardviewWorkexplayout;
    private TextView mWorkexpIvEdit;
    private RecyclerView mWorkexpRecylerviewWorkexp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(root);
        getDataForPublicProfileById();
        getDataWorkExperience();
        return root;
    }

    private void getDataForPublicProfileById() {
        new GetProfileDetailsByIdService(getActivity(), new GetProfileDetailsByIdService.onResponseGetPublicProfileDetailsById() {
            @Override
            public void sendProfileDetailsByIdResponse(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse) {
                setProfessionalDetailsFromResponse(publicProfileDetailsByIDResponse);
            }
        });
    }

    private void setProfessionalDetailsFromResponse(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse) {
        mProfileTextviewName.setText(publicProfileDetailsByIDResponse.getAccount().getName());
        mProfileTextviewDesignation.setText(publicProfileDetailsByIDResponse.getWorkExperiences().get(0).getDesignation());

        mPersonaldetailsTextviewEmailidvalue.setText(publicProfileDetailsByIDResponse.getContactDetails().getEmail());
        mPersonaldetailsTextviewMobilenovalue.setText(publicProfileDetailsByIDResponse.getContactDetails().getPhone());
        mProfessionalsummaryTextviewProfiletitlevalue.setText(publicProfileDetailsByIDResponse.getAccount().getCurrentDesignation());
        mPersonaldetailsTextviewClocationvalue.setText(publicProfileDetailsByIDResponse.getAccount().getCurrentLocation().getDistrictName());
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
        mProfileTextviewName = root.findViewById(R.id.profile_textview_name);
        mProfileTextviewDesignation = root.findViewById(R.id.profile_textview_designation);
        mProfileTextviewPlaceholderprofiletext = root.findViewById(R.id.profile_textview_placeholderprofiletext);
        mProfileTextviewProfilecompletionpercentage = root.findViewById(R.id.profile_textview_profilecompletionpercentage);
        mProfileProgressindicatorProfilecompletionpercentage = root.findViewById(R.id.profile_progressindicator_profilecompletionpercentage);
        mProfileTextviewLastupdated = root.findViewById(R.id.profile_textview_lastupdated);
        mProfileProgressindicatorProfilecompletionpercentage.setProgress(60);
        mProfileCardviewProfilepercentagelayout = root.findViewById(R.id.profile_cardview_profilepercentagelayout);
        mProfileTextviewResumelastupdated = root.findViewById(R.id.profile_textview_resumelastupdated);
        mProfileIvResumeattach = root.findViewById(R.id.profile_iv_resumeattach);
        mProfileIvResumdownload = root.findViewById(R.id.profile_iv_resumdownload);
        mPersonaldetailsIvEdit = root.findViewById(R.id.personaldetails_iv_edit);
        mPersonaldetailsTextviewEmailidvalue = root.findViewById(R.id.personaldetails_textview_emailidvalue);
        mPersonaldetailsTextviewMobilenovalue = root.findViewById(R.id.personaldetails_textview_mobilenovalue);
        mPersonaldetailsTextviewClocationvalue = root.findViewById(R.id.personaldetails_textview_clocationvalue);
        mPersonaldetailsTextviewDobvalue = root.findViewById(R.id.personaldetails_textview_dobvalue);
        mPersonaldetailsTextviewGendervalue = root.findViewById(R.id.personaldetails_textview_gendervalue);
        mProfessionalsummaryIvEdit = root.findViewById(R.id.professionalsummary_iv_edit);
        mProfessionalsummaryTextviewProfiletitlevalue = root.findViewById(R.id.professionalsummary_textview_profiletitlevalue);
        mProfessionalsummaryTextviewAnnualsalaryvalue = root.findViewById(R.id.professionalsummary_textview_annualsalaryvalue);
        mProfessionalsummaryTextviewTotalexpvalue = root.findViewById(R.id.professionalsummary_textview_totalexpvalue);
        mProfessionalsummaryTextviewTeamhvalue = root.findViewById(R.id.professionalsummary_textview_teamhvalue);
        mProfessionalsummaryTextviewNoticeperiodvalue = root.findViewById(R.id.professionalsummary_textview_noticeperiodvalue);
        mProfileCardviewWorkexplayout = root.findViewById(R.id.profile_cardview_workexplayout);
        mWorkexpIvEdit = root.findViewById(R.id.workexp_textview_edit);
        mWorkexpRecylerviewWorkexp = root.findViewById(R.id.workexp_recylerview_workexp);
    }
}
