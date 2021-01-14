package com.bysong.android.apidemo;

import android.app.Application;

import org.bbs.android.log.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import no.nordicsemi.android.log.LogContract;
import no.nordicsemi.android.log.LogSession;
import no.nordicsemi.android.log.Logger;

public class App extends Application {

    private static final String TAG = App.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        initLog();

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

        Log.d(TAG, this + " onCreate.");
    }

    public static void npe() {
        String nullStr = null;
        if (nullStr.length() > 0) {
            ; // do nothing
        }
    }

    void initLog() {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger("com.bysong.android.apidemo");
        logger.setLevel(Level.ALL);
        logger.setUseParentHandlers(false);

        LogSession logSession = Logger.newSession(this, "api", "log");
        Handler h = new Handler(){

            @Override
            public void publish(LogRecord record) {
//                android.util.Log.d(TAG, "publish record:" + record);
                String msg = "";
                Log.Record r = (Log.Record)record;
                msg = r.getMessage();
                no.nordicsemi.android.log.Logger.log(logSession, LogContract.Log.Level.DEBUG, msg);
            }

            @Override
            public void flush() {

            }

            @Override
            public void close() throws SecurityException {

            }
        };
        logger.addHandler(h);

//        FileHandler fileHanlder = null;
//        try {
//            File dir = new File("/sdcard/log/");
//            dir.mkdirs();
//            fileHanlder = new FileHandler(dir.getPath() + "/log_%g_%u.log.txt");
//            logger.addHandler(fileHanlder);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Log.setLogger(logger);
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
