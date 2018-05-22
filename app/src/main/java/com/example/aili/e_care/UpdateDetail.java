package com.example.aili.e_care;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateDetail extends AppCompatActivity {

    EditText name,date,time;
    Button medlist, update;
    DBhelper dbh;
    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    String update_name,  update_time, update_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_detail);

        name = (EditText) findViewById(R.id.name_update);

        date = (EditText) findViewById(R.id.date_rupdate);
        time = (EditText) findViewById(R.id.dose_update);

        medlist = (Button) findViewById(R.id.openlist_update);
        update = (Button) findViewById(R.id.save_update);
        medlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(UpdateDetail.this);
                dialog.setContentView(R.layout.activity_medication_list);
                dialog.setTitle("Medicine info");
                Button ok = (Button) dialog.findViewById(R.id.save_list);
                final EditText med_list = (EditText) dialog.findViewById(R.id.editText);
                dbh = new DBhelper(UpdateDetail.this);
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(UpdateDetail.this, "Data Saved", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();


                    }
                });
                dialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_name = name.getText().toString().trim();

                update_time = time.getText().toString().trim();
                update_date = date.getText().toString().trim();
                Toast.makeText(UpdateDetail.this,update_date + update_time+ update_name,Toast.LENGTH_SHORT).show();
                dbh.updatedata(update_name,update_date,update_time);
                Toast.makeText(UpdateDetail.this, "Data Updated Successfuly", Toast.LENGTH_SHORT).show();


            }
        });
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListener, year_x, month_x, day_x);
        return null;

    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_x = year;
            month_x = month + 1;
            day_x = dayOfMonth;
            date.setText("" + day_x + "-" + (month_x) + "-" + year_x);
        }
    };

}
