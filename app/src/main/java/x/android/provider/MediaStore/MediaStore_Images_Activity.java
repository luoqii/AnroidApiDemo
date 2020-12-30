package x.android.provider.MediaStore;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

public class MediaStore_Images_Activity extends AppCompatActivity {

    private static final String TAG = MediaStore_Images_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_store_images);
    }

    public void insertImage(View view) {
        String title = "title";
        String desc = "desc";
        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ALPHA_8);
        String uri = MediaStore.Images.Media.insertImage(getContentResolver(),
                bitmap,
                title,
                desc);
        Log.d(TAG, "uri:" + uri);
    }

    public void Media_Query(View view) {
        String title = "title";
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] projection = new String[]{MediaStore.Images.ImageColumns.TITLE,
                MediaStore.Images.ImageColumns.DESCRIPTION,
                MediaStore.Images.ImageColumns.ALBUM};
        Cursor c = MediaStore.Images.Media.query(getContentResolver(),
                uri,
                projection,
                MediaStore.Images.ImageColumns.TITLE + " LIKE ?",
                new String[]{title + "%"},
                "" + MediaStore.Images.ImageColumns.DATE_ADDED + " desc");
        try {
            if (null != c && c.getCount() > 0 && c.moveToFirst() ) {
                do {
                    String imageTitle = c.getString(0);
                    String desc = c.getString(1);
                    Log.d(TAG, "imageTitle:" + imageTitle + " desc:" + desc);
                } while (c.moveToNext());
             }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != c) {
                c.close();
            }
        }
    }

    public void insert_dericted_by_ContentResolver(View view) {
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
}