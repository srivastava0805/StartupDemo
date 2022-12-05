package in.startupjobs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import in.startupjobs.R;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import in.startupjobs.utils.GlobalVariablesNMethods;

public class WorkExperienceAdapter extends RecyclerView.Adapter<WorkExperienceAdapter.WorkExperienceViewHolder> {

    List<WorkExperienceResponse> list = Collections.emptyList();

    Context context;


    public WorkExperienceAdapter(List<WorkExperienceResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public WorkExperienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the layout

        View photoView = inflater.inflate(R.layout.row_work_experience_layout, parent, false);

        WorkExperienceViewHolder viewHolder = new WorkExperienceViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final WorkExperienceViewHolder viewHolder, final int position) {
        int index = viewHolder.getAdapterPosition();
        viewHolder.mRowworkexpTextviewDesignation.setText(list.get(position).getDesignation());
        viewHolder.mRowworkexpTextviewCompanyname.setText(list.get(position).getCompanyName());
        viewHolder.mRowworkexpTextviewIndustrytype.setText(list.get(position).getRoleDescription());
        viewHolder.mRowworkexpTextviewCompanyname.setText(list.get(position).getCompanyName());
        if (list.get(position).getCurrentlyWorking()) {
            String date = GlobalVariablesNMethods.convertDate(context, list.get(position).getStartDate(), "yyyy-MM", "MMM yyyy");
            viewHolder.mRowworkexpTextviewWorkedtimeduration.setText("Working here");
        } else if (list.get(position).getEndDate() != null) {

            viewHolder.mRowworkexpTextviewWorkedtimeduration.setText("From " + list.get(position).getStartDate() + " to " + list.get(position).getEndDate());
        } else
            viewHolder.mRowworkexpTextviewWorkedtimeduration.setText("From " + list.get(position).getStartDate());
//        viewHolder.view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listiner.click(index);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class WorkExperienceViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView mRowworkexpTextviewDesignation;
        private AppCompatImageView mRowworkexpIvEdit;
        private AppCompatImageView mRowworkexpIvDelete;
        private AppCompatTextView mRowworkexpTextviewCompanyname;
        private AppCompatTextView mRowworkexpTextviewWorkedtimeduration;
        private AppCompatTextView mRowworkexpTextviewIndustrytype;
        private AppCompatTextView mRowworkexpTextviewFunctinalarea;

        public WorkExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            mRowworkexpTextviewDesignation = itemView.findViewById(R.id.rowworkexp_textview_designation);
            mRowworkexpIvEdit = itemView.findViewById(R.id.rowworkexp_iv_edit);
            mRowworkexpIvDelete = itemView.findViewById(R.id.rowworkexp_iv_delete);
            mRowworkexpTextviewCompanyname = itemView.findViewById(R.id.rowworkexp_textview_companyname);
            mRowworkexpTextviewWorkedtimeduration = itemView.findViewById(R.id.rowworkexp_textview_workedtimeduration);
            mRowworkexpTextviewIndustrytype = itemView.findViewById(R.id.rowworkexp_textview_industrytype);
            mRowworkexpTextviewFunctinalarea = itemView.findViewById(R.id.rowworkexp_textview_functinalarea);
        }
    }
}
