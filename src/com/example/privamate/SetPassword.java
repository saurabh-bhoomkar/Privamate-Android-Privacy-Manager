package com.example.privamate;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by prasad on 11/20/14.
 */
public class SetPassword extends Activity {

    String entered,entered1,out;
    String s_pass,u_pass;
    int cnt;
    int screen;
    MyDBAdapter adapter;
    TextView d1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpassword);


        entered=entered1=out=s_pass=u_pass="";
        cnt=screen=0;

        TableLayout tb = (TableLayout) findViewById(R.id.table1);
        final WallpaperManager wm = WallpaperManager.getInstance(this);
        final Drawable wd = wm.getDrawable();
        tb.setBackground(wd);


        d1= (TextView) findViewById(R.id.txtDisplay);
        d1.setText("Enter SU password\n");

    }

    @Override
    public void onBackPressed() {

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void clickIt1(View view) {
        entered = entered + "1";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt2(View view) {
        entered = entered + "2";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt3(View view) {
        entered = entered + "3";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt4(View view) {
        entered = entered + "4";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt5(View view) {
        entered = entered + "5";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt6(View view) {
        entered = entered + "6";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt7(View view) {
        entered = entered + "7";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt8(View view) {
        entered = entered + "8";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt9(View view) {
        entered = entered + "9";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickIt0(View view) {
        entered = entered + "9";
        cnt++;
        Display();
        if (cnt == 4)
            clickItOk(view);
    }

    public void clickItOk(View view) {


        if(screen==0) {
            entered1=entered;
            entered="";
            Display();
            d1.setText("Confirm SU Password\n");
            screen++;
            cnt=0;
        }
        else if(screen==1)
        {
            if(entered.equals(entered1))
            {
               /* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString(getString(R.string.su_password), entered);
                edit.commit();
                Toast.makeText(this,getResources().getString(R.string.su_password),Toast.LENGTH_LONG).show();
                Log.i("-----New Pass---------", getResources().getString(R.string.su_password));
                //Intent intent = new Intent(this, Showapplist.class);
               // startActivity(intent);*/
                s_pass=entered;
                entered=entered1="";
                cnt=0;
               // Display();
                screen++;

                d1.setText("Enter User Password\n");
            }
            else
            {
                screen=0;
                Display();
                cnt=0;
                entered=entered1="";
                d1.setText("Enter Su Password\n");
                Toast.makeText(this,"Password did not match. Please re-enter",Toast.LENGTH_LONG).show();
            }
        }
        else if(screen==2)
        {
            entered1=entered;
            entered="";
            //Display();
            d1.setText("Confirm User Password\n");
            screen++;
            cnt=0;
        }
        else if(screen==3)
        {
            if(entered.equals(entered1))
            {
                u_pass=entered;
                adapter = new MyDBAdapter(this);
                adapter.open();
                adapter.insertPassword(s_pass,u_pass);
                adapter.close();
                entered=entered1="";
                cnt=0;
            //    Display();

                Toast.makeText(this,s_pass+" "+u_pass,Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, Showapplist.class));
                this.finish();
            }
            else
            {
                Display();
                screen=2;
                cnt=0;
                entered=entered1="";
                d1.setText("Enter User Password\n");
                Toast.makeText(this,"Password did not match. Please re-enter",Toast.LENGTH_LONG).show();
            }


        }

    }

//
//    public void clickItx(View view) {
//        if (cnt > 0) {
//
//            cnt--;
//            String temp = entered.substring(0, cnt);
//            entered = temp;
//
//        }
//        Display();
//
//    }
//
//
//    public void clickItDel(View view) {
//        entered = "";
//        cnt = 0;
//        Display();
//    }

    public void Display() {

        out=entered;

       // TextView d2 = (TextView) findViewById(R.id.txtDisplay);
        d1.setText(d1.getText().subSequence(0,d1.getText().length()-(cnt-1))+out);

    }


}
