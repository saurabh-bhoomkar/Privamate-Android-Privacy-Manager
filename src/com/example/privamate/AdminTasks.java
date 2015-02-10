package com.example.privamate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by sony on 21/11/14.
 */
/*
public class AdminTasks extends Activity implements  {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_buttons);
    }
}*/
//////////////////////////



public class AdminTasks extends Activity implements
        CompoundButton.OnCheckedChangeListener {
    static final String TAG = "DevicePolicyDemoActivity";
    static final int ACTIVATION_REQUEST = 47; // identifies our request id
    DevicePolicyManager devicePolicyManager;
    ComponentName demoDeviceAdmin;
    ToggleButton toggleButton;
    Button disabler;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Toast.makeText(this,"ssss",Toast.LENGTH_LONG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_buttons);
        setupActionBar();

        toggleButton = (ToggleButton) super
                .findViewById(R.id.toggle_device_admin);
        toggleButton.setOnCheckedChangeListener(this);
        disabler = (Button)findViewById(R.id.button);


        // Initialize Device Policy Manager service and our receiver class
        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        demoDeviceAdmin = new ComponentName(this, DemoDeviceAdminReceiver.class);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    public void clickreset(View view){

        MyDBAdapter adapter = new MyDBAdapter(this);
        adapter.open();
        adapter.removeEntry();
        adapter.close();
        startActivity(new Intent(this, SetPassword.class));
        this.finish();

    }



    /**
     * Called when a button is clicked on. We have Lock Device and Reset Device
     * buttons that could invoke this method.

     public void onClick(View v) {
     switch (v.getId()) {
     case R.id.button_lock_device:
     // We lock the screen
     Toast.makeText(this, "Locking device...", Toast.LENGTH_LONG).show();
     Log.d(TAG, "Locking device now");
     devicePolicyManager.lockNow();
     break;
     case R.id.button_reset_device:
     // We reset the device - this will erase entire /data partition!
     Toast.makeText(this, "Locking device...", Toast.LENGTH_LONG).show();
     Log.d(TAG,
     "RESETing device now - all user data will be ERASED to factory settings");
     devicePolicyManager.wipeData(ACTIVATION_REQUEST);
     break;
     }
     }*/

    /**
     * Called when the state of toggle button changes. In this case, we send an
     * intent to activate the device policy administration.
     */
    @Override
    public void onCheckedChanged(CompoundButton button, boolean isChecked) {
        if (isChecked) {
            // Activate device administration
            Intent intent = new Intent(
                    DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                    demoDeviceAdmin);
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                    "Your boss told you to do this");
            startActivityForResult(intent, ACTIVATION_REQUEST);
            //
            disabler.setVisibility(View.VISIBLE);
            toggleButton.setVisibility(View.INVISIBLE);


        }
        else
        {
            ;
        }
        Log.d(TAG, "onCheckedChanged to: " + isChecked);
    }

    public void ssn(View view)
    {
        ComponentName devAdminReceiver = new ComponentName(this, DemoDeviceAdminReceiver.class);
        DevicePolicyManager mDPM = (DevicePolicyManager)getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDPM.removeActiveAdmin(devAdminReceiver);
        disabler.setVisibility(View.INVISIBLE);
        toggleButton.setVisibility(View.VISIBLE);
    }

    /**
     * Called when startActivityForResult() call is completed. The result of
     * activation could be success of failure, mostly depending on user okaying
     * this app's request to administer the device.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVATION_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i(TAG, "Administration enabled!");
                    toggleButton.setChecked(true);
                } else {
                    Log.i(TAG, "Administration enable FAILED!");
                    toggleButton.setChecked(false);
                }
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}