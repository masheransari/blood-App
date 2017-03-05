package com.example.asheransari.bloodapplication;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.asheransari.bloodapplication.MyActivityBinding;

//import com.saylani.databindingsample.databinding.MyActivityBinding;

import com.example.asheransari.bloodapplication.variables_classes.Required_variable_blood;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.asheransari.bloodapplication.Splash.name;

public class post_required extends Fragment {

    private Spinner blood,state,urgent,relationSpi;
    private EditText unit,city, hospital,contact,additional;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mReference, indivualReference;
    private Button mButton;
    private FirebaseUser mFirebaseUser;
//    private MyActivityBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.activity_post_required, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference().child("required");
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        final String email = mFirebaseUser.getEmail();
//        email = email.replace("@","_");
        Log.e("Email ", "email = "+email);
        indivualReference = mFirebaseDatabase.getReference().child(email.replace(".","_"));

        blood = (Spinner)v.findViewById(R.id.required_blood_spinner);
        state = (Spinner)v.findViewById(R.id.required_state_spinner);
        relationSpi = (Spinner)v.findViewById(R.id.required_relation_spinner);
        urgent = (Spinner)v.findViewById(R.id.required_blood_urgency);

        unit = (EditText)v.findViewById(R.id.required_unit_txt);
        city =(EditText)v.findViewById(R.id.required_city_txt);
        hospital = (EditText)v.findViewById(R.id.required_hospital_txt);
        contact = (EditText)v.findViewById(R.id.required_contact_txt);
        additional = (EditText)v.findViewById(R.id.request_additional_txt);
        mButton = (Button)v.findViewById(R.id.required_post_button);

//        final TextView name = (TextView)v.findViewById(R.id.title_name);
//        Toolbar toolbar = findViewById(R.id.toolbar);

//        NavigationView navigationView = (NavigationView) v.findViewById(R.id.nav_view);
//
//        View hView = navigationView.getHeaderView(0);
//        final TextView name = (TextView) hView.findViewById(R.id.title_name);
//        Log.e("data = ",name.getText().toString());

//        String country = "Pakistan";
//        NavigationView navigationView = (NavigationView) v.findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//        View hView = navigationView.getHeaderView(0);
//        final TextView name = (TextView) hView.findViewById(R.id.title_name);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(unit.getText().toString())||TextUtils.isEmpty(city.getText().toString())||TextUtils.isEmpty(hospital.getText().toString())||TextUtils.isEmpty(contact.getText().toString())||TextUtils.isEmpty(additional.getText().toString())){
                    Toast.makeText(getContext(), "Please fill all the Required Information First..", Toast.LENGTH_SHORT).show();
                }
                else{
                    Required_variable_blood variableBlood = new Required_variable_blood(name,email,"Pakistan",blood.getSelectedItem().toString(),state.getSelectedItem().toString(),urgent.getSelectedItem().toString(),relationSpi.getSelectedItem().toString(),Integer.valueOf(unit.getText().toString()),city.getText().toString(),hospital.getText().toString(),contact.getText().toString(),additional.getText().toString());
                    Required_variable_blood indivualBlood = new Required_variable_blood(name,email,"Pakistan",blood.getSelectedItem().toString(),state.getSelectedItem().toString(),urgent.getSelectedItem().toString(),relationSpi.getSelectedItem().toString(),Integer.valueOf(unit.getText().toString()),city.getText().toString(),hospital.getText().toString(),contact.getText().toString(),additional.getText().toString());
                    mReference.push().setValue(variableBlood);
                    indivualReference.push().setValue(indivualBlood);
                    unit.setText("");
                    city.setText("");
                    hospital.setText("");
                    contact.setText("");
                    additional.setText("");

//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    fm.beginTransaction().replace(R.id.content_main_panel,new Home()).commit();
                }

            }
        });

        return v;
    }
}
