package x.android.app.Activity;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.Utils;
import com.bysong.android.apidemo.R;

import java.io.File;

public class Install_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.install_activity);
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
        } else if (R.id.installNrfConnect == item.getItemId()) {
            installNrfConnect(null);
        }
        return super.onOptionsItemSelected(item);
    }

    public void installNrfConnect(View view) {
        String assetFilePath = "apk/nRFConnect.apk";
        File destFile = new File(Utils.getApp().getExternalCacheDir(), "app.apk");
        destFile.delete();
        ResourceUtils.copyFileFromAssets(assetFilePath, destFile.toString());
        AppUtils.installApp(destFile.toString());
    }

    public void installNrfLogger(View view){
        String assetFilePath = "apk/nRFLogger.apk";
        File destFile = new File(Utils.getApp().getExternalCacheDir(), "app.apk");
        destFile.delete();
        ResourceUtils.copyFileFromAssets(assetFilePath, destFile.toString());
        AppUtils.installApp(destFile.toString());
    }
}