package com.example.aili.e_care;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Mydb extends SQLiteOpenHelper {

    private static final String DB_NAME = "ecare_appointment";
    private static final int DB_Version = 1;
    Context ct;

    Mydb(Context c) {
        super(c, DB_NAME,null,DB_Version);
        ct = c;

    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_Table = "CREATE TABLE myinfo ( " +
                "UserName TEXT, " +
                "UserMobile TEXT, " +
                "UserDate TEXT,"+
                "Problem TEXT)";
        db.execSQL(CREATE_Table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String s = String.format("DELETE TABLE %s IF EXISTS %s);", "myinfo");
        db.execSQL(s);
        onCreate(db);

    }
    public void store(String name,String mobile,String problem,String date)
    {
        SQLiteDatabase sdb = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserName", name);
        cv.put("UserMobile", mobile);
        cv.put("UserDate", date);
        cv.put("Problem", problem);
        sdb.insertWithOnConflict("myinfo", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        Toast.makeText(ct, "Data Saved Successfuly ::" + name, Toast.LENGTH_SHORT).show();
        sdb.close();
    }
    public ArrayList<String> getTaskList() {
        ArrayList<String> tasklist = new ArrayList<>();
        SQLiteDatabase sdb = getReadableDatabase();
        Cursor cr = sdb.query("myinfo", new String[]{"UserName"},null,null,null,null,null);
        while (cr.moveToNext()) {
            int index = cr.getColumnIndex("UserName");

            tasklist.add(cr.getString(index));
        }
        cr.close();
        sdb.close();
        return tasklist;

    }

}
