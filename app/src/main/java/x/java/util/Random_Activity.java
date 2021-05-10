package x.java.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.util.Random;

public class Random_Activity extends AppCompatActivity {

    private static final String TAG = "Random_Activity";
    Random mRandom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.random_activity);
    }

    public void random(View view) {
        mRandom = new Random();
    }

    public void randomWithSeed(View view) {
        mRandom = new Random(Integer.parseInt(String.valueOf(((TextView)findViewById(R.id.text)).getText())));
    }

    public void nextInt(View view) {
        int next = mRandom.nextInt();
        Log.d(TAG, "next:" + next);
    }
}
