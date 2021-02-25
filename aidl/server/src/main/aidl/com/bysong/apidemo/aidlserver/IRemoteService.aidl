// IRemoteService.aidl
package com.bysong.apidemo.aidlserver;

// Declare any non-default types here with import statements

interface IRemoteService {
    //https://developer.android.com/guide/components/aidl#Create
    //void getPid() = 10;

    /** Request the process ID of this service, to do evil things with it. */
    int getPid()  = 10;

    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString)  = 11;
}