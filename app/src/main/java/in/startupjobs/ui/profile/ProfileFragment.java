package in.startupjobs.ui.profile;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import in.startupjobs.R;
import in.startupjobs.activity.EditProfileDetailsActivity;
import in.startupjobs.adapter.WorkExperienceAdapter;
import in.startupjobs.model.RegistrationResponseModel;
import in.startupjobs.model.basicDetails.BasicDetailsReponse;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import in.startupjobs.services.GetBasicDetailsService;
import in.startupjobs.services.GetProfileDetailsByIdService;
import in.startupjobs.services.GetWorkExperienceService;
import in.startupjobs.services.PutUploadResumeRequest;
import in.startupjobs.utils.AlertDialogFragment;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.GetFilePathUtil;
import in.startupjobs.utils.GlobalVariablesNMethods;

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
    private AppCompatTextView mProfessionalsummaryDescription;
    private AppCompatTextView mProfessionalsummaryTvNoticeperiodvalue;
    private MaterialCardView mProfileCardviewWorkexplayout;
    private TextView mWorkexpIvEdit;
    private RecyclerView mWorkexpRecylerviewWorkexp;
    private PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse;
    private BasicDetailsReponse basicDetailsResponse;
    private File fileResumeForAndroid11;

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
        new GetProfileDetailsByIdService(getActivity(), publicProfileDetailsByIDResponse ->
                setProfessionalDetailsFromResponse(publicProfileDetailsByIDResponse));

        new GetBasicDetailsService(getActivity(), basicDetailsResponse -> {
            setWorkRelatedData(basicDetailsResponse);
        });
    }

    private void setWorkRelatedData(BasicDetailsReponse basicDetailsResponse) {
        this.basicDetailsResponse = basicDetailsResponse;
        mProfessionalsummaryTvAnnualsalaryvalue.setText(basicDetailsResponse.getCurrentCTC());
        mProfessionalsummaryTvTotalexpvalue.setText(basicDetailsResponse.getTotalExperienceYears() + " Years");
        mProfessionalsummaryTvNoticeperiodvalue.setText(basicDetailsResponse.getNoticePeriod());
        mProfessionalsummaryDescription.setText(basicDetailsResponse.getProfessionalSummary());
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

        if (publicProfileDetailsByIDResponse.getResumes() != null && publicProfileDetailsByIDResponse.getResumes().size() > 0)
            setLatUpdatedResumeName(publicProfileDetailsByIDResponse.getResumes().get(0));
    }

    private void setLatUpdatedResumeName(String nameResumeLastUpdated) {
        String[] nameArrayResume = nameResumeLastUpdated.split("/");
        mProfileTvResumelastupdated.setText(nameArrayResume[nameArrayResume.length - 1]);
    }

    private void getDataWorkExperience() {
        new GetWorkExperienceService(getActivity(), new GetWorkExperienceService.onResponseGetWorkExperience() {
            @Override
            public void sendDashBoardJobsDataResponse(List<WorkExperienceResponse> workExperienceResponse) {
                WorkExperienceAdapter adapter = new WorkExperienceAdapter(workExperienceResponse, getActivity(), ProfileFragment.this);
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
        mProfessionalsummaryDescription = root.findViewById(R.id.professionalsummary_textview_professionaldescription);
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
            intent.putExtra(AppConstants.ALLDATA, basicDetailsResponse);
            someActivityResultLauncher.launch(intent);
        });

        mWorkexpIvEdit.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EditProfileDetailsActivity.class);
            intent.putExtra(AppConstants.PROFILE_HEADER, AppConstants.WORK_EXPERIENCE);
            someActivityResultLauncher.launch(intent);
        });

        mProfileIvResumeattach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!GlobalVariablesNMethods.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, getActivity()))
                        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 001);
                    else selectFileChooser();
                } else selectFileChooser();
            }
        });
//        mProfileIvResumdownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
//                Uri uri = Uri.parse("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf");
//                DownloadManager.Request request = new DownloadManager.Request(uri);
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
//                long reference = manager.enqueue(request);
//            }
//        });

    }

    private void selectFileChooser() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        Intent intent = Intent.createChooser(chooseFile, "Choose a file");
        pickFileActionResult.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 001) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                selectFileChooser();
                // if user checked Don't ask again on permission dialog, this block will be called.
                if (!shouldShowRequestPermissionRationale(permissions[0])) {
                    showErrorDialog();

                } else {
                    Toast.makeText(getActivity(), "Permission Denied, Permission is needed to proceed.", Toast.LENGTH_SHORT).show();
                }

            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                showErrorDialog();
            }
        }
    }

    private void showErrorDialog() {
        DialogFragment fragment = AlertDialogFragment.newInstance(R.string.Perm_Needed, getString(R.string.Goto_settings_page), false
                , (dialog, which) -> {
                    // Take user to app settings page
                    dialog.dismiss();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                }, null);
        fragment.show(getChildFragmentManager(), "error");
    }

    private Uri realUri;
    ActivityResultLauncher<Intent> pickFileActionResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    assert result.getData() != null;
                    if (result.getData().getData() != null) {
                        if (checkIfPdfOrWord(result.getData().getData()))
                            try {
                                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
                                    Uri myUri = result.getData().getData();
                                    openPath(myUri);
                                } else {
                                    assert result.getData().getData() != null;
                                    realUri = Uri.parse(GetFilePathUtil.getPath(getActivity(), result.getData().getData()));
                                }

                                if (GetFilePathUtil.getFileName() != null && !GetFilePathUtil.getFileName().isEmpty()) {
                                    mProfileTvResumelastupdated.setText(GetFilePathUtil.getFileName());
                                    uploadResumeRequest();
                                }
                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                    } else
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            });

    private boolean checkIfPdfOrWord(Uri data) {
        String filenameArray[] = data.toString().split("\\.");
        String extension = filenameArray[filenameArray.length-1];
        if(extension.equalsIgnoreCase("pdf") || extension.equalsIgnoreCase("doc") || extension.equalsIgnoreCase("docx"))
            return true;
        else {
            Toast.makeText(getActivity(), getActivity().getString(R.string.upload_a_doc_or_pdf_file), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    getDataForProfile();
                    getDataWorkExperience();
                }
            });

    public void openPath(Uri uri) {
        InputStream is = null;
        try {
            is = getActivity().getContentResolver().openInputStream(uri);
            try {
                fileResumeForAndroid11 = new File(getActivity().getCacheDir(), "cacheFileAppeal.srl");
                try (OutputStream output = new FileOutputStream(fileResumeForAndroid11)) {
                    byte[] buffer = new byte[4 * 1024]; // or other buffer size
                    int read;

                    while ((read = is.read(buffer)) != -1) {
                        output.write(buffer, 0, read);
                    }

                    output.flush();
                }
            } finally {
                is.close();
            }
            is.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void uploadResumeRequest() {
        File resumeFIle;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q)
            resumeFIle = fileResumeForAndroid11;

        else
            resumeFIle = new File(String.valueOf(realUri));
        new PutUploadResumeRequest(getActivity(), resumeFIle, new PutUploadResumeRequest.onResponseResumeUpload() {
            @Override
            public void sendUploadResumeStatusResponse(RegistrationResponseModel otpResponseModel) {

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 000 && resultCode == Activity.RESULT_OK) {
            getDataWorkExperience();
        }
    }
}
