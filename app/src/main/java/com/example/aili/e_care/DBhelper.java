package com.example.aili.e_care;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ecare_info";
    private static final int DB_Version = 1;



    Context ct;

    DBhelper(Context c) {
        super(c, DB_NAME,null,DB_Version);
        ct = c;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Table = "CREATE TABLE info ( " +
                "UserName TEXT, " +
                "UserAddress TEXT, " +
                "UserMobile TEXT, " +
                "UserDoctorName TEXT,"+
                "UserDate TEXT,"+
                "Medicineinfo TEXT,"+
                "Uid TEXT,"+
                "UserTime TEXT)";
        db.execSQL(CREATE_Table);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String s = String.format("DELETE TABLE %s IF EXISTS %s);", "info");
        db.execSQL(s);
        onCreate(db);


    }

    public void store(String name, String address, String mobile, String doctorname, String date, String list,String uid,String time) {
        SQLiteDatabase sdb = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("UserName", name);
        cv.put("UserAddress", address);
        cv.put("UserMobile", mobile);
        cv.put("UserDoctorName", doctorname);
        cv.put("UserDate", date);
        cv.put("Medicineinfo",list);
        cv.put("Uid",uid);
        cv.put("UserTime", time);


        sdb.insertWithOnConflict("info", null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        Toast.makeText(ct, "Data Saved Successfuly ::" + name, Toast.LENGTH_SHORT).show();
        sdb.close();
    }

    public ArrayList<String> getTaskList() {
        ArrayList<String> tasklist = new ArrayList<>();
        SQLiteDatabase sdb = getReadableDatabase();
        Cursor cr = sdb.query("info", new String[]{"UserName"}, null, null, null, null, null);
        while (cr.moveToNext()) {
            int index = cr.getColumnIndex("UserName");

            tasklist.add(cr.getString(index));
        }
        cr.close();
        sdb.close();
        return tasklist;

    }

    public String showPatientDate(String name) {
        int count=0;
        SQLiteDatabase sr = getReadableDatabase();
        String[] params = new String[]{ name };
        Cursor c = sr.rawQuery("SELECT * FROM info WHERE UserName = ?",
                params);
        String s=null;

        while (c.moveToNext()) {
            count=1;
            s = c.getString(4);
        }

        if(count==0)
        {
            Toast.makeText(ct,"No Record Found",Toast.LENGTH_SHORT).show();
        }
        sr.close();
        c.close();
        return s;


    }

    public String showAddress(String name )
    {
        SQLiteDatabase sr =getReadableDatabase();
        String[] params =new String[]{name};
        Cursor c = sr.rawQuery("select * FROM info WHERE UserName = ?",params);
        String s=null;
        while (c.moveToNext())
        {

            s=c.getString(1);
        }
        sr.close();
        c.close();
        return s;
    }

    public String showDoctorDetail(String name )
    {
        SQLiteDatabase sr =getReadableDatabase();
        String[] params =new String[]{name};
        Cursor c = sr.rawQuery("select * FROM info WHERE UserName = ?",params);
        String s=null;
        while (c.moveToNext())
        {

            s=c.getString(3);
        }
        sr.close();
        c.close();
        return s;
    }

    public String showNumber(String name )
    {
        SQLiteDatabase sr =getReadableDatabase();
        String[] params =new String[]{name};
        Cursor c = sr.rawQuery("select * FROM info WHERE UserName = ?",params);
        String s=null;
        while (c.moveToNext())
        {

            s=c.getString(2);
        }
        sr.close();
        c.close();
        return s;
    }
    public String showuid(String name )
    {
        SQLiteDatabase sr =getReadableDatabase();
        String[] params =new String[]{name};
        Cursor c = sr.rawQuery("select * FROM info WHERE UserName = ?",params);
        String s=null;
        while (c.moveToNext())
        {

            s=c.getString(6);
        }
        sr.close();
        c.close();
        return s;
    }
    public String showmedicine(String name )
    {
        SQLiteDatabase sr =getReadableDatabase();
        String[] params =new String[]{name};
        Cursor c = sr.rawQuery("select * FROM info WHERE UserName = ?",params);
        String s=null;
        while (c.moveToNext())
        {

            s=c.getString(5);
        }
        sr.close();
        c.close();
        return s;
    }

    public void updatedata(String name,String date,String time) {
        SQLiteDatabase sr=this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put("UserDate", date);
        args.put("UserTime", time);

        sr.update("info",args,"UserName= ?",new String[]{name});
        sr.close();
    }
}


