package x.android.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.bysong.android.apidemo.R;

import org.bbs.android.commonlib.BtUtils;
import org.bbs.android.log.Log;

import java.lang.reflect.Method;

public class BluetoothDevice_Activity extends Activity {
    private static final String TAG = BluetoothDevice_Activity.class.getSimpleName();

    private EditText mMacEV;
    private String mDeviceMac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_device);

        mMacEV = findViewById(R.id.mac);
        mMacEV.setText("54:0E:2D:0F:6A:D6");
        mMacEV.setText("18:E7:77:00:05:66");
    }

    public void getBondState(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac);
        int state =  d.getBondState();
        Log.d(TAG, "state:" + BtUtils.toBluetoothDeviceBondStateString(state));
    }


    public void removebound(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac);
        Log.d(TAG, "removebound:" + removeBond(d));
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
            Method localMethod = device.getClass().getMethod("removeBond");
            if (localMethod != null) {
                return (Boolean) localMethod.invoke(device, new Object[]{});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}