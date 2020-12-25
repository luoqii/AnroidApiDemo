package x.java.lang;

import android.os.Bundle;

import com.bysong.android.apidemo.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import org.bbs.android.log.Log;

import java.util.Date;

public class SystemActivity extends AppCompatActivity {

    private static final String TAG = SystemActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        long timeMillis =  System.currentTimeMillis();
        Log.d(TAG, "timeMillis:" + timeMillis);

    }

    public void setProperty(View view) {
        String key = "key";
        String value = "value " + new Date();
        String previous = System.setProperty(key, value);
        Log.d(TAG, "previous:" + previous);

        String newValue = System.getProperty(key);
        Log.d(TAG, "newValue:" + newValue);
    }
}