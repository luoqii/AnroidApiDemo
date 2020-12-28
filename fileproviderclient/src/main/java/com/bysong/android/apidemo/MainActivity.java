package com.bysong.android.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readProperties();
    }

    void readProperties(){
        Uri uri = Uri.parse("content://com.bysong.android.apidemo.fileprovider/properties/properties");
        try {
            InputStream is = getContentResolver().openInputStream(uri);
            Properties p = new Properties();
            p.load(is);
            String property = p.getProperty("property1");
            Log.d(TAG, "property:" + property);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}