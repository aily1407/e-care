package com.example.aili.e_care;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowData extends AppCompatActivity {

    ArrayAdapter<String> madapter;
    ListView listtask;
    DBhelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        listtask=(ListView)findViewById(R.id.show_list);
        dbh=new DBhelper(this);

        ArrayList<String> tasklist=dbh.getTaskList();
        if(madapter==null)
        {
            madapter=new ArrayAdapter<String>(this,R.layout.mylist,R.id.name,tasklist);

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
