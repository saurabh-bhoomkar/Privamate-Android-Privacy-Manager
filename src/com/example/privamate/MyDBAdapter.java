package com.example.privamate;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MyDBAdapter {

    private static final String DB_NAME="Privamate_DATABASE";
    private static final String DB_TABLE="locked_apps";
    private static final int DB_VERSION=2;
    private static final String DB_TABLE1="user_password";

    private static final String COL_ID="_id";

    private static final String COL_delete="delete from user_password";
    private static final String COL_LANG_NAME="PackageName";
    private static final String COL_PASS="password";
    private static final String COL_PASS1="guest_password";


    /*
        //create table language (_id integer primary key autoincrement , langName text not null);
        private static final String DB_CREATE="create table "+DB_TABLE+" ("+KEY_ID+" integer primary key "+
                "autoincrement , "+COLMN_NAME+" text not null);";
    */
    private static final String DB_CREATE="create table locked_apps (_id integer primary key autoincrement , PackageName text not null unique);";
    private static final String DB_INSERT="insert into locked_apps values('1','Privamate');";
    private static final String DB_CREATE1="create table user_password (password text,guest_password text);";
    //private static final String DB_INSERT1="insert into user_password values('1','1234','4321');";

    private SQLiteDatabase  database;
    private MyDBHelper helper;

    public MyDBAdapter(Context context){
        helper=new MyDBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public MyDBAdapter open(){
        database=helper.getWritableDatabase();
        return this;
    }

    public Cursor getAllEntries() {
        // select _id, langName from language;
        return database.query(DB_TABLE, new String[]{COL_ID,COL_LANG_NAME}, null, null, null, null, null);
    }
    public Cursor getPassword() {
        // select _id, langName from language;
        return database.query(DB_TABLE1, new String[]{COL_PASS,COL_PASS1}, null, null, null, null, null);
    }


    public Cursor getAllEntries2() {
        // select _id, langName from language;
        return database.query(DB_TABLE, new String[]{COL_ID,COL_LANG_NAME}, null, null, null, null, null);
    }
    public void close() {
        database.close();
    }

    public long insertEntry(String PackageName) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_LANG_NAME, PackageName);
        //contentValues.put(column name, column value);
        return database.insert(DB_TABLE, null, contentValues);
    }
    public long insertPassword(String pass,String pass1) {
        ContentValues contentValues1=new ContentValues();
        //ContentValues contentValues2=new ContentValues();

        contentValues1.put(COL_PASS, pass);
        contentValues1.put(COL_PASS1, pass1);

        //contentValues.put(column name, column value);
       // return database.insert(DB_TABLE1,null,contentValues1);
        return database.insert(DB_TABLE1,null,contentValues1);

    }

    String[] fromCursorToStringArray1(Cursor c){
        String[] result = new String[2];
        c.moveToFirst();
            String spass = c.getString(0);//     (c.getColumnIndex(ReminderProvider.COLUMN_BODY));
            //You can here manipulate a single string as you please
            String upass1=c.getString(1);
          //  Log.i("---------==============-------------",spass+" "+upass1);
            result[0]=spass;
            result[1]=upass1;

            //   Toast.makeText(this, pass+"    "+pass1, Toast.LENGTH_LONG).show();
//
        return result;
    }

    public void removeEntry() {
        //delete from language where _id = rowIndex;
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(COL_delete);
    }

    public int updateEntry(long rowIndex,String newLangName) {
        ContentValues updateValues=new ContentValues();
        updateValues.put(COL_LANG_NAME, newLangName);
        //update language set langName=newLangName where _id=rowIndex
        return database.update(DB_TABLE, updateValues, COL_ID+" = "+rowIndex, null);
    }

    private static class MyDBHelper extends SQLiteOpenHelper
    {
        public MyDBHelper(Context context,String name,CursorFactory cursorFactory, int version){
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
            db.execSQL(DB_CREATE1);
            db.execSQL(DB_INSERT);
    //        db.execSQL(DB_INSERT1);

            //Toast.makeText(MyDBAdapter.this, "trying", Toast.LENGTH_LONG).show();
//            List<String> languages=new ArrayList<String>();
//            languages.add("C");
//            languages.add("C++");
//            languages.add("Java");
//            languages.add("Perl");
//
//            for(String lang: languages){
//                ContentValues contentValues=new ContentValues();
//                contentValues.put(COL_LANG_NAME, lang);
//                //contentValues.put(column name, column value);
//                db.insert(DB_TABLE, null, contentValues);
//            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Updation", "Data base version is being updates");
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
            onCreate(db);
        }
    }
}
