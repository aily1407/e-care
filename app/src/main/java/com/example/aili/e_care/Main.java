package com.example.aili.e_care;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Main extends Activity {
    Button register,about,doctor_detail,logout,appointment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register=(Button)findViewById(R.id.btnReg);
        about=(Button)findViewById(R.id.btnAu);
        doctor_detail=(Button)findViewById(R.id.btnDD);
        logout=(Button)findViewById(R.id.btnLogout);
        appointment=(Button)findViewById(R.id.btnApp);

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main.this,Appointment.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main.this,Register.class);
                startActivity(i);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this,About.class);
                startActivity(i);
            }
        });

        doctor_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this,Details.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Main.this,Login.class);
                Toast.makeText(Main.this,"Logout Successfuly",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }

}
