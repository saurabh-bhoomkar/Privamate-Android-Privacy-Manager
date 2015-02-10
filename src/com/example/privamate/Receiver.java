package com.example.privamate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by prasad on 9/24/14.
 */
public class Receiver extends BroadcastReceiver {

    public static boolean wasScreenOn = true;
    //   public static boolean start=true;

    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            wasScreenOn = false;
            Intent intent11 = new Intent(context, ScreenLock.class);
            intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //  intent11.putExtra("start",start);
            intent11.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent11);


        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {

            wasScreenOn = true;
            Intent intent11 = new Intent(context, ScreenLock.class);
            intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent11.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        } else if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            Intent intent11 = new Intent(context, ScreenLock.class);

            intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent11.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
            context.startActivity(intent11);


        }

    }

}