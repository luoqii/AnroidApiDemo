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
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_SAMPLE_CODE);
//        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mainIntent.addFlags(Intent.FLA)

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        Log.d(TAG, "list:" + list);
    }
}