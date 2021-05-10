package x.java.security;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.security.SecureRandom;

public class SecureRandom_Activity extends AppCompatActivity {

    private static final String TAG = "Random_Activity";
    SecureRandom mRandom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.securerandom_activity);
    }

    public void random(View view) {
        mRandom = new SecureRandom();
    }

    public void randomWithSeed(View view) {
        mRandom = new SecureRandom(String.valueOf(((TextView)findViewById(R.id.text)).getText()).getBytes());
    }

    public void nextInt(View view) {
        int next = mRandom.nextInt();
        Log.d(TAG, "next:" + next);
    }
}
