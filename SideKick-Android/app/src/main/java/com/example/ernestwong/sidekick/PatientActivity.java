package com.example.ernestwong.sidekick;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PatientActivity extends Activity {

    TextView patientHistoryView;
    TextView patientSurgeryView;
    TextView patientAllergyView;
    TextView patientMedicationView;
    TextView patientSocialView;
    TextView patientSubstanceView;

    TextView patientHistoryHeader;
    TextView patientSurgeryHeader;
    TextView patientAllergyHeader;
    TextView patientMedicationHeader;
    TextView patientSocialHeader;
    TextView patientSubstanceHeader;

    ArrayAdapter<String> historyText;
    ArrayAdapter<String> surgeryText;
    ArrayAdapter<String> allergyText;
    ArrayAdapter<String> medicationText;
    ArrayAdapter<String> socialText;
    ArrayAdapter<String> substanceText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.patient_layout);

        patientHistoryView = (TextView) findViewById(R.id.history_text);
        patientSurgeryView = (TextView) findViewById(R.id.surgeries_text);
        patientAllergyView = (TextView) findViewById(R.id.allergies_text);
        patientMedicationView = (TextView) findViewById(R.id.medications_text);
        patientSocialView = (TextView) findViewById(R.id.social_text);
        patientSubstanceView = (TextView) findViewById(R.id.substance_text);

        patientHistoryHeader = (TextView) findViewById(R.id.patient_history);
        patientSurgeryHeader = (TextView) findViewById(R.id.patient_surgeries);
        patientAllergyHeader = (TextView) findViewById(R.id.patient_allergies);
        patientMedicationHeader = (TextView) findViewById(R.id.patient_medications);
        patientSocialHeader = (TextView) findViewById(R.id.patient_social);
        patientSubstanceHeader = (TextView) findViewById(R.id.patient_substance);


        patientHistoryView.setText(Html.fromHtml("&#8226; Diabetes Type 2 (since 1995) <br> &#8226; Congestive Heart Failure <br> &#8226; Atrial Fibrillatio <br> &#8226; Myocardial Infarct (2008) – treated with CABG <br> &#8226; Osteoporosis\nDementia <br> &#8226; Previous deep vein thrombosis (2013) "));
        patientSurgeryView.setText(Html.fromHtml("&#8226; Right hip fracture repair (2013) – complicated by deep vein thrombosis post-operatively<br> &#8226; Coronary Artery Bypass Graft x2 (2008) <br> &#8226; Cataract repair (L eye in 2006, R eye in 2007) "));
        patientAllergyView.setText(Html.fromHtml("&#8226; Furosemide<br> &#8226; Bisoprolol <br> &#8226; Janumet (Sitagliptin and metformin) <br> &#8226; Warfarin <br>  &#8226; Aspirin <br> &#8226; Alendronate "));
        patientMedicationView.setText(Html.fromHtml("&#8226; Furosemide<br> &#8226; Bisoprolol <br> &#8226; Janumet (Sitagliptin and metformin) <br> &#8226; Warfarin <br>  &#8226; Aspirin <br> &#8226; Alendronate "));
        patientSocialView.setText(Html.fromHtml("&#8226; Lives in ‘ABC’ nursing home <br> &#8226; Husband deceased <br> &#8226; 2 children – son lives in New York, daughter lives in Markham (Power of Attorney) <br> &#8226; No other friends or relatives in the city "));
        patientSubstanceView.setText(Html.fromHtml("&#8226; No smoking, alcohol or recreational drug use "));

        patientHistoryHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(patientHistoryView);
            }
        });
        patientSurgeryHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(patientSurgeryView);
            }
        });
        patientAllergyHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(patientAllergyView);
            }
        });
        patientMedicationHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(patientMedicationView);
            }
        });
        patientSocialHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(patientSocialView);
            }
        });
        patientSubstanceHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleText(patientSubstanceView);
            }
        });
    }

    private void toggleText(TextView textview) {
        boolean hidden = (textview.getVisibility() == View.GONE);

        if(hidden) {
            textview.setVisibility(View.VISIBLE);
        } else {
            textview.setVisibility(View.GONE);
        }
    }
}
