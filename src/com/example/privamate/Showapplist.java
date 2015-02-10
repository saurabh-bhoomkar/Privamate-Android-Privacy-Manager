package com.example.privamate;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;
import com.example.appslist.adapter.ApkAdapter;
import java.lang.*;


public class Showapplist extends Activity implements AdapterView.OnItemClickListener {
    PackageManager packageManager;
    ListView apkList;
    private MyDBAdapter adapter;

    public List<String> myList;
    public List<Integer> idList;

    private ListView langView;
    private ListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apklist);

        idList=new ArrayList<Integer>();
        myList=new ArrayList<String>();
        adapter=new MyDBAdapter(this);

        populateData();

        packageManager = getPackageManager();
        List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();

        /*To filter out System apps*/
        for (PackageInfo pi : packageList)
        {
            boolean b = isSystemPackage(pi);
            if (!b) {
                packageList1.add(pi);
            }
        }
        apkList = (ListView) findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(this, packageList1, packageManager));
        apkList.setOnItemClickListener(this);
    }

    ////////////// new code //////////////

    public void adminOptions(View view)
    {
        Intent intent = new Intent(this,AdminTasks.class);
        startActivity(intent);
    }

///*
    private void populateData() {
//        try
//        {
//            idList.clear();
//            myList.clear();
//        }
//        catch(Exception e3)
//        {
//            Toast.makeText(Showapplist.this, e3.getMessage() , Toast.LENGTH_LONG).show();
//        }
    //    Toast.makeText(Showapplist.this, "trying", Toast.LENGTH_LONG).show();

        //try
        //{
            adapter.open();
   //         Toast.makeText(Showapplist.this, "in adapter", Toast.LENGTH_LONG).show();
            Cursor cursor= null  ;
            //try
            //{
                //cursor=adapter.getAllEntries();
            //}
//            catch (Exception e4)
//            {
//                Toast.makeText(Showapplist.this, "trying", Toast.LENGTH_LONG).show();
//            }
            //cursor.moveToFirst();
//            for(int i=0;i<cursor.getCount();i++)
//            {
//                idList.add(cursor.getInt(0));
//                myList.add(cursor.getString(1));
//                cursor.moveToNext();
//            }
            adapter.close();
        //}
//        catch (Exception e1)
//        {
//            String msg=e1.getMessage();
//            Toast.makeText(this,msg,Toast.LENGTH_LONG);
//        }
        //listAdapter=new ArrayAdapter<String>(Showapplist.this,android.R.layout.simple_expandable_list_item_1,myList);
        //angView.setAdapter(listAdapter);
        //apkList.setAdapter(listAdapter);
    }

//*/
  /**
     * Set up the {@link android.app.ActionBar}, if the API is available.

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }*/

    /**
     * Return whether the given PackgeInfo represents a system package or not.
     * User-installed packages (Market or otherwise) should not be denoted as
     * system packages.
     *
     * @param pkgInfo
     * @return boolean
     */
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, final int index, long arg3) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Lock");
        dialog.setMessage("Are you sure?");
        final View arg2=arg1;
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Showapplist.this, "Locked", Toast.LENGTH_LONG).show();
                try
                {
                    String app = apkList.getOnItemClickListener().toString();
                    String aa=apkList.getItemAtPosition(index).toString();
                    String[] aa1=aa.split(" ");
                    String aa2=aa1[1];
                    aa2=aa2.substring(0,aa2.length()-1);
        //            Toast toast=Toast.makeText(getApplicationContext(),aa2, Toast.LENGTH_SHORT);
          //          toast.show();
                    adapter.open();
                    adapter.insertEntry(aa2);
                    adapter.close();
      //              Toast.makeText(Showapplist.this, "Trying..", Toast.LENGTH_LONG).show();

//                    if(result!=0)
//                    {
//                        Toast.makeText(Showapplist.this,entry+" inserted",Toast.LENGTH_LONG);
//                    }
//                    else
//                    {
//                        Toast.makeText(Showapplist.this,"Chutya",Toast.LENGTH_LONG).show();
//                    }
                }
                catch (Exception e)
                {
                    String error = e.getMessage();
      //              Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
                }
        //        Toast.makeText(Showapplist.this, "Exiting..", Toast.LENGTH_LONG).show();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.apk_list, menu);
        return true;
    }



    @Override
    protected void onResume() {
        super.onResume();
        populateData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.close();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.close();
    }

}