package com.example.ernestwong.sidekick;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PatientActivity extends Activity {

    ListView patientHistoryView;
    ListView patientSurgeryView;
    ListView patientAllergyView;
    ListView patientMedicationView;
    ListView patientSocialView;
    ListView patientSubstanceView;

    ArrayAdapter<String> historyList;
    ArrayAdapter<String> surgeryList;
    ArrayAdapter<String> allergyList;
    ArrayAdapter<String> medicationList;
    ArrayAdapter<String> socialList;
    ArrayAdapter<String> substanceList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        patientHistoryView = (ListView) findViewById(R.id.patient_history_list);
        patientSurgeryView = (ListView) findViewById(R.id.patient_surgeries_list);
        patientAllergyView = (ListView) findViewById(R.id.patient_allergies_list);
        patientMedicationView = (ListView) findViewById(R.id.patient_medications_list);
        patientSocialView = (ListView) findViewById(R.id.patient_social_list);
        patientSubstanceView = (ListView) findViewById(R.id.patient_substance_list);
        
        historyList = new ArrayAdapter<String>(this, R.layout.patient_layout);
        surgeryList = new ArrayAdapter<String>(this, R.layout.patient_layout);
        allergyList = new ArrayAdapter<String>(this, R.layout.patient_layout);
        medicationList = new ArrayAdapter<String>(this, R.layout.patient_layout);
        socialList = new ArrayAdapter<String>(this, R.layout.patient_layout);
        substanceList = new ArrayAdapter<String>(this, R.layout.patient_layout);

        populate();
    }

    private void populate() {
        historyList.add("testing string");
    }
}
