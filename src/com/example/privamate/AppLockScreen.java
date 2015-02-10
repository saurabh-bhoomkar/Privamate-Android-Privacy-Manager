package com.example.privamate;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by prasad on 11/19/14.
 */
public class AppLockScreen extends Activity {

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
        tb.setBackground(wd);
        sound_grant = MediaPlayer.create(AppLockScreen.this,R.raw.grantaccess1);
        sound_deny = MediaPlayer.create(AppLockScreen.this,R.raw.accessdenied);
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
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        this.finish();
       // Log.i("-------------home bhava-------------", home);



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

        if ((entered.equals(res[0]))) {
            attempt_cnt = 0;
            sound_grant.start();
            //Toast.makeText(this, "Unlocked", Toast.LENGTH_SHORT).show();
            finish();

        }
        else {
            attempt_cnt++;
            Toast.makeText(this, "Password is incorrect" + (5 - attempt_cnt), Toast.LENGTH_SHORT).show();
            sound_deny.start();
            if (attempt_cnt > 4) {
                attempt_cnt = 0;
                Toast.makeText(this, "Redirect to email authentication", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,Email.class);
                startActivity(intent);
                this.finish();
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






