package com.bysong.android.apidemo;

import android.annotation.SuppressLint;
import android.app.Application;

import org.bbs.android.commonlib.ExceptionCatcher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class APP extends Application {

    private static final String TAG = APP.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

//        ExceptionCatcher.attachExceptionHandler(this);
        initFileProviderFiles();

//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//
//            @SuppressLint("WorldReadableFiles")
//            @Override
//            public void uncaughtException(Thread thread, Throwable ex) {
//                PrintStream writer = null;
//                try {
//                    writer.write(1);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });

//        npe();
    }

    public static void npe() {
        String nullStr = null;
        if (nullStr.length() > 0) {
            ; // do nothing
        }
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
