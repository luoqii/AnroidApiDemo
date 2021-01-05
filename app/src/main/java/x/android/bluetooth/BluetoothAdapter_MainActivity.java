package x.android.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

public class BluetoothAdapter_MainActivity extends AppCompatActivity {
    private static final String TAG = "BluetoothAdapter_MainActivity";

    private BluetoothAdapter mBtAdapter ;
    private BluetoothAdapter.LeScanCallback mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_adapter__main);

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        mCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Log.d(TAG, "onLeScan. rssi:" + rssi + " scanRecord:" + scanRecord);
            }
        };
    }

    public void startLeScan(View view) {
        mBtAdapter.startLeScan(mCallback);
    }

    public void stopLeScan(View view) {
        mBtAdapter.stopLeScan(mCallback);
    }
}