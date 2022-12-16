package in.startupjobs.ui.slideshow;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.startupjobs.R;
import in.startupjobs.adapters.JobsFragmentViewAdapter;
import in.startupjobs.model.Product;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private Context context;
    private RecyclerView recyclerView;
    JobsFragmentViewAdapter jobsFragmentViewAdapter;
    private ArrayList<Product> productList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        this.context = getContext();
        recyclerView = root.findViewById(R.id.recyclerview_fragment_recommendedjobs);
        populateData();
        jobsFragmentViewAdapter = new JobsFragmentViewAdapter(context, productList);
        recyclerView.setAdapter(jobsFragmentViewAdapter);


        return root;
    }

        private void populateData() {

            productList.add(new Product("Apple","Sweet Green Apple","Rs 200"));
            productList.add(new Product("Mango","Sweet yellow Mango","Rs 100"));
            productList.add(new Product("Grapes","Sweet green Grapes","Rs 100"));

//        productList.add(new Product("image_1", "Android", getString(R.string.lorem), "4999", "5999", "10% off", 4.2f));
//        productList.add(new Product("image_2", "Beta", getString(R.string.lorem), "6999", "8999", "15% off", 4.6f));
//        productList.add(new Product("image_3", "Cupcake", getString(R.string.lorem), "4999", "5999", "10% off", 3.2f));
//        productList.add(new Product("image_4", "Donut", getString(R.string.lorem), "4999", "5999", "10% off", 4.1f));
//        productList.add(new Product("image_5", "Eclair", getString(R.string.lorem), "4999", "5999", "10% off", 4.8f));
//        productList.add(new Product("image_6", "Froyo", getString(R.string.lorem), "9999", "10999", "10% save now", 3.6f));
//        productList.add(new Product("image_1", "Gingerbread", getString(R.string.lorem), "4999", "5999", "10% off", 2.8f));
//        productList.add(new Product("image_2", "Ice Cream Sandwich", getString(R.string.lorem), "4999", "5999", "10% off", 4.1f));
//        productList.add(new Product("image_3", "Jelly Bean", getString(R.string.lorem), "3999", "4999", "10% off", 4.5f));
//        productList.add(new Product("image_4", "Lollipop", getString(R.string.lorem), "4999", "5999", "10% off", 4.0f));
//        productList.add(new Product("image_5", "Marshmallow", getString(R.string.lorem), "4999", "5999", "10% save now", 5.0f));
//        productList.add(new Product("image_6", "Nougat", getString(R.string.lorem), "5999", "7999", "10% off", 4.1f));
//        productList.add(new Product("image_1", "Oreo", getString(R.string.lorem), "4999", "5999", "10% off", 3.9f));
    }
}