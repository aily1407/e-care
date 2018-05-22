package com.example.aili.e_care;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity {

    EditText name,date,address,mobile,time,doctorname;
    Button openlist,save;
    DBhelper dbh;
    int random;

    String user_name,user_date,user_address,user_mobile,user_time,user_doctorname,list;
    int year_x,month_x,day_x;
    static final int DIALOG_ID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);dbh = new DBhelper(this);
        name = (EditText) findViewById(R.id.name_register);
        date = (EditText) findViewById(R.id.date_register);
        address = (EditText) findViewById(R.id.address_register);
        mobile = (EditText) findViewById(R.id.register_number);
        time = (EditText) findViewById(R.id.dose_time);
        doctorname = (EditText) findViewById(R.id.doctor_name);
        save = (Button) findViewById(R.id.save_registration);
        java.util.Calendar c= java.util.Calendar.getInstance();
        year_x=c.get(java.util.Calendar.YEAR);
        month_x=c.get(java.util.Calendar.MONTH);
        day_x=c.get(java.util.Calendar.DAY_OF_MONTH);

        openlist = (Button) findViewById(R.id.openlist);
        openlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Register.this);
                dialog.setContentView(R.layout.activity_medication_list);
                dialog.setTitle("Medicine info");
                Button ok=(Button)dialog.findViewById(R.id.save_list);
                final EditText med_list=(EditText)dialog.findViewById(R.id.editText);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        user_mobile = mobile.getText().toString();
                        list=med_list.getText().toString();
                        dialog.dismiss();


                    }
                });
                dialog.show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_name = name.getText().toString();
                user_date = date.getText().toString();
                user_address = address.getText().toString();
                user_mobile = mobile.getText().toString();
                user_time = time.getText().toString();
                user_doctorname = doctorname.getText().toString();
                if(user_name.equals("")|| user_date.equals("")|| user_address.equals("")||user_mobile.equals("")||user_time.equals("")||user_doctorname.equals("")||list.equals(""))
                {
                    Toast.makeText(Register.this,"Please Provide Details",Toast.LENGTH_SHORT).show();
                }
                else {

                    random = (int) (Math.random() * 10000);
                    String uid = Integer.toString(random);
                    dbh.store(user_name, user_address, user_mobile, user_doctorname, user_date, list, uid, user_time);

                    AlertDialog.Builder adb = new AlertDialog.Builder(Register.this);
                    adb.setTitle("ID");
                    adb.setIcon(R.drawable.icon);

                    adb.setMessage("ID is::" + uid);
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i=new Intent(Register.this,Main.class);
                            startActivity(i);

                        }
                    });






                    adb.show();
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
