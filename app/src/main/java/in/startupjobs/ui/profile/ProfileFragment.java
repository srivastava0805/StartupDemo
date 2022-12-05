package in.startupjobs.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.List;

import in.startupjobs.R;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;
import in.startupjobs.services.GetAppliedJobs;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        new GetAppliedJobs(getActivity(), new GetAppliedJobs.onResponseAppliedJobs() {
            @Override
            public void sendAppliedJobsResponse(List<AppliedJobsResponse> appliedJobsResponseList) {
                Toast.makeText(getActivity(), "Got Apple", Toast.LENGTH_SHORT).show();
            }
        });
        initView(root);
        return root;
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
    }
}
