package in.startupjobs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.startupjobs.R;
import in.startupjobs.model.Product;

public class JobsFragmentViewAdapter extends RecyclerView.Adapter<JobsFragmentViewAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Product> dataModel;
    private int width, height;
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text_title;
        TextView price;
        TextView description;
        View itemView;
        public MyViewHolder(View view) {
            super(view);
            this.text_title = (TextView) view.findViewById(R.id.title);
            this.price = (TextView) view.findViewById(R.id.price);
            this.description = (TextView) view.findViewById(R.id.description);
            this.itemView = view;
        }
    }
    public JobsFragmentViewAdapter(Context _context, ArrayList<Product> data) {
        this.context = _context;
        this.dataModel = data;
        this.width = 160;
        this.height = 100;
    }
    public JobsFragmentViewAdapter(Context _context, ArrayList<Product> data, int _width, int _height) {
        this.context = _context;
        this.dataModel = data;
        this.width = _width;
        this.height = _height;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_demo, parent, false);
        // view.setOnClickListener(MainActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        try{
//            Glide.with(holder.itemView)
//                    .load(getImage(dataModel.get(position).getImage()))
//                    .fitCenter()
//                    .into(holder.image_view);
            holder.text_title.setText(dataModel.get(position).getTitle());
            holder.price.setText(dataModel.get(position).getPrice());
            holder.description.setText(dataModel.get(position).getDescription());
//            holder.description.setText(context.getString(R.string.price, dataModel.get(position).getPrice()));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Intent intent = new Intent(context, DetailActivity.class);
//                    intent.putExtra("dataSet", dataModel.get(position));
                    //ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(context, holder.image_view, "robot");
//                    context.startActivity(intent);
                }
            });
        }catch (Exception e){e.printStackTrace();}
    }
    private int getImage(String imageName) {
        int drawableResourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        return drawableResourceId;
    }
    @Override
    public int getItemCount() {
        return dataModel.size();
    }
}