package com.example.asheransari.bloodapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgetPassword extends AppCompatActivity {
    private Button request;
    private EditText email;
    private TextView back;
    private String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        back =(TextView)findViewById(R.id.login_signup);
        request = (Button)findViewById(R.id.request_btn_forget);
        email = (EditText)findViewById(R.id.email_forget);

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = email.getText().toString();
                if (TextUtils.isEmpty(Email)){
                    Toast.makeText(forgetPassword.this, "Please Complete The Email First..", Toast.LENGTH_SHORT).show();
                }
                else{

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
