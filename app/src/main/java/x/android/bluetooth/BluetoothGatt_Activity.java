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

import static android.bluetooth.BluetoothDevice.PHY_LE_1M_MASK;
import static android.bluetooth.BluetoothDevice.PHY_LE_2M_MASK;
import static android.bluetooth.BluetoothDevice.PHY_LE_CODED;
import static android.bluetooth.BluetoothDevice.PHY_LE_CODED_MASK;
import static android.bluetooth.BluetoothDevice.TRANSPORT_AUTO;
import static android.bluetooth.BluetoothDevice.TRANSPORT_BREDR;
import static android.bluetooth.BluetoothDevice.TRANSPORT_LE;

public class BluetoothGatt_Activity extends Activity {

    private static final String TAG = BluetoothGatt_Activity.class.getSimpleName();

    private EditText mMacEV;
    private BluetoothGatt mGatt;
    private String mDeviceMac;
    private BluetoothGattCallback mGattCallback;
    private int mPhy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_gatt);

        mMacEV = findViewById(R.id.mac);
        mMacEV.setText("54:0E:2D:0F:6A:D6");
        mMacEV.setText("18:E7:77:00:05:66");
    }

    public void connect(View view) {
        mGatt.connect();
    }


    public void connect_false(View view) {
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

    public void connect_true(View view) {
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
                true, mGattCallback);
    }

    public void connect_true_auto(View view) {
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
                true, mGattCallback, TRANSPORT_AUTO);
    }

    public void connect_true_le(View view) {
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
                true, mGattCallback, TRANSPORT_LE);
    }

    public void connect_true_bredr(View view) {
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
                true, mGattCallback, BluetoothDevice.TRANSPORT_BREDR);
    }

    public void connect_auto_phy(View view) {
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
                true, mGattCallback, TRANSPORT_AUTO, mPhy);
    }

    public void connect_le_phy(View view) {
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
                true, mGattCallback, TRANSPORT_LE, mPhy);
    }

    public void connect_bredr_phy(View view) {
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
                true, mGattCallback, TRANSPORT_BREDR, mPhy);
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

    public void clear_phy(View view) {
        mPhy = 0;
    }

    public void phy_1m(View view) {
        mPhy |= PHY_LE_1M_MASK;
    }

    public void phy_2m(View view) {
        mPhy |= PHY_LE_2M_MASK;
    }

    public void phy_coded(View view) {
        mPhy |= PHY_LE_CODED_MASK;
    }
}