package com.example.privamate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Agrawal Bhushan on 22/11/14.
 */
public class Email extends Activity {

    TextView email_view ;
    String emailID = "";
    EditText email_txtbx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        email_view = (TextView)findViewById(R.id.textView1);
        email_txtbx = (EditText)findViewById(R.id.editText1);
        //setupActionBar();
        //send_email();
        //email_txtbx.setText("");
    }


    private String[] fromCursorToStringArray(Cursor c){
        String[] result = new String[c.getCount()];
        c.moveToFirst();
        for(int i = 0; i < c.getCount(); i++){
            String row = c.getString(1);//     (c.getColumnIndex(ReminderProvider.COLUMN_BODY));
            //You can here manipulate a single string as you please
            result[i] = row;

            //  Toast.makeText(this, row, Toast.LENGTH_LONG).show();
//
            c.moveToNext();
        }
        return result;
    }

    public void send_email(View view) throws NullPointerException
    {

        MyDBAdapter adapter = null;
        adapter=new MyDBAdapter(this);
        adapter.open();
        Cursor cursor1=adapter.getPassword();
        final String[] res=adapter.fromCursorToStringArray1(cursor1);
        adapter.close();

        String message = "Hello, this is Privamate team. We sent you this email to help you unlock your apps. Your password is : "+res[0];
        email_txtbx.setHint("Please enter the email-address here.");
        emailID = email_txtbx.getText().toString();
        String subject = "Privamate Help";

        String[] recipients = {email_txtbx.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        // prompts email clients only
        email.setType("message/rfc822");

        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Choose an email client from..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Email.this, "No email client installed.", Toast.LENGTH_LONG).show();
        }
        email_txtbx.setText("");
        this.finish();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.apk_list, menu);
        return true;
    }


}