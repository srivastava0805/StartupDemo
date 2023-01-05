package in.startupjobs.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.startupjobs.R;
import in.startupjobs.activity.EditProfileDetailsActivity;
import in.startupjobs.model.workExperience.WorkExperienceResponse;
import in.startupjobs.services.DeleteWorkExpRequest;
import in.startupjobs.utils.AppConstants;

public class WorkExperienceAdapter extends RecyclerView.Adapter<WorkExperienceAdapter.WorkExperienceViewHolder> {

    List<WorkExperienceResponse> list = new ArrayList<>();

    Activity context;

    Fragment resultContext;


    public WorkExperienceAdapter(List<WorkExperienceResponse> list, Activity context, Fragment resultContext) {
        this.list = list;
        this.context = context;
        this.resultContext = resultContext;
    }

    @Override
    public WorkExperienceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.row_work_experience_layout, parent, false);

        WorkExperienceViewHolder viewHolder = new WorkExperienceViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final WorkExperienceViewHolder viewHolder, final int position) {
        WorkExperienceResponse data = list.get(position);
        viewHolder.mRowworkexpTextviewDesignation.setText(data.getDesignation());
        viewHolder.mRowworkexpTextviewCompanyname.setText(data.getCompanyName());
        viewHolder.mRowworkexpTextviewIndustrytype.setText(data.getRoleDescription());
        if (data.getCurrentlyWorking()) {
            viewHolder.mRowworkexpTextviewWorkedtimeduration.setText("Working here");
        } else if (data.getEndDate() != null) {

            viewHolder.mRowworkexpTextviewWorkedtimeduration.setText("From " + data.getStartDate() + " to " + data.getEndDate());
        } else
            viewHolder.mRowworkexpTextviewWorkedtimeduration.setText("From " + data.getStartDate());
        viewHolder.mRowworkexpIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteConfirmationDialog(data, position, data.getId());
            }
        });

        viewHolder.mRowworkexpIvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditProfileDetailsActivity.class);
                intent.putExtra(AppConstants.PROFILE_HEADER, AppConstants.WORK_EXPERIENCE);
                intent.putExtra(AppConstants.ALLDATA, list.get(position));
                resultContext.startActivityForResult(intent, 000);
            }
        });

        if (data.getCurrentCtcLakhs() != null)
            viewHolder.mRowworkexpTextviewFunctinalarea.setText(data.getCurrentCtcLakhs() + " Lakhs ");
        if (data.getCurrentCtcThousands() != null)
            viewHolder.mRowworkexpTextviewFunctinalarea.setText(viewHolder.mRowworkexpTextviewFunctinalarea.getText() + "and " + data.getCurrentCtcThousands() + " Thousands");
    }

    private void showDeleteConfirmationDialog(WorkExperienceResponse workExperienceResponse, Integer position, Integer id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.delete))
                .setMessage(context.getString(R.string.are_you_sure))
                .setPositiveButton(context.getString(R.string.yes), (dialog, which) -> {
                    List<WorkExperienceResponse> tempList = new ArrayList<>();
                    tempList.addAll(list);
                    list.remove(workExperienceResponse);
                    notifyItemRemoved(position);
                    new DeleteWorkExpRequest(context, id, isDeleted -> {
                        if (!isDeleted) {
                            list.add(position, tempList.get(position));
                            notifyItemInserted(position);
                        }
                    });
                })
                .setNegativeButton(context.getString(R.string.no), (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                });
        alertDialog.create().show();
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
