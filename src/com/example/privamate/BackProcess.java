package com.example.privamate;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.*;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

/**
 * Created by prasad on 9/24/14.
 */
public class BackProcess extends Service{

    BroadcastReceiver br,ft;
    private WindowManager windowManager;
    private View v1;
    Intent in;
    ActivityManager am;
    Boolean flag=false;
    String pass;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override


    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        br = new Receiver();
        registerReceiver(br, filter);

        }




    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }

}