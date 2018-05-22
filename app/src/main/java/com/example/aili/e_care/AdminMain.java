package com.example.aili.e_care;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminMain extends AppCompatActivity {

    Button check_name,update,info,logout,appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        check_name=(Button) findViewById(R.id.checkname);
        info=(Button) findViewById(R.id.checkdetail);
        update=(Button) findViewById(R.id.update);
        logout=(Button)findViewById(R.id.logout);
        appointment=(Button)findViewById(R.id.checklist);

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMain.this,ShowAppointment.class);
                startActivity(i);
            }
        });

        check_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMain.this,ShowData.class);
                startActivity(i);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMain.this,CheckInfo.class);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMain.this,UpdateDetail.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(AdminMain.this,AdminLogin.class);
                Toast.makeText(AdminMain.this,"Logout Successfuly",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });



    }
}
