package x.android.content;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.util.Date;

public class ContentResolver_Activity extends AppCompatActivity {

    private static final String TAG = ContentResolver_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);
    }

    public void insert(View view) {
        String title = "title insert_dericted_by_ContentResolver";
        String desc = "desc";
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, desc);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        // Add the date meta data to ensure the image is added at the front of the gallery
        values.put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis() / 1000);

        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Log.d(TAG, "uri:" + uri);
    }

    public void delete(View view){
        String title = "title insert_dericted_by_ContentResolver";
        String desc = "desc";

        String where = "title + ?";
        String[] selectionArgs = new String[] {
                title
        };
        int affectedRow = getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, where, selectionArgs);
        Log.d(TAG, "affectedRow:" + affectedRow);
    }

    public void update(View view){
        String title = "title insert_dericted_by_ContentResolver";
        String desc = "desc";

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DESCRIPTION, desc + " " + new Date());

        String where = "title + ?";
        String[] selectionArgs = new String[] {
                title
        };
        int affectedRow = getContentResolver().update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values, where, selectionArgs);
        Log.d(TAG, "affectedRow:" + affectedRow);
    }

}