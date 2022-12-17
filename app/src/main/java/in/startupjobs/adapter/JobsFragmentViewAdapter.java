package in.startupjobs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.startupjobs.R;
import in.startupjobs.model.appliedJobsListing.AppliedJobsResponse;

public class JobsFragmentViewAdapter extends RecyclerView.Adapter<JobsFragmentViewAdapter.MyViewHolder> {
    private Context context;
    private List<AppliedJobsResponse> dataModel;
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

        public MyViewHolder(View view) {
            super(view);
            mRowJobsTextviewJobrole = view.findViewById(R.id.rowJobs_textview_jobrole);
            mRowJobsTextviewCompanyname = view.findViewById(R.id.rowJobs_textview_companyname);
            mRowJobsTextviewLoaction = view.findViewById(R.id.rowJobs_textview_loaction);
            mRowJobsTextviewExpneeded = view.findViewById(R.id.rowJobs_textview_expneeded);
            mRowJobsTextviewSalary = view.findViewById(R.id.rowJobs_textview_salary);
            mRowJobsTextviewSkills = view.findViewById(R.id.rowJobs_textview_skills);
            mRowJobsIvSave = view.findViewById(R.id.rowJobs_iv_save);
            mRowJobsBtnApply = view.findViewById(R.id.rowJobs_btn_apply);
            this.itemView = view;
        }
    }

    public JobsFragmentViewAdapter(Context _context, List<AppliedJobsResponse> data) {
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
        try {
            holder.mRowJobsTextviewJobrole.setText(dataModel.get(position).getJobTitle());
            holder.mRowJobsTextviewCompanyname.setText(dataModel.get(position).getCompanyName());
            if (dataModel.get(position).getApplicationStatuses().get(0).getInterviewLocation() != null)
                holder.mRowJobsTextviewLoaction.setText(dataModel.get(position).getApplicationStatuses().get(0).getInterviewLocation().toString());
            holder.mRowJobsIvSave.setVisibility(View.GONE);
            holder.mRowJobsTextviewLoaction.setVisibility(View.GONE);
            holder.mRowJobsTextviewExpneeded.setVisibility(View.GONE);
            holder.mRowJobsTextviewSalary.setVisibility(View.GONE);
            holder.mRowJobsBtnApply.setText(R.string.view);
            holder.mRowJobsBtnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Nothing more to show", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataModel.size();
    }
}