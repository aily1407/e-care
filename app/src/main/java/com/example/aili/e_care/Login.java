package com.example.aili.e_care;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    EditText name,pass;
    TextView signup,admin;
    Button signin;
    String user_name,password;
    FirebaseAuth auth;
    Connector con;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);
        signup=(TextView)findViewById(R.id.signup);
        admin=(TextView)findViewById(R.id.admin);
        signin=(Button)findViewById(R.id.signin);
        auth=FirebaseAuth.getInstance();
        con=new  Connector(this);
        if(con.isConnected()==false)
        {
            final AlertDialog.Builder adb=new AlertDialog.Builder(this);
            adb.setMessage("No Internet Connection!!");
            adb.setIcon(R.drawable.icon);
            adb.setTitle("Alert");

            adb.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    moveTaskToBack(true);
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                }
            });
            adb.show();


        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name= name.getText().toString();
                password = pass.getText().toString();
                if (user_name.equals("") || password.equals("")) {
                    Toast.makeText(Login.this, "Incorrect Details", Toast.LENGTH_LONG).show();
                } else {

                    auth.signInWithEmailAndPassword(user_name, password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(Login.this, "Error", Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent i = new Intent(Login.this, Main.class);
                                        startActivity(i);
                                    }
                                }
                            });
                }
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,AdminLogin.class);
                startActivity(i);
            }
        });





    }
}