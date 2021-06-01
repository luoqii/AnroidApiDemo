package x.android.bluetooth.le;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.commonlib.BtUtils;
import org.bbs.android.log.Log;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class BluetoothLeScanner_Activity extends AppCompatActivity {

    private static final String TAG = BluetoothLeScanner_Activity.class.getSimpleName();
    private BluetoothLeScanner mBleScanner;
    private ScanCallback mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_le_scanner);

        mBleScanner = BluetoothAdapter.getDefaultAdapter().getBluetoothLeScanner();
        mCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);

                Log.d(TAG, "onScanResult. callbackType:" + callbackType + " result:" + result);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);

                Log.d(TAG, "onBatchScanResults: results:" + results);
            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);

                Log.w(TAG, "onScanFailed. errorCode:" + BtUtils.toScanFailedReasonString(errorCode));
            }
        };
    }

    public void startLeScan(View view) {
        Log.d(TAG, "startScan mCallback:" + mCallback);
        mBleScanner.startScan(mCallback);
    }

    public void stopLeScan(View view) {
        Log.d(TAG, "stopLeScan mCallback:" + mCallback);
        mBleScanner.stopScan(mCallback);
    }
}