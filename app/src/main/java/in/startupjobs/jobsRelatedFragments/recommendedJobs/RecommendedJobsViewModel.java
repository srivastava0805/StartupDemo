package in.startupjobs.jobsRelatedFragments.recommendedJobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RecommendedJobsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RecommendedJobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}