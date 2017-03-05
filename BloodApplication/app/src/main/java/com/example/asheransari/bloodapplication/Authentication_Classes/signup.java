package com.example.asheransari.bloodapplication.Authentication_Classes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.bloodapplication.R;
import com.example.asheransari.bloodapplication.main_panel;
import com.example.asheransari.bloodapplication.variables_classes.new_users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.asheransari.bloodapplication.Splash.name;

public class signup extends AppCompatActivity {

    private EditText fname, lname, email, psk;
    private Spinner blood;
    private TextView login;
//    private String fName = "df", lName = "dfa", eMail = "wed", pSk = "rds", bLood = "erds";
    private Button signUp;
    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = (EditText) findViewById(R.id.fname_signup);
        lname = (EditText) findViewById(R.id.lname_signup);
        email = (EditText) findViewById(R.id.email_signup);
        psk = (EditText) findViewById(R.id.password_signup);
        blood = (Spinner) findViewById(R.id.blood_spinner_signup);
        login = (TextView) findViewById(R.id.login_signup_lbl);
        signUp = (Button) findViewById(R.id.signup_btn_signup);

        auth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.e("Sign Up",fname.getText().toString());
//                Log.e("Sign Up",lname.getText().toString());
//                Log.e("Sign Up",email.getText().toString());
//                Log.e("Sign Up",psk.getText().toString());
//                Log.e("Sign Up",blood.getSelectedItem().toString());

//                if(fname.getText().equals("")){
//                    Toast.makeText(signup.this, "fname", Toast.LENGTH_SHORT).show();
//                }
//                else if (lname.getText().equals(""))
//                {
//                    Toast.makeText(signup.this, "lname", Toast.LENGTH_SHORT).show();
//
//                }
//                else if(email.getText().toString().equals(""))
//                {
//                    Toast.makeText(signup.this, "email", Toast.LENGTH_SHORT).show();
//
//                }
                if (psk.getText().toString().equals("") || email.getText().toString().equals("") || lname.getText().equals("") || fname.getText().equals("")) {

//                    Log.e("signup  = ", fname.getText() + "    " + lname.getText() + "      " + email.getText() + "      " + psk.getText());
                    Toast.makeText(signup.this, "Please Fill All The Information First", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(email.getText().toString(), psk.getText().toString()).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(signup.this, "Authentication Failed, Please Check ur Internet Connection...", Toast.LENGTH_SHORT).show();
                            } else {
                                new_users users = new new_users(fname.getText().toString() + " " + lname.getText().toString(), email.getText().toString(), blood.getSelectedItem().toString(), psk.getText().toString());
                                mDatabaseReference = mFirebaseDatabase.getReference().child("user");
                                mDatabaseReference.push().setValue(users);
                                Intent i =new Intent(signup.this,main_panel.class);

                                i.putExtra("name", fname.getText().toString()+" "+lname.getText().toString());
                                name = fname.getText().toString()+" "+lname.getText().toString();
                                i.putExtra("group", blood.getSelectedItem().toString());
                                i.putExtra("email", email.getText().toString());
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signup.this, com.example.asheransari.bloodapplication.Authentication_Classes.login.class);
                startActivity(i);
                finish();
            }
        });
    }
}
