package com.soepaing.tmkconverter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Patterns;

public class Option extends PreferenceActivity {


    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.option_layout);

        findPreference(this.getString(R.string.about)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                getAboutAlert();
                return true;
            }
        });
        findPreference(this.getString(R.string.contact)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                getFeedback();
                return true;
            }
        });

    }

    private StringBuffer getAboutString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Developed by : Soe Paing");
        stringBuffer.append("\n\nPhone : 09799729700");
        stringBuffer.append("\n\nEmail : soepaingmohnyin.mm@gmail.com");
        return stringBuffer;
    }

    private void getAboutAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("TMK Converter");
        builder.setMessage(getAboutString());
        builder.setCancelable(false);
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private boolean isEmailValid(CharSequence charSequence) {
        return Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    private void getFeedback() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"soepaingmonyin.mm@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "TMK Converter v1.0");
        intent.putExtra(Intent.EXTRA_TEXT, "Dear Soe Paing,\n");
        intent.setType("text/plain");
        startActivity(intent);
    }

}
