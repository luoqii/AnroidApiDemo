package x.android.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.lang.reflect.Method;

public class BluetoothGatt_Activity extends Activity {

    private static final String TAG = BluetoothGatt_Activity.class.getSimpleName();
    private EditText mMacEV;
    private BluetoothGatt mGatt;
    private String mDeviceMac;
    private BluetoothGattCallback mGattCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_gatt);

        mMacEV = findViewById(R.id.mac);
        mMacEV.setText("54:0E:2D:0F:6A:D6");
    }

    public void connect(View view) {
        mGatt.connect();
    }

    public void open(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        mGattCallback = new BluetoothGattCallback() {
            @Override
            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                super.onConnectionStateChange(gatt, status, newState);
                Log.d(TAG, "onConnectionStateChange. status:" + status + " newState:" + newState);
            }

            @Override
            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                super.onServicesDiscovered(gatt, status);

                Log.d(TAG, "onServicesDiscovered status:" + status);
            }
        };
        mGatt = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac).connectGatt(this,
                false, mGattCallback);
    }

    public void disconnect(View view) {
        mGatt.disconnect();
    }

    public void close(View view) {
        mGatt.close();
    }

    public void discovery_service(View view) {
        mGatt.discoverServices();
    }

    public void bind(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        createBond(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac));
    }

    //android.permission.BLUETOOTH_PRIVILEGED
    // system app only
    public void unbind(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        removeBond(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac));
    }

    public static boolean createBond(BluetoothDevice device, int transport) {
        boolean result = false;
        try {
            Method localMethod = device.getClass().getMethod("createBond", new Class[]{int.class});
            if (localMethod != null) {
                return (Boolean) localMethod.invoke(device, new Object[]{transport});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean createBond(BluetoothDevice device) {
        boolean result = false;
        try {
            Method localMethod = device.getClass().getMethod("createBond", new Class[]{});
            if (localMethod != null) {
                return (Boolean) localMethod.invoke(device, new Object[]{});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean removeBond(BluetoothDevice device) {
        boolean result = false;
        try {
            Method localMethod = device.getClass().getMethod("removeBond", new Class[]{});
            if (localMethod != null) {
                return (Boolean) localMethod.invoke(device, new Object[]{});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}