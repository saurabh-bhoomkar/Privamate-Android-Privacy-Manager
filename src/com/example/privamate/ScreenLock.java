package com.example.privamate;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//import java.util.Objects;

/**
 * Created by prasad on 9/26/14.
 */
public class ScreenLock extends Activity {

    TextView t1;
    String entered = "", out = "";
    int cnt = 0, attempt_cnt = 0;
    MediaPlayer sound_grant;
    MediaPlayer sound_deny;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenlock);
        TableLayout tb = (TableLayout) findViewById(R.id.table1);
        final WallpaperManager wm = WallpaperManager.getInstance(this);
        final Drawable wd = wm.getDrawable();
        sound_grant = MediaPlayer.create(ScreenLock.this,R.raw.grantaccess1);
        sound_deny = MediaPlayer.create(ScreenLock.this,R.raw.accessdenied);
        tb.setBackground(wd);

        Display();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
        MyDBAdapter adapter = new MyDBAdapter(this);
        adapter.open();
        Cursor cursor1=adapter.getPassword();
        final String[] res=adapter.fromCursorToStringArray1(cursor1);
        adapter.close();
        Log.i("-----Pass=======------",res[0]+" "+res[1]);

        if ((entered.equals(res[0]))||(entered.equals(res[1]))) {
            attempt_cnt = 0;
            sound_grant.start();
            Toast.makeText(this, "Unlocked", Toast.LENGTH_SHORT).show();
            try {

                if (!entered.equals("")) {
                        Intent i11 = new Intent(this, AppDetector.class);
                i11.putExtra("pass", entered);
                startService(i11);
                }
            }
            catch(Exception e){

                Log.i("----Exceptions--------------",""+e);
            }


            finish();

        } else {

            attempt_cnt++;
            entered="";
            sound_deny.start();
            Toast.makeText(this, "Password is incorrect" + (5 - attempt_cnt), Toast.LENGTH_SHORT).show();
            if (attempt_cnt > 4) {
                attempt_cnt = 0;
                Toast.makeText(this, "Redirect to email authentication", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Email.class);
                startActivity(intent);
                this.finish();
            }

        }
        Display();

        entered = "";
        cnt = 0;
        Display();

    }


    public void clickItx(View view) {
        if (cnt > 0) {
            cnt--;
            String temp = entered.substring(0, cnt);
            entered = temp;

        }
        Display();
    }


    public void clickItDel(View view) {
        entered = "";
        cnt = 0;
        Display();
    }

    public void Display() {


        switch (cnt) {
            case 0:
                out = "";
                break;

            case 1:
                out = "*";
                break;
            case 2:
                out = "**";
                break;
            case 3:
                out = "***";
                break;
            case 4:
                out = "****";
                break;
            default:
                break;

        }
        TextView d1 = (TextView) findViewById(R.id.txtDisplay);
        d1.setText(out);
    }
}