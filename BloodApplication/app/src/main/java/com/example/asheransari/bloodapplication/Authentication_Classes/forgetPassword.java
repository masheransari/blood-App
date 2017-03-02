package com.example.asheransari.bloodapplication.Authentication_Classes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.bloodapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {
    private Button request;
    private EditText email;
    private TextView back;
    private String Email;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        back =(TextView)findViewById(R.id.login_signup);
        request = (Button)findViewById(R.id.request_btn_forget);
        email = (EditText)findViewById(R.id.email_forget);
        auth = FirebaseAuth.getInstance();

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = email.getText().toString();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(forgetPassword.this, "Please Complete The Email First..", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.sendPasswordResetEmail(Email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(!task.isSuccessful())
                            {
                                Toast.makeText(forgetPassword.this, "Please Check ur Email or Internet Connection", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                email.setText("");
                                Toast.makeText(forgetPassword.this, "Please Check Your Provided Email Address.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(forgetPassword.this,login.class);
                startActivity(i);
            }
        });
    }
}
