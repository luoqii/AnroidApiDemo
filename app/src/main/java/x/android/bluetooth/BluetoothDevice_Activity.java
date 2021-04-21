package x.android.bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import com.bysong.android.apidemo.R;

import org.bbs.android.commonlib.BtUtils;
import org.bbs.android.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class BluetoothDevice_Activity extends Activity {
    private static final String TAG = BluetoothDevice_Activity.class.getSimpleName();

    private EditText mMacEV;
    private String mDeviceMac;
    private UUID mRfcUuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket mBluetoothSocket;
    private int mPsm = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_device);

        mMacEV = findViewById(R.id.mac);

        String text = "";
        text = "54:0E:2D:0F:6A:D6";
        text = "18:E7:77:00:05:66";
        text = "18:E7:77:00:32:37";
        text = "66:66:11:11:21:24";

        mMacEV.setText(text);
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

    public void createRfcommSocketToServiceRecord(View view) {
        new Thread(){
            @Override
            public void run() {
//                doCreateRfcommSocketToServiceRecord(null);
            }
        }.start();

        doCreateRfcommSocketToServiceRecord(null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void doCreateRfcommSocketToServiceRecord(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac);
        try {
            Log.d(TAG, "before create");
            mBluetoothSocket = d.createRfcommSocketToServiceRecord(mRfcUuid);
            Log.d(TAG, "mBtSocket:" + mBluetoothSocket);
            mBluetoothSocket.connect();
            int maxMaxTransmitPacketSize = mBluetoothSocket.getMaxTransmitPacketSize();
            int maxReceivePacketSize = mBluetoothSocket.getMaxReceivePacketSize();
            Log.d(TAG, "maxMaxTransmitPacketSize:" + maxMaxTransmitPacketSize);
            Log.d(TAG, "maxReceivePacketSize    :" + maxReceivePacketSize);
            Log.d(TAG, "getConnectionType       :" + mBluetoothSocket.getConnectionType());
            InputStream in = mBluetoothSocket.getInputStream();
            OutputStream out = mBluetoothSocket.getOutputStream();

            Log.d(TAG, "in:" + in);
            Log.d(TAG, "out:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void createInsecureRfcommSocketToServiceRecord(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac);
        try {
            Log.d(TAG, "before create");
            mBluetoothSocket = d.createInsecureRfcommSocketToServiceRecord(mRfcUuid);
            Log.d(TAG, "mBtSocket:" + mBluetoothSocket);
            mBluetoothSocket.connect();
            int maxMaxTransmitPacketSize = mBluetoothSocket.getMaxTransmitPacketSize();
            int maxReceivePacketSize = mBluetoothSocket.getMaxReceivePacketSize();
            Log.d(TAG, "maxMaxTransmitPacketSize:" + maxMaxTransmitPacketSize);
            Log.d(TAG, "maxReceivePacketSize    :" + maxReceivePacketSize);
            Log.d(TAG, "getConnectionType       :" + mBluetoothSocket.getConnectionType());
            InputStream in = mBluetoothSocket.getInputStream();
            OutputStream out = mBluetoothSocket.getOutputStream();

            Log.d(TAG, "in:" + in);
            Log.d(TAG, "out:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void createL2capChannel(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac);
        try {
            Log.d(TAG, "before create");
            mBluetoothSocket = d.createL2capChannel(mPsm);
            Log.d(TAG, "mBtSocket:" + mBluetoothSocket);
            mBluetoothSocket.connect();
            int maxMaxTransmitPacketSize = mBluetoothSocket.getMaxTransmitPacketSize();
            int maxReceivePacketSize = mBluetoothSocket.getMaxReceivePacketSize();
            Log.d(TAG, "maxMaxTransmitPacketSize:" + maxMaxTransmitPacketSize);
            Log.d(TAG, "maxReceivePacketSize    :" + maxReceivePacketSize);
            Log.d(TAG, "getConnectionType       :" + mBluetoothSocket.getConnectionType());
            InputStream in = mBluetoothSocket.getInputStream();
            OutputStream out = mBluetoothSocket.getOutputStream();

            Log.d(TAG, "in:" + in);
            Log.d(TAG, "out:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void createInsecureL2capChannel(View view) {
        mDeviceMac = mMacEV.getText().toString().toUpperCase();
        BluetoothDevice d = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(mDeviceMac);
        try {
            Log.d(TAG, "before create");
            mBluetoothSocket = d.createInsecureL2capChannel(mPsm);
            Log.d(TAG, "mBtSocket:" + mBluetoothSocket);
            mBluetoothSocket.connect();
            int maxMaxTransmitPacketSize = mBluetoothSocket.getMaxTransmitPacketSize();
            int maxReceivePacketSize = mBluetoothSocket.getMaxReceivePacketSize();
            Log.d(TAG, "maxMaxTransmitPacketSize:" + maxMaxTransmitPacketSize);
            Log.d(TAG, "maxReceivePacketSize    :" + maxReceivePacketSize);
            Log.d(TAG, "getConnectionType       :" + mBluetoothSocket.getConnectionType());
            InputStream in = mBluetoothSocket.getInputStream();
            OutputStream out = mBluetoothSocket.getOutputStream();

            Log.d(TAG, "in:" + in);
            Log.d(TAG, "out:" + out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}