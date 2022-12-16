package in.startupjobs.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import in.startupjobs.R;
import in.startupjobs.model.companies.GetCompaniesResponse;
import in.startupjobs.model.companies.Result;

public class SearchedCompaniesResultsViewAdapter extends RecyclerView.Adapter<SearchedCompaniesResultsViewAdapter.MyViewHolder> {
    private Context context;
    private GetCompaniesResponse dataModel;
    private int width, height;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mRowCompanyName;
        private TextView mRowCompanyTotalEmployees;
        private TextView mRowJobsTextviewLoaction;
        private TextView mRowCompanyTextViewTotalJobs;
        private WebView mRowCompanyWebViewAbout;
        private TextView mRowJobsTextviewSkills;
        private ImageView mRowJobsIvSave;
        private AppCompatButton mRowJobsBtnApply;
        View itemView;
        private ShapeableImageView mRowjobsShapeivCompanylogo;

        public MyViewHolder(View view) {
            super(view);
            mRowCompanyName = view.findViewById(R.id.rowcompanies_textview_name);
            mRowCompanyTotalEmployees = view.findViewById(R.id.rowcompanies_textview_employeecount);
            mRowJobsTextviewLoaction = view.findViewById(R.id.rowcompanies_textview_loaction);
            mRowCompanyTextViewTotalJobs = view.findViewById(R.id.rowcompanies_textview_totaljobs);
            mRowCompanyWebViewAbout = view.findViewById(R.id.rowcompanies_webview_aboutcompany);
            mRowjobsShapeivCompanylogo = view.findViewById(R.id.rowcompanies_shapeiv_companylogo);
            this.itemView = view;
        }
    }

    public SearchedCompaniesResultsViewAdapter(Context _context, GetCompaniesResponse data) {
        this.context = _context;
        this.dataModel = data;
        this.width = 160;
        this.height = 100;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_companies, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Result jobDetails = dataModel.getResults().get(position);
        try {
            holder.mRowCompanyName.setText(jobDetails.getName());
            if (jobDetails.getEmployeeStrength() != null && !jobDetails.getEmployeeStrength().isEmpty())
                holder.mRowCompanyTotalEmployees.setText("No. Of Employees " + jobDetails.getEmployeeStrength());
            else holder.mRowCompanyTotalEmployees.setText("No. Of Employees N/A");

            if (jobDetails.getTotalJobs() > 1)
                holder.mRowCompanyTextViewTotalJobs.setText(jobDetails.getTotalJobs() + " Jobs");
            else
                holder.mRowCompanyTextViewTotalJobs.setText(jobDetails.getTotalJobs() + " Job ");
            setTextForJobDescription(holder.mRowCompanyWebViewAbout, jobDetails.getDescription());

            if (jobDetails.getLocationNames() != null
                    && jobDetails.getLocationNames().size() > 0)
                if (jobDetails.getLocationNames().get(0) != null)
                    holder.mRowJobsTextviewLoaction.setText(jobDetails.getLocationNames().get(0).getDistrictName());
//            setCtc(jobDetails, holder.mRowJobsTextviewSalary);
//            setExperience(jobDetails, holder.mRowJobsTextviewExpneeded);
//            setSkills(jobDetails, holder.mRowJobsTextviewSkills);
            if (jobDetails.getLogo() != null
                    && !jobDetails.getLogo().isEmpty())
                Glide.with(context)
                        .load(jobDetails.getLogo())
                        .into(holder.mRowjobsShapeivCompanylogo);
            holder.mRowJobsBtnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, JobDetails.class);
//                    intent.putExtra(AppConstants.JOB_DETAILS, jobDetails);
//                    context.startActivity(intent);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setTextForJobDescription(WebView textview, String description) {
        String res = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            res = Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT).toString();
        } else {
            res = Html.fromHtml(description).toString();
        }

        textview.loadDataWithBaseURL(null, res, "text/html", "utf-8", null);
    }
//    private void setCtc(Result jobDetails, TextView mRowJobsTextviewSalary) {
//        StringBuilder mCtc = new StringBuilder();
//        mCtc.append("\u25AA").append("   CTC: ").append(jobDetails.getSalaryMin()).append(" - ").append(jobDetails.getSalaryMax());
//        mRowJobsTextviewSalary.setText(mCtc);
//    }
//
//    private void setExperience(Result jobDetails, TextView mRowJobsTextviewExpneeded) {
//        StringBuilder expNeeded = new StringBuilder();
//        expNeeded.append(jobDetails.getExperienceMin()).append(" - ");
//        expNeeded.append(jobDetails.getExperienceMax()).append(" Years");
//        mRowJobsTextviewExpneeded.setText(expNeeded);
//    }
//
//    private void setSkills(Result jobDetails, TextView mRowJobsTextviewSkills) {
//        StringBuilder skills = new StringBuilder();
//        skills.append("\u25AA");
//        skills.append("   ");
//        for (int i = 0; i < jobDetails.getSkillNames().size(); i++) {
//            SkillName skillName = jobDetails.getSkillNames().get(i);
//            if (i < jobDetails.getSkillNames().size() - 1)
//                skills.append(skillName.getName()).append(",");
//            else skills.append(skillName.getName());
//
//        }
//        mRowJobsTextviewSkills.setText(skills);
//    }

    @Override
    public int getItemCount() {
        return dataModel.getResults().size();
    }
}