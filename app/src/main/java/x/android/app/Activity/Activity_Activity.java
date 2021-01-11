package x.android.app.Activity;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.Utils;
import com.bysong.android.apidemo.R;

import java.io.File;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.installNrfLogger == item.getItemId()) {
            installNrfLogger(null);
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresPermission("android.permission.REQUEST_INSTALL_PACKAGES")
    public void installNrfLogger(View view){
        String assetFilePath = "apk/nRFLogger.apk";
        String destFilePath = new File(Utils.getApp().getExternalCacheDir(), "app.apk").toString();
        ResourceUtils.copyFileFromAssets(assetFilePath, destFilePath);
        AppUtils.installApp(destFilePath);
    }
}