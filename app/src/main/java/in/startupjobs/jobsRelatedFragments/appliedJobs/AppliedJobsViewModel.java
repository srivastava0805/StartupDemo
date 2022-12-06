package in.startupjobs.jobsRelatedFragments.appliedJobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppliedJobsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AppliedJobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}