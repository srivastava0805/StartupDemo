package in.startupjobs.activity;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;

import in.startupjobs.R;
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
    private DatePickerDialog StartTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        initView();
        setAdapterForLocation();
        setDatePicker();

    }

    private void setDatePicker() {
        final Calendar newCalendar = Calendar.getInstance();
        StartTime = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mEditpdSpinnerDob.setText(GlobalVariablesNMethods.convertDate(EditProfileDetailsActivity.this
                        , newDate.getTime().toString(), "EEE MMM dd HH:mm:ss zzz yyyy", "dd MMM yyyy"));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    private void setAdapterForLocation() {
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>
//                (this, android.R.layout.select_dialog_item, fruits);
//        //Getting the instance of AutoCompleteTextView
//        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.editpd_autocompletetv_location);
//        actv.setThreshold(1);//will start working from first character
//        actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
//        actv.setTextColor(Color.RED);
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
        mRdbJava = findViewById(R.id.rdbJava);
        mRdbPython = findViewById(R.id.rdbPython);
        mRdbAndroid = findViewById(R.id.rdbAndroid);
        mRdbAngular = findViewById(R.id.rdbAngular);
        setClicks();
    }

    private void setClicks() {
        mRdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ColorStateList colorStateList = new ColorStateList(
                        new int[][]{
                                new int[]{getResources().getColor(R.color.color_accent)}
                        },
                        new int[]{getResources().getColor(R.color.purple)}
                );
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rdbJava:
                        mRdbJava.setButtonTintList(ColorStateList.valueOf(getColor(R.color.purple)));
                        break;

                    case R.id.rdbPython:
                        mRdbPython.setButtonTintList(colorStateList); // set the color tint list
                        mRdbJava.invalidate();
                        break;

                    case R.id.rdbAndroid:
                        mRdbAndroid.setButtonTintList(colorStateList); // set the color tint list
                        mRdbAndroid.invalidate();
                        break;

                    case R.id.rdbAngular:
                        mRdbAngular.setButtonTintList(colorStateList); // set the color tint list
                        mRdbAngular.invalidate();
                }
            }
        });
        mEditpdSpinnerDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime.show();
            }
        });
    }
}
