package in.startupjobs.ui.company;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import in.startupjobs.R;
import in.startupjobs.adapter.SearchedCompaniesResultsViewAdapter;
import in.startupjobs.services.GetSearchedCompanies;

public class CompaniesFragment extends Fragment {

    private CompanyViewModel companyViewModel;
    private SearchedCompaniesResultsViewAdapter searchedCompanyAdapter;
    private RecyclerView mCompanyRecyclerViewCompanies;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        companyViewModel =
                ViewModelProviders.of(this).get(CompanyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_company, container, false);
        initView(root);
        getCompaniesDataRequest();
        return root;
    }

    private void initView(View root) {
        mCompanyRecyclerViewCompanies = root.findViewById(R.id.company_recyclerview_companies);
    }

    private void getCompaniesDataRequest() {
        new GetSearchedCompanies(getActivity(), 30, "", getCompaniesResponse -> {
            if (getCompaniesResponse != null) {
                mCompanyRecyclerViewCompanies.setHasFixedSize(true);
                mCompanyRecyclerViewCompanies.setLayoutManager(new GridLayoutManager(getActivity(), 1));
                searchedCompanyAdapter = new SearchedCompaniesResultsViewAdapter(getActivity(), getCompaniesResponse, false);
                mCompanyRecyclerViewCompanies.setAdapter(searchedCompanyAdapter);
            }
        });
    }
}