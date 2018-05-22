package com.example.aili.e_care;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowAppointment extends AppCompatActivity {

    ArrayAdapter<String> madapter;
    ListView listtask;
    Mydb mdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_appointment);

        listtask=(ListView)findViewById(R.id.show_list1);
        mdb=new Mydb(this);

        ArrayList<String> tasklist=mdb.getTaskList();
        if(madapter==null)
        {
            madapter=new ArrayAdapter<String>(this,R.layout.namelist,R.id.name1,tasklist);

            listtask.setAdapter(madapter);

        }
        else
        {
            madapter.clear();
            madapter.addAll(tasklist);
            madapter.notifyDataSetChanged();
        }


    }
}
