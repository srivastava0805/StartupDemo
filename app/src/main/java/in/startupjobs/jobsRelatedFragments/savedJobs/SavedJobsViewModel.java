package in.startupjobs.jobsRelatedFragments.savedJobs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SavedJobsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SavedJobsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}