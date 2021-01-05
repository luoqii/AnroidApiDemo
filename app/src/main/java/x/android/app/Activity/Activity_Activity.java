package x.android.app.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import java.security.Permission;

public class Activity_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(View view) {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        requestPermissions(permissions, 0);
    }
}