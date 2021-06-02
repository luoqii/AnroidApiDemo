package x.android.app.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Activity extends AppCompatActivity {


    private static final String TAG = Activity_Activity.class.getSimpleName();
    static String[] sPermissionArray;
    boolean[] mpermissionChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void requestPermissions(View view) {
        mpermissionChecked = new boolean[sPermissionArray.length];
        createPermissionDialog().show();
    }

    //https://developer.android.com/guide/topics/ui/dialogs#java
    @RequiresApi(api = Build.VERSION_CODES.M)
    public Dialog createPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        String[] uiPermissionArray = new String[sPermissionArray.length];
        for (int i = 0; i < sPermissionArray.length; i++) {
            String p = sPermissionArray[i].substring(sPermissionArray[i].lastIndexOf(".") + 1);
            if (!shouldShowRequestPermissionRationale(sPermissionArray[i])) {
                p += " +";
            }
            uiPermissionArray[i] = p;
        }
        builder.setTitle("permission")
                .setMultiChoiceItems(uiPermissionArray, mpermissionChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        mpermissionChecked[which] = isChecked;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List<String> checked = new ArrayList<>();
                        for (int i = 0; i < mpermissionChecked.length ; i++) {
                            if (mpermissionChecked[i]){
                                String p = sPermissionArray[i];
                                checked.add(p);
                            }
                        }

                        String[] checkedPermission = new String[checked.size()];
                        checked.toArray(checkedPermission);

                        Activity_Activity.this.requestPermissions(checkedPermission, 0);
                    }
                })
                ;
        return builder.create();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult requestCode:" + requestCode
                    + " permissions:" + Arrays.toString(permissions)
                    + " grantResults:" + Arrays.toString(grantResults));
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    static {
        sPermissionArray = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
        };
    }
}