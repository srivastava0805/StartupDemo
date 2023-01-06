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

public class TopCompaniesDashboardAdapter extends RecyclerView.Adapter<TopCompaniesDashboardAdapter.MyViewHolder> {
    private final boolean hideViewsForDashboard;
    private Context context;
    private GetCompaniesResponse dataModel;
    private int width, height;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mRowCompanyName;
        private TextView mRowJobsTextviewLoaction;
        View itemView;
        private ShapeableImageView mRowjobsShapeivCompanylogo;

        public MyViewHolder(View view) {
            super(view);
            mRowCompanyName = view.findViewById(R.id.rowcompanies_textview_name);
            mRowJobsTextviewLoaction = view.findViewById(R.id.rowcompanies_textview_loaction);
            mRowjobsShapeivCompanylogo = view.findViewById(R.id.rowcompanies_shapeiv_companylogo);
            this.itemView = view;
        }
    }

    public TopCompaniesDashboardAdapter(Context _context, GetCompaniesResponse data, boolean hideViewsForDashboard) {
        this.context = _context;
        this.dataModel = data;
        this.width = 160;
        this.height = 100;
        this.hideViewsForDashboard= hideViewsForDashboard;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_top_companies_dashboard, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Result jobDetails = dataModel.getResults().get(position);
        try {
            holder.mRowCompanyName.setText(jobDetails.getName());

            if (jobDetails.getLocationNames() != null
                    && jobDetails.getLocationNames().size() > 0)
                if (jobDetails.getLocationNames().get(0) != null)
                    holder.mRowJobsTextviewLoaction.setText(jobDetails.getLocationNames().get(0).getDistrictName());
            if (jobDetails.getLogo() != null
                    && !jobDetails.getLogo().isEmpty())
                Glide.with(context)
                        .load(jobDetails.getLogo())
                        .into(holder.mRowjobsShapeivCompanylogo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public int getItemCount() {
        return dataModel.getResults().size();
    }
}