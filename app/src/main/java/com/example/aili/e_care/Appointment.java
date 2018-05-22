package com.example.aili.e_care;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Appointment extends AppCompatActivity {

    EditText patient_name,date,problem,patient_mobile;
    Mydb mydb;
    Button store;
    static final int DIALOG_ID=0;
    int year_x,month_x,day_x;
    String name,reserve_date,patient_problem,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        patient_name = (EditText) findViewById(R.id.patient_name);
        date = (EditText) findViewById(R.id.patient_date);
        problem = (EditText) findViewById(R.id.patient_problem);
        store = (Button) findViewById(R.id.save_appointment);
        patient_mobile = (EditText) findViewById(R.id.patient_mobile);
        final Mydb mydb = new Mydb(this);
        Calendar c=Calendar.getInstance();
        year_x=c.get(Calendar.YEAR);
        month_x=c.get(Calendar.MONTH);
        day_x=c.get(Calendar.DAY_OF_MONTH);

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = patient_name.getText().toString();
                reserve_date = date.getText().toString();
                patient_problem = problem.getText().toString();
                mobile = patient_mobile.getText().toString();
                if (name.equals("") || reserve_date.equals("") || patient_problem.equals("") || mobile.equals("")) {
                    Toast.makeText(Appointment.this, "Please fill the credentials", Toast.LENGTH_LONG).show();

                } else {
                    mydb.store(name, mobile, reserve_date, patient_problem);
                    Intent i =new Intent(Appointment.this,Main.class);
                    startActivity(i);

                }

            }
        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
    }

    protected Dialog onCreateDialog(int id)
    {
        if(id==DIALOG_ID)
            return new DatePickerDialog(this,dpickerListener,year_x,month_x,day_x);
        return null;

    }
    private DatePickerDialog.OnDateSetListener dpickerListener=new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x=year;
            month_x=month+1;
            day_x=dayOfMonth;
            date.setText( "" + day_x + "-" + (month_x) + "-" + year_x);
        }
    };

}
