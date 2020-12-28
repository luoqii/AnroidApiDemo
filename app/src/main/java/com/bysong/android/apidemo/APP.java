package com.bysong.android.apidemo;

import android.app.Application;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;

import org.bbs.android.log.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class APP extends Application {

    private static final String TAG = APP.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        initFileProviderFiles();
    }

    void initFileProviderFiles(){
        File dir = getFilesDir();
        File pFile = new File(new File(dir, "properties"), "properties");
        pFile.getParentFile().mkdir();
        FileWriter w = null;
        try {
            pFile.createNewFile();
            w = new FileWriter(pFile);
            w.write("#comment\n");
            w.write("property1=1");
            w.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != w) {
                try {
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
