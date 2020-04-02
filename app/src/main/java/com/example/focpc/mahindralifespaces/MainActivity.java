package com.example.focpc.mahindralifespaces;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.focpc.mahindralifespaces.ui.activities.referal_section.ReferSectionActivity;
import com.example.focpc.mahindralifespaces.ui.activities.select_contact.ContactFetcherAsync;
import com.example.focpc.mahindralifespaces.utils.MlsConstants;
import com.example.focpc.mahindralifespaces.utils.MlsUtils;
import com.mlife.R;

public class MainActivity extends AppCompatActivity {
    private Button enterBtn;
    private EditText mobileET,nameET,emailET;
    private boolean isLoogedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContacts();
        this.finish();
    }

    private void getContacts(){
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, MlsConstants.GET_CONTACTS_FLAG);
            Toast.makeText(this, "Please allow the premision's", Toast.LENGTH_SHORT).show();
        } else {
            new ContactFetcherAsync(this,null).execute();
            Intent intent = new Intent(this, ReferSectionActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MlsConstants.GET_CONTACTS_FLAG){
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContacts();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}