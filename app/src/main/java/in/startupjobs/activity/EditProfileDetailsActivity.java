package in.startupjobs.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import in.startupjobs.R;
import in.startupjobs.model.basicDetails.BasicDetailsReponse;
import in.startupjobs.model.basicPublicProfileDetails.PublicProfileDetailsByIDResponse;
import in.startupjobs.model.editProfile.EditProfileResponseData;
import in.startupjobs.model.editProfile.SearchCitiesResponse;
import in.startupjobs.model.editProfile.ToSendEditProfileData;
import in.startupjobs.model.editProfile.ToSendProfessionalDetails;
import in.startupjobs.model.editProfile.ToSendWorkExpData;
import in.startupjobs.services.GetAllCitiesServiceService;
import in.startupjobs.services.PostAddWorkExpService;
import in.startupjobs.services.PostEditPersonalDetailsService;
import in.startupjobs.services.PostEditProfessionalDetailsService;
import in.startupjobs.utils.AppConstants;
import in.startupjobs.utils.GlobalVariablesNMethods;

public class EditProfileDetailsActivity extends AppCompatActivity {

    private TextView mEditdetailsTextviewTitle;
    private TextView mEditdetailsTextviewDiscard;
    private AppCompatEditText mEditpdEdittextName;
    private AppCompatEditText mEditpdEdittextEmail;
    private AppCompatEditText mEditpdEdittextMobileno;
    private AutoCompleteTextView mEditpdAutocompletetvLocation;
    private AppCompatTextView mEditpdSpinnerDob;
    private RadioGroup mRdGroup;
    private RadioButton mRdbJava;
    private RadioButton mRdbPython;
    private RadioButton mRdbAndroid;
    private RadioButton mRdbAngular;
    private DatePickerDialog datePickerForDob;
    private MaterialCardView mEditdetailsCardviewToolbarheader;
    private AppCompatEditText mEditpdEdittextJobtitle;
    private AppCompatEditText mEditpdEdittextAnualsalry;
    private AppCompatEditText mEditpdEdittextTotalexp;
    private AppCompatEditText mEditpdEdittextTeamhandled;
    private AppCompatEditText mEditpdEdittextProfessionalSummary;
    private AppCompatEditText mEditwexpEdittextDesignation;
    private AppCompatEditText mEditwexpEdittextCompanyname;
    private AppCompatEditText mEditwexpEdittextYourRole;
    private AppCompatEditText mEditwexpEdittextSalaryInLakhs;
    private AppCompatTextView mEditwexpEdittextExpplaceholder;
    private AppCompatTextView mEditwexpEdittextFromdate;
    private AppCompatTextView mEditwexpEdittextTodate;
    private DatePickerDialog datePickerForFromDate;
    private DatePickerDialog datePickerForToDate;
    private ConstraintLayout mEditdetailsLayoutPersonaldetails;
    private ConstraintLayout mEditdetailsLayoutProfessionaldetails;
    private ConstraintLayout mEditdetailsLayoutWorkexperience;
    private boolean personalDetails;
    private boolean workExp;
    private boolean professionalDetails;
    private AppCompatButton mBtnSaveChanges;
    private PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse;
    private BasicDetailsReponse basicDetailsResponse;
    private List<String> citiesList = new ArrayList<>();
    private String selectedGender;
    private AppCompatEditText mEditwexpEdittextCurrentCompany;
    private AppCompatEditText mEditpdEdittextNoticeperiod;
    private List<SearchCitiesResponse> listOfCities;
    private CheckBox mEditwexpCheckBoxCurrentlyWorking;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        initView();
        setSearchLogicOnLocationAutoComplete();
        setDatePicker();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setDatePicker() {
        final Calendar newCalendar = Calendar.getInstance();
        datePickerForDob = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mEditpdSpinnerDob.setText(GlobalVariablesNMethods.convertDate(EditProfileDetailsActivity.this
                        , newDate.getTime().toString(), "EEE MMM dd HH:mm:ss zzz yyyy", "dd MMM yyyy"));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerForFromDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mEditwexpEdittextFromdate.setText(GlobalVariablesNMethods.convertDate(EditProfileDetailsActivity.this
                        , newDate.getTime().toString(), "EEE MMM dd HH:mm:ss zzz yyyy", "dd MMM yyyy"));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerForToDate = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mEditwexpEdittextTodate.setText(GlobalVariablesNMethods.convertDate(EditProfileDetailsActivity.this
                        , newDate.getTime().toString(), "EEE MMM dd HH:mm:ss zzz yyyy", "dd MMM yyyy"));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setSearchLogicOnLocationAutoComplete() {
        new GetAllCitiesServiceService(EditProfileDetailsActivity.this, " ", listCities -> {
            listOfCities = listCities;
            setAdapterForLocation(listCities);
        });
    }

    private void setAdapterForLocation(List<SearchCitiesResponse> listCities) {
        citiesList.clear();
        for (int i = 0; i < listCities.size(); i++) {
            citiesList.add(listCities.get(i).getDistrictName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (EditProfileDetailsActivity.this, android.R.layout.select_dialog_item, citiesList);
        mEditpdAutocompletetvLocation.setThreshold(1);//will start working from first character
        mEditpdAutocompletetvLocation.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        mEditpdAutocompletetvLocation.setTextColor(Color.BLACK);
    }

    private void initView() {
        mEditdetailsTextviewTitle = findViewById(R.id.editdetails_textview_title);
        mEditdetailsTextviewDiscard = findViewById(R.id.editdetails_textview_discard);
        mEditpdEdittextName = findViewById(R.id.editpd_edittext_name);
        mEditpdEdittextEmail = findViewById(R.id.editpd_edittext_email);
        mEditpdEdittextMobileno = findViewById(R.id.editpd_edittext_mobileno);
        mEditpdAutocompletetvLocation = findViewById(R.id.editpd_autocompletetv_location);
        mEditpdSpinnerDob = findViewById(R.id.editpd_spinner_dob);
        mRdGroup = findViewById(R.id.rdGroup);
        mRdbJava = findViewById(R.id.rdbMale);
        mRdbPython = findViewById(R.id.rdbFemale);
        mRdbAndroid = findViewById(R.id.rdbNonBinary);
        mRdbAngular = findViewById(R.id.rdbPreferNotToSay);
        mEditdetailsCardviewToolbarheader = findViewById(R.id.editdetails_cardview_toolbarheader);
        mEditpdEdittextJobtitle = findViewById(R.id.editpd_edittext_jobtitle);
        mEditpdEdittextAnualsalry = findViewById(R.id.editpd_edittext_anualsalry);
        mEditpdEdittextTotalexp = findViewById(R.id.editpd_edittext_totalexp);
        mEditpdEdittextProfessionalSummary = findViewById(R.id.editpd_edittext_professionalsummarry);
        mEditpdEdittextNoticeperiod = findViewById(R.id.editpd_edittext_professionalsummarry);

        mEditwexpEdittextDesignation = findViewById(R.id.editwexp_edittext_designation);
        mEditwexpEdittextCurrentCompany = findViewById(R.id.editpd_edittext_currentcompany);
        mEditwexpEdittextCompanyname = findViewById(R.id.editwexp_edittext_companyname);
        mEditwexpEdittextYourRole = findViewById(R.id.editwexp_edittext_yourrole);
        mEditwexpEdittextSalaryInLakhs = findViewById(R.id.editwexp_edittext_salaryinlakhs);
        mEditwexpEdittextExpplaceholder = findViewById(R.id.editwexp_edittext_expplaceholder);
        mEditwexpEdittextFromdate = findViewById(R.id.editwexp_edittext_fromdate);
        mEditwexpEdittextTodate = findViewById(R.id.editwexp_edittext_todate);
        mEditwexpCheckBoxCurrentlyWorking = findViewById(R.id.editwexp_checkbox_iworkhere);

        mEditdetailsLayoutPersonaldetails = findViewById(R.id.editdetails_layout_personaldetails);
        mEditdetailsLayoutProfessionaldetails = findViewById(R.id.editdetails_layout_professionaldetails);
        mEditdetailsLayoutWorkexperience = findViewById(R.id.editdetails_layout_workexperience);
        mBtnSaveChanges = findViewById(R.id.editdetails_btn_savechanges);

        setData();
        setClicks();
    }

    private void setData() {
        if (getIntent() != null) {
            if (getIntent().getStringExtra(AppConstants.PROFILE_HEADER) != null) {
                mEditdetailsTextviewTitle.setText(getIntent().getStringExtra(AppConstants.PROFILE_HEADER));
                if (getIntent().getStringExtra(AppConstants.PROFILE_HEADER).equalsIgnoreCase(AppConstants.WORK_EXPERIENCE)) {
                    setAndHideViews(mEditdetailsLayoutWorkexperience);
                    setInActivePage();
                    workExp = true;
                } else if (getIntent().getStringExtra(AppConstants.PROFILE_HEADER)
                        .equalsIgnoreCase(AppConstants.PROFESSIONAL_DETAILS)) {
                    basicDetailsResponse = (BasicDetailsReponse) getIntent().getSerializableExtra(AppConstants.ALLDATA);
                    setAndHideViews(mEditdetailsLayoutProfessionaldetails);
                    setInActivePage();
                    professionalDetails = true;
                    setDataProfessional(basicDetailsResponse);
                } else {
                    publicProfileDetailsByIDResponse = (PublicProfileDetailsByIDResponse) getIntent().getSerializableExtra(AppConstants.ALLDATA);
                    setAndHideViews(mEditdetailsLayoutPersonaldetails);
                    setInActivePage();
                    personalDetails = true;
                    setDataPersonal(publicProfileDetailsByIDResponse);
                }
            }
        }
    }

    private void setDataProfessional(BasicDetailsReponse basicDetailsResponse) {
        if (basicDetailsResponse != null) {
            mEditpdEdittextJobtitle.setText(basicDetailsResponse.getCurrentDesignation());
            mEditwexpEdittextCurrentCompany.setText(basicDetailsResponse.getCurrentCompanyName());
            mEditpdEdittextAnualsalry.setText(basicDetailsResponse.getCurrentCTC());
            mEditpdEdittextTotalexp.setText(basicDetailsResponse.getTotalExperienceYears() + " Years");
            mEditpdEdittextNoticeperiod.setText(basicDetailsResponse.getNoticePeriod());
            if (basicDetailsResponse.getCurrentLocation() != null)
                mEditpdAutocompletetvLocation.setText(basicDetailsResponse.getCurrentLocation().getDistrictName());
            mEditpdEdittextProfessionalSummary.setText(basicDetailsResponse.getProfessionalSummary());
        }
    }

    private void setDataPersonal(PublicProfileDetailsByIDResponse publicProfileDetailsByIDResponse) {
        mEditpdEdittextName.setText(publicProfileDetailsByIDResponse.getAccount().getName());
        mEditpdEdittextEmail.setText(publicProfileDetailsByIDResponse.getContactDetails().getEmail());
        mEditpdEdittextMobileno.setText(publicProfileDetailsByIDResponse.getContactDetails().getPhone());
        if (publicProfileDetailsByIDResponse.getAccount().getCurrentLocation() != null)
            mEditpdAutocompletetvLocation.setText(publicProfileDetailsByIDResponse.getAccount().getCurrentLocation().getDistrictName());
    }


    private void setInActivePage() {
        workExp = false;
        professionalDetails = false;
        personalDetails = false;
    }

    private void setAndHideViews(ConstraintLayout viewToBeShown) {
        mEditdetailsLayoutPersonaldetails.setVisibility(View.INVISIBLE);
        mEditdetailsLayoutProfessionaldetails.setVisibility(View.INVISIBLE);
        mEditdetailsLayoutWorkexperience.setVisibility(View.INVISIBLE);
        viewToBeShown.setVisibility(View.VISIBLE);
    }

    private void setClicks() {
        mEditdetailsTextviewDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRdGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{getResources().getColor(R.color.color_accent)}
                    },
                    new int[]{getResources().getColor(R.color.purple)}
            );
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.rdbMale:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        selectedGender = "male";
                        mRdbJava.setButtonTintList(ColorStateList.valueOf(getColor(R.color.purple)));
                    }
                    break;

                case R.id.rdbFemale:
                    selectedGender = "female";
                    mRdbPython.setButtonTintList(colorStateList); // set the color tint list
                    mRdbJava.invalidate();
                    break;

                case R.id.rdbNonBinary:
                    selectedGender = "transgender";
                    mRdbAndroid.setButtonTintList(colorStateList); // set the color tint list
                    mRdbAndroid.invalidate();
                    break;

                case R.id.rdbPreferNotToSay:
                    selectedGender = "not_specified";
                    mRdbAngular.setButtonTintList(colorStateList); // set the color tint list
                    mRdbAngular.invalidate();
            }
        });
        mEditpdSpinnerDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerForDob.show();
            }
        });

        mEditwexpEdittextFromdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerForFromDate.show();
            }
        });


        mEditwexpEdittextTodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerForToDate.show();
            }
        });


        mBtnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (personalDetails)
                    collectDataChangesToPersonalDetails();
                else if (professionalDetails) {
                    collectDataChangesToProfessionalDetails();
                } else collectDataChangesToWorkExperience();
            }
        });

        mEditwexpCheckBoxCurrentlyWorking.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                    mEditwexpEdittextTodate.setVisibility(View.INVISIBLE);
                else mEditwexpEdittextTodate.setVisibility(View.VISIBLE);
            }
        });
    }

    private void collectDataChangesToWorkExperience() {
        String endDate = null;
        ToSendWorkExpData toSendWorkExpData = new ToSendWorkExpData();
        String designation = mEditwexpEdittextDesignation.getText().toString().trim();
        String companyName = mEditwexpEdittextCompanyname.getText().toString().trim();
        String annualSalary = mEditwexpEdittextSalaryInLakhs.getText().toString().replaceAll("[^0-9]", "").trim();
        String yourRole = mEditwexpEdittextYourRole.getText().toString().trim();
        String startDate = GlobalVariablesNMethods.convertDate(this, mEditwexpEdittextFromdate.getText().toString().trim()
                , "dd MMM yyyy", "yyyy-MM");
        if (!mEditwexpCheckBoxCurrentlyWorking.isChecked()) {
            endDate = GlobalVariablesNMethods.convertDate(this, mEditwexpEdittextTodate.getText().toString().trim()
                    , "dd MMM yyyy", "yyyy-MM");
            toSendWorkExpData.setEndDate(endDate);
        } else toSendWorkExpData.setCurrentlyEmployed(true);
        toSendWorkExpData.setDesignation(designation);
        toSendWorkExpData.setCompanyName(companyName);
        toSendWorkExpData.setCurrentCtcLakhs(Integer.valueOf(annualSalary));
        toSendWorkExpData.setCurrentCtcThousands(Integer.valueOf(annualSalary));
        toSendWorkExpData.setDescription(yourRole);
        toSendWorkExpData.setStartDate(startDate);

        new PostAddWorkExpService(this,
                toSendWorkExpData, profileResponseData -> {
            Intent _result = new Intent();
            setResult(Activity.RESULT_OK, _result);
            finish();
        });
    }

    private void collectDataChangesToProfessionalDetails() {
        ToSendProfessionalDetails toSendProfessionalDetails = new ToSendProfessionalDetails();
        String currentDesignation = mEditpdEdittextJobtitle.getText().toString().trim();
        String currentCompany = mEditwexpEdittextCurrentCompany.getText().toString().trim();
        String annualSalary = mEditpdEdittextAnualsalry.getText().toString().trim();
        String totalExp = mEditpdEdittextTotalexp.getText().toString().replaceAll("[^0-9]", "").trim();
        String currentLocation = mEditpdAutocompletetvLocation.getText().toString().trim();
        String professionalSummary = mEditpdEdittextProfessionalSummary.getText().toString().trim();
        String noticePeriod = mEditpdEdittextNoticeperiod.getText().toString().trim();

        toSendProfessionalDetails.setCurrentDesignation(currentDesignation);
        toSendProfessionalDetails.setCompanyId(5);
        toSendProfessionalDetails.setCurrentCTC(annualSalary);
        toSendProfessionalDetails.setTotalExperienceYears(Integer.valueOf(totalExp));
        toSendProfessionalDetails.setProfessionalSummary(professionalSummary);
        if (!currentLocation.isEmpty() && listOfCities != null)
            for (int i = 0; i < listOfCities.size(); i++) {
                if (currentLocation.equalsIgnoreCase(listOfCities.get(i).getDistrictName()))
                    toSendProfessionalDetails.setCurrentLocation(listOfCities.get(i).getId());
            }
        new PostEditProfessionalDetailsService(this,
                toSendProfessionalDetails, profileResponseData -> {
            Intent _result = new Intent();
            setResult(Activity.RESULT_OK, _result);
            finish();
        });
    }

    private void collectDataChangesToPersonalDetails() {
        String dob = null;
        ToSendEditProfileData toSendEditProfileData = new ToSendEditProfileData();
        String name = mEditpdEdittextName.getText().toString().trim();
        String email = mEditpdEdittextEmail.getText().toString().trim();
        String mobileNo = mEditpdEdittextMobileno.getText().toString().trim();
        if (!mEditpdSpinnerDob.getText().toString().trim().isEmpty()) {
            dob = GlobalVariablesNMethods.convertDate(this, mEditpdSpinnerDob.getText().toString().trim(), "dd MMM yyyy", "yyyy-MM-dd");
            toSendEditProfileData.setBirthday(dob);
        }
        toSendEditProfileData.setName(name);
        toSendEditProfileData.setEmail(email);

        toSendEditProfileData.setMobileNumber(mobileNo);
        toSendEditProfileData.setGender(selectedGender);
        toSendEditProfileData.setSocialFacebook(publicProfileDetailsByIDResponse.getSocalLinks().getFacebook());
        toSendEditProfileData.setSocialInstagram(publicProfileDetailsByIDResponse.getSocalLinks().getInstagram());
        toSendEditProfileData.setSocialWebsite(publicProfileDetailsByIDResponse.getSocalLinks().getWebsite());
        toSendEditProfileData.setSocialLinkedIn(publicProfileDetailsByIDResponse.getSocalLinks().getLinkedIn());
        toSendEditProfileData.setSocialTwitter(publicProfileDetailsByIDResponse.getSocalLinks().getTwitter());
        new PostEditPersonalDetailsService(this, toSendEditProfileData, new PostEditPersonalDetailsService.onResponseFromEditProfilePersonal() {
            @Override
            public void sendEditProfileResponse(EditProfileResponseData profileResponseData) {
                Intent _result = new Intent();
                setResult(Activity.RESULT_OK, _result);
                finish();
            }
        });
    }
}
