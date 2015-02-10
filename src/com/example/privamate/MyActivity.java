
package com.example.privamate;

import android.app.ActivityManager;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.SecureRandom;
import java.util.List;

public class MyActivity extends Activity {

    TextView t1;
    String entered = "",out="";
    int cnt = 0, attempt_cnt = 0;
    private MyDBAdapter adapter;
    Cursor cursor1= null;
    String[] res;
    MediaPlayer sound_grant;
    MediaPlayer sound_deny;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screenlock);
        sound_grant = MediaPlayer.create(MyActivity.this,R.raw.grantaccess1);
        sound_deny = MediaPlayer.create(MyActivity.this,R.raw.accessdenied);

        try {
            Intent i =new Intent(this,BackProcess.class);
            startService(i);
        }
        catch(Exception e){}


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), Boolean.FALSE);
        if(!previouslyStarted){
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();
            startActivity(new Intent(this,SetPassword.class));
            this.finish();

        }


    }


    @Override
    protected void onStart() {
        super.onStart();
        TableLayout tb = (TableLayout) findViewById(R.id.table1);
        final WallpaperManager wm = WallpaperManager.getInstance(this);
        final Drawable wd = wm.getDrawable();
        tb.setBackground(wd);
        Display();
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
        adapter = new MyDBAdapter(this);
        adapter.open();
        cursor1=adapter.getPassword();
        String[] res=adapter.fromCursorToStringArray1(cursor1);
        adapter.close();

        if (entered.equals(res[0])) {
            attempt_cnt = 0;
            sound_grant.start();
            Intent intent = new Intent(this, Showapplist.class);
            startActivity(intent);
            this.finish();

        } else {
            attempt_cnt++;
            sound_deny.start();
            Toast.makeText(this, "Password is incorrect" + (5 - attempt_cnt), Toast.LENGTH_SHORT).show();
            if (attempt_cnt > 4) {
                attempt_cnt = 0;
                Toast.makeText(this, "Redirect to email authentication", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Email.class);
                startActivity(intent);
            }
        }
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