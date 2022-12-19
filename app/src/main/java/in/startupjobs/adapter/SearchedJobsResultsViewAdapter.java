package in.startupjobs.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import in.startupjobs.R;
import in.startupjobs.activity.JobDetails;
import in.startupjobs.model.serachedJobs.Result;
import in.startupjobs.model.serachedJobs.SearchedJobsResponse;
import in.startupjobs.model.serachedJobs.SkillName;
import in.startupjobs.utils.AppConstants;

public class SearchedJobsResultsViewAdapter extends RecyclerView.Adapter<SearchedJobsResultsViewAdapter.MyViewHolder> {
    private Context context;
    private SearchedJobsResponse dataModel;
    private int width, height;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mRowJobsTextviewJobrole;
        private TextView mRowJobsTextviewCompanyname;
        private TextView mRowJobsTextviewLoaction;
        private TextView mRowJobsTextviewExpneeded;
        private TextView mRowJobsTextviewSalary;
        private TextView mRowJobsTextviewSkills;
        private ImageView mRowJobsIvSave;
        private AppCompatButton mRowJobsBtnApply;
        View itemView;
        private ShapeableImageView mRowjobsShapeivCompanylogo;

        public MyViewHolder(View view) {
            super(view);
            mRowJobsTextviewJobrole = view.findViewById(R.id.rowJobs_textview_jobrole);
            mRowJobsTextviewCompanyname = view.findViewById(R.id.rowJobs_textview_companyname);
            mRowJobsTextviewLoaction = view.findViewById(R.id.rowJobs_textview_loaction);
            mRowJobsTextviewExpneeded = view.findViewById(R.id.rowJobs_textview_expneeded);
            mRowJobsTextviewSalary = view.findViewById(R.id.rowJobs_textview_salary);
            mRowJobsTextviewSkills = view.findViewById(R.id.rowJobs_textview_skills);
            mRowJobsBtnApply = view.findViewById(R.id.rowJobs_btn_apply);
            mRowjobsShapeivCompanylogo = view.findViewById(R.id.rowjobs_shapeiv_companylogo);
            this.itemView = view;
        }
    }

    public SearchedJobsResultsViewAdapter(Context _context, SearchedJobsResponse data) {
        this.context = _context;
        this.dataModel = data;
        this.width = 160;
        this.height = 100;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_jobs, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Result jobDetails = dataModel.getResults().get(position);
        try {
            holder.mRowJobsTextviewJobrole.setText(jobDetails.getJobTitle());
            holder.mRowJobsTextviewCompanyname.setText(jobDetails.getCompany().getName());
            if (jobDetails.getLocationNames() != null
                    && jobDetails.getLocationNames().size() > 0)
                if (jobDetails.getLocationNames().get(0) != null)
                    holder.mRowJobsTextviewLoaction.setText(jobDetails.getLocationNames().get(0).getDistrictName());
            setCtc(jobDetails, holder.mRowJobsTextviewSalary);
            setExperience(jobDetails, holder.mRowJobsTextviewExpneeded);
            setSkills(jobDetails, holder.mRowJobsTextviewSkills);
            if (jobDetails.getCompany().getLogo() != null
                    && !jobDetails.getCompany().getLogo().isEmpty())
                Glide.with(context)
                        .load(jobDetails.getCompany().getLogo())
                        .into(holder.mRowjobsShapeivCompanylogo);
            holder.mRowJobsBtnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, JobDetails.class);
                    intent.putExtra(AppConstants.JOB_DETAILS, jobDetails);
                    context.startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCtc(Result jobDetails, TextView mRowJobsTextviewSalary) {
        StringBuilder mCtc = new StringBuilder();
        mCtc.append("\u25AA").append("   CTC: ").append(jobDetails.getSalaryMin()).append(" - ").append(jobDetails.getSalaryMax());
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
            skills.append("\u25AA");
            skills.append("   ");
            for (int i = 0; i < jobDetails.getSkillNames().size(); i++) {
                SkillName skillName = jobDetails.getSkillNames().get(i);
                if (i < jobDetails.getSkillNames().size() - 1)
                    skills.append(skillName.getName()).append(",");
                else skills.append(skillName.getName());

            }
            mRowJobsTextviewSkills.setText(skills);
        }
    }

    @Override
    public int getItemCount() {
        return dataModel.getResults().size();
    }
}