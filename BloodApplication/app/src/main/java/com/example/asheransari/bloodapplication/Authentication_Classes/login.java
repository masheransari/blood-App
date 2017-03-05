package com.example.asheransari.bloodapplication.Authentication_Classes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.bloodapplication.R;
import com.example.asheransari.bloodapplication.main_panel;
import com.example.asheransari.bloodapplication.variables_classes.new_users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.asheransari.bloodapplication.Splash.name;

public class login extends AppCompatActivity {

    private EditText uname, password;
    private Button login, signup;
    private TextView forget;
//    String email = null;
//    String passw = null;
    private FirebaseAuth auth;
    private FirebaseDatabase mFirebaseDatabase;
    ChildEventListener mChildEventListener = null;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference().child("user");

        uname = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        login = (Button) findViewById(R.id.login_login_btn);
        signup = (Button) findViewById(R.id.login_signup_btn);
        forget = (TextView) findViewById(R.id.login_forget);

//        referenceUserData = mFirebaseDatabase.getReference()

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                email = uname.getText().toString();
//                passw = password.getText().toString();
                if (TextUtils.isEmpty(uname.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    Toast.makeText(login.this, "Please Enter The Complete Detail First", Toast.LENGTH_SHORT).show();
                } else {
//                    progressBar.setVisibility(View.VISIBLE);
                    auth.signInWithEmailAndPassword(uname.getText().toString(), password.getText().toString()).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

//                            Log.e("Login", email + " and password = " + passw);
                            if (!task.isSuccessful()) {
                                Toast.makeText(login.this, "" + getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                            } else {
                                mDatabaseReference = mFirebaseDatabase.getReference().child("user");
                                fetchUserDetail(uname.getText().toString());
                            }
                        }
                    });
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, signup.class);
                startActivity(i);
                finish();
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this, forgetPassword.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void fetchUserDetail(final String email) {
        Log.e("email",""+email);
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    new_users users = dataSnapshot.getValue(new_users.class);
//                    Log.e("check ", "" + dataSnapshot);
                    ArrayList<new_users> arrayList = new ArrayList<>();
                    arrayList.add(dataSnapshot.getValue(new_users.class));

                    for (int i = 0; i < arrayList.size(); i++) {
                        Log.e("count",""+arrayList.get(i).getEmail());

                        if (email.equals(arrayList.get(i).getEmail())) {
                            Toast.makeText(login.this, "in fetchUserDetail method and further in if condition", Toast.LENGTH_SHORT).show();
                            Intent a = new Intent(login.this, main_panel.class);
                            name = arrayList.get(i).getName();
                            a.putExtra("name", arrayList.get(i).getName());
                            a.putExtra("group", arrayList.get(i).getBlood());
                            a.putExtra("email", arrayList.get(i).getEmail());
                            startActivity(a);
                            finish();
                        }
                    }
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            mDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
