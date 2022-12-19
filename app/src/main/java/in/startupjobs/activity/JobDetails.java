package in.startupjobs.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import in.startupjobs.R;
import in.startupjobs.model.applyJob.ApplyJobResponseModel;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.serachedJobs.Result;
import in.startupjobs.model.serachedJobs.SkillName;
import in.startupjobs.services.GetProfileDetailsByIdService;
import in.startupjobs.services.PostApplyJobRequest;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.GlobalVariablesNMethods;

public class JobDetails extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatImageView companyLogo;
    TextView jobTitle, companyTitle, jobPostedTime, numOfApplicants;
    TextView expValue, vacanciesAvailableNum, jobLocation, salaryPackageValue, skillsValue, otherSkillsValue, empTypeValue;
    WebView jobRoleValue;
    MaterialButton saveButton, applyButton;

    Result jobDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        jobDetails = (Result) getIntent().getSerializableExtra(AppConstants.JOB_DETAILS);
        initToolbar();
        initLayoutViews();

    }

    private void initLayoutViews() {

        companyLogo = findViewById(R.id.activity_jd_companylogo);
        jobTitle = findViewById(R.id.activity_jd_jobtitle);
        companyTitle = findViewById(R.id.activity_jd_companytitle);
        jobPostedTime = findViewById(R.id.activity_jd_jobpostedtime);
        numOfApplicants = findViewById(R.id.activity_jd_jobapplicants);

        expValue = findViewById(R.id.tv_jd_exp);
        vacanciesAvailableNum = findViewById(R.id.tv_jd_vacancies);
        jobLocation = findViewById(R.id.tv_jd_location);
        salaryPackageValue = findViewById(R.id.tv_jd_salarypackage);
        skillsValue = findViewById(R.id.tv_jd_skills_value);
        otherSkillsValue = findViewById(R.id.tv_jd_otherskills_value);
        empTypeValue = findViewById(R.id.tv_jd_emptype_value);

        jobRoleValue = findViewById(R.id.webview_jd_job_role_value);

        saveButton = findViewById(R.id.activity_jd_save_button);
        applyButton = findViewById(R.id.activity_jd_apply_button);

        if (jobDetails != null) {
            setDataForJob(jobDetails);
        }

        setClicks();
    }

    private void setClicks() {
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataForApplyJob();
            }
        });
    }

    private void setDataForApplyJob() {
        new GetProfileDetailsByIdService(this, new GetProfileDetailsByIdService.onResponseGetPublicProfileDetailsById() {
            @Override
            public void sendProfileDetailsByIdResponse(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse) {
                ApplyJobResponseModel.ApplyJobDataToSend jobDataToSend = new ApplyJobResponseModel.ApplyJobDataToSend();
                jobDataToSend.setEmail(AppConstants.mLoginData.getEmail());
                jobDataToSend.setName(AppConstants.mLoginData.getFullName());
                jobDataToSend.setMobileNumber(Long.valueOf(publicProfileDetailsByIDResponse.getContactDetails().getPhone()));
                jobDataToSend.setCurrentDesignation(publicProfileDetailsByIDResponse.getWorkExperiences().get(0).getDesignation());
                if (publicProfileDetailsByIDResponse.getResumes() != null
                        && publicProfileDetailsByIDResponse.getResumes().size() > 0)
                    jobDataToSend.setResumeFile(publicProfileDetailsByIDResponse.getResumes().get(0));
                sendApplyJobRequest(jobDataToSend);
            }
        });

    }

    private void sendApplyJobRequest(ApplyJobResponseModel.ApplyJobDataToSend jobDataToSend) {
        new PostApplyJobRequest(this, String.valueOf(jobDetails.getId()), jobDataToSend, new PostApplyJobRequest.onApplyJobResponse() {
            @Override
            public void sendApplyJobResponse(ApplyJobResponseModel applyJobResponseModel) {

            }
        });
    }

    private void setDataForJob(Result jobDetails) {
        jobTitle.setText(jobDetails.getJobTitle());
        companyTitle.setText(jobDetails.getCompany().getName());
        setTextForJobDescription(jobRoleValue);
        if (jobDetails.getLocationNames() != null
                && jobDetails.getLocationNames().size() > 0)
            if (jobDetails.getLocationNames().get(0) != null)
                jobLocation.setText(jobDetails.getLocationNames().get(0).getDistrictName());
        setCtc(jobDetails, salaryPackageValue);
        setExperience(jobDetails, expValue);
        setSkills(jobDetails, skillsValue);
        if (jobDetails.getCompany().getLogo() != null
                && !jobDetails.getCompany().getLogo().isEmpty())
            Glide.with(this)
                    .load(jobDetails.getCompany().getLogo())
                    .into(companyLogo);

        vacanciesAvailableNum.setText(jobDetails.getPositionOpen() + " Positions");
        empTypeValue.setText(jobDetails.getJobType().getName() + ", " + jobDetails.getWorkType());
        jobPostedTime.setText(GlobalVariablesNMethods.convertDate(this, jobDetails.getCreatedOn(), null, "dd MMM yyyy"));
    }

    private void setTextForJobDescription(WebView jobRoleValue) {
        String res = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            res = Html.fromHtml(jobDetails.getJobDescription(), Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            res = Html.fromHtml(jobDetails.getJobDescription()).toString();
        }

        jobRoleValue.loadDataWithBaseURL(null, res, "text/html", "utf-8", null);
    }

    private void setCtc(Result jobDetails, TextView mRowJobsTextviewSalary) {
        StringBuilder mCtc = new StringBuilder();
        mCtc.append(jobDetails.getSalaryMin()).append(" - ").append(jobDetails.getSalaryMax());
        mRowJobsTextviewSalary.setText(mCtc);
    }

    private void setExperience(Result jobDetails, TextView mRowJobsTextviewExpneeded) {
        StringBuilder expNeeded = new StringBuilder();
        expNeeded.append(jobDetails.getExperienceMin()).append(" - ");
        expNeeded.append(jobDetails.getExperienceMax()).append(" Years");
        mRowJobsTextviewExpneeded.setText(expNeeded);
    }

    private void setSkills(Result jobDetails, TextView mRowJobsTextviewSkills) {
        if (jobDetails.getSkillNames() != null
                && jobDetails.getSkillNames().size() > 0) {
            StringBuilder skills = new StringBuilder();
            for (int i = 0; i < jobDetails.getSkillNames().size(); i++) {
                SkillName skillName = jobDetails.getSkillNames().get(i);
                if (i < jobDetails.getSkillNames().size() - 1)
                    skills.append(skillName.getName()).append(",");
                else skills.append(skillName.getName());

            }
            mRowJobsTextviewSkills.setText(skills);
        }
    }

    public void initToolbar() {
        toolbar = findViewById(R.id.activity_jd_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_jd_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_jd_menu_star:
                //add the function to perform here
                Toast.makeText(this, "fav clicked", Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.activity_jd_menu_share:
                //add the function to perform here
                Toast.makeText(this, "share clicked", Toast.LENGTH_SHORT).show();
                return (true);
        }
        return (super.onOptionsItemSelected(item));
    }
}