package com.example.aili.e_care;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CheckInfo extends AppCompatActivity {

    EditText patientname;
    TextView date,address,doctor_detail,number,u_id,medication_list;
    Button fetch;
    DBhelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info);

        dbh=new DBhelper(this);
        patientname=(EditText)findViewById(R.id.editText2);
        address=(TextView)findViewById(R.id.PatientAddress);
        doctor_detail=(TextView)findViewById(R.id.DoctorDetail);
        number=(TextView)findViewById(R.id.Phonenumber);
        date=(TextView)findViewById(R.id.PatientDate);
        u_id=(TextView)findViewById(R.id.unique_number);
        medication_list=(TextView)findViewById(R.id.medlist);
        fetch=(Button)findViewById(R.id.button2);

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=patientname.getText().toString();
                String date_user=dbh.showPatientDate(n);
                date.setText(date_user);
                String add=dbh.showAddress(n);
                address.setText(add);
                String doc=dbh.showDoctorDetail(n);
                doctor_detail.setText(doc);
                String num=dbh.showNumber(n);
                number.setText(num);
                String id=dbh.showuid(n);
                u_id.setText(id);
                String med_list=dbh.showmedicine(n);
                medication_list.setText(med_list);
              /* SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date strDate = null;
                try {
                    strDate = sdf.parse(date_user);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.currentTimeMillis();
                if (System.currentTimeMillis() <= strDate.getTime()) {
                    Toast.makeText(Checkinfo.this,"Hello",Toast.LENGTH_SHORT).show();
                     NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);
                mBuilder .setSmallIcon(R.mipmap.v);
                mBuilder .setContentTitle(" VCare ");
                mBuilder.setContentText("Patient should be called again");
                NotificationManager mNotificationManager =

                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification ni = mBuilder.build();

                       mNotificationManager.notify(0,ni);

                }*/


            }
        });


    }
}
