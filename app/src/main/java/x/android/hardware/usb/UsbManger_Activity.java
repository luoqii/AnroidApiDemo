package x.android.hardware.usb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.util.HashMap;
import java.util.Iterator;

public class UsbManger_Activity extends AppCompatActivity {

    private static final String TAG = UsbManger_Activity.class.getSimpleName();
    private UsbManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usb_manger_);

        mManager = (UsbManager) getSystemService(Context.USB_SERVICE);

        UsbAccessory[] accessoryList = mManager.getAccessoryList();
        if (null != accessoryList && accessoryList.length > 0) {
            for (UsbAccessory a : accessoryList) {
                Log.d(TAG, "UsbAccessory:" + a);
            }
        } else {
            Log.w(TAG, "no UsbAccessory");
        }

        HashMap<String, UsbDevice> deviceList = mManager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()){
            UsbDevice device = deviceIterator.next();
            Log.d(TAG, "UsbDevice:" + device);
        }
    }


}