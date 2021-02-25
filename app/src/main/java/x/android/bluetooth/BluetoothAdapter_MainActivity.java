package x.android.bluetooth;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.util.List;
import java.util.Set;

public class BluetoothAdapter_MainActivity extends AppCompatActivity {
    private static final String TAG = "BluetoothAdapter_MainActivity";
    private static final boolean DEBUG_LOCAL = true;

    private BluetoothAdapter mBtAdapter ;
    private BluetoothAdapter.LeScanCallback mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_adapter__main);

        dumpBluetoothState(this);

        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        mCallback = new BluetoothAdapter.LeScanCallback() {
            @Override
            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                Log.d(TAG, "onLeScan. rssi:" + rssi + " scanRecord:" + scanRecord);
            }
        };
    }

    public static void  dumpBluetoothState(Context context) {
        BluetoothManager bm = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        List<BluetoothDevice> ds = bm.getDevicesMatchingConnectionStates(BluetoothProfile.GATT, new int[BluetoothProfile.STATE_CONNECTED]);
        dump("gatt device in connected state    :", ds);
        ds = bm.getDevicesMatchingConnectionStates(BluetoothProfile.GATT, new int[BluetoothProfile.STATE_CONNECTING]);
        dump("gatt device in connecting state   :", ds);
        ds = bm.getDevicesMatchingConnectionStates(BluetoothProfile.GATT, new int[BluetoothProfile.STATE_DISCONNECTED]);
        dump("gatt device in disconnected state :", ds);
        ds = bm.getDevicesMatchingConnectionStates(BluetoothProfile.GATT, new int[BluetoothProfile.STATE_DISCONNECTING]);
        dump("gatt device in disconnecting state:", ds);

        Set<BluetoothDevice> boundDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
        for (BluetoothDevice d : boundDevices){
            if (DEBUG_LOCAL) {
                Log.d(TAG, "bound device:" + d.getAddress());
            }
        }
        if (DEBUG_LOCAL && null != boundDevices && boundDevices.size() == 0){
            Log.d(TAG, "no bound device");
        }
    }

    public static void dump(String header, List<BluetoothDevice> devices){
        String message = header;
        if (null == devices || devices.size() == 0) {
            message += "empty";
            Log.d(TAG, message);
        } else {
            for (BluetoothDevice d : devices) {
                message += "\n    mac:" + d.getAddress();
            }
            Log.w(TAG, message);
        }
    }

    public void startLeScan(View view) {
        mBtAdapter.startLeScan(mCallback);
    }

    public void stopLeScan(View view) {
        mBtAdapter.stopLeScan(mCallback);
    }
}