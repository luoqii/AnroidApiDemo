package x.android.provider.Settings;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.text.NoCopySpan;

import org.bbs.android.log.Log;

public class Setting_Activity extends Activity {
    private static final String KEY = "Setting_Activity_KEY";
    private static final String TAG = Setting_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        put2Setting();

        getFromSetting();
    }

    // require android.permission.WRITE_SETTINGS
    void put2Setting(){
        boolean success = Settings.System.putInt(getContentResolver(), KEY, 1);
        Log.d(TAG, "success:" + success);
    }

    // require android.permission.WRITE_SETTINGS
    void getFromSetting() {
        try {
            int v = Settings.System.getInt(getContentResolver(), KEY);
            Log.d(TAG, "v:" + v);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }
}
