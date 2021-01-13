package x.android.content.pm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.util.List;

    public class PackageManager_Activity extends AppCompatActivity {

    private static final String TAG = PackageManager_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_manager_);
    }

    public void queryIntentActivities(View view) {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
//        mainIntent = new Intent("android.intent.action.MAIN_P");
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, PackageManager.MATCH_ALL);

        // on emuator 11 can not get permission act.
        Log.d(TAG, "list:" + list);
    }
}