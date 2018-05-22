package com.example.aili.e_care;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignUp extends AppCompatActivity {
    EditText fname,lname,email,password,repassword;
    String pass,user_email,re_pass;
    FirebaseAuth auth1;
    Button signup;

    @SuppressLint("WrongViewCast")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        email=(EditText)findViewById(R.id.mail);
        password=(EditText)findViewById(R.id.pass);
        repassword=(EditText)findViewById(R.id.repass);

        signup = (Button)findViewById(R.id.signup);

        auth1=FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email=email.getText().toString();
                pass=password.getText().toString();
                re_pass=repassword.getText().toString();
                if(user_email.equals("") || pass.equals("") || re_pass.equals(""))
                {
                    Toast.makeText(SignUp.this,"Error",Toast.LENGTH_LONG).show();
                }
                else
                {

                    auth1.createUserWithEmailAndPassword(user_email,pass).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            Toast.makeText(SignUp.this, "Successful SignUp", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(SignUp.this, Login.class);
                            startActivity(in);

                            if(!task.isSuccessful())
                            {
                                Toast.makeText(SignUp.this,"Error Occured",Toast.LENGTH_LONG).show();
                            }
                        }

                    });

                }



            }
        });


    }





}

