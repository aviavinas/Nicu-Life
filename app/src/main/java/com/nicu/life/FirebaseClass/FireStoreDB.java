package com.nicu.life.FirebaseClass;

import android.app.Activity;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FireStoreDB {
    private static FireStoreDB single_instance = null;
    private FirebaseFirestore db;
    private Activity activity;
    private static final String TAG = "DBX";

    private FireStoreDB(Activity a) {
        activity = a;
        db = FirebaseFirestore.getInstance();
    }

    public static String PrettyTime(Date past) {
        try {
            Date now = new Date();
            long agoSec = TimeUnit.MILLISECONDS.toSeconds(now.getTime() - past.getTime()),
                    agoMin = TimeUnit.MILLISECONDS.toMinutes(now.getTime() - past.getTime()),
                    agoHour = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()),
                    agoDay = TimeUnit.MILLISECONDS.toDays(now.getTime() - past.getTime());

            if(agoSec<60) {
                return agoSec + " sec ago";
            } else if(agoMin<60) {
                return agoMin + " min ago";
            } else if(agoHour<24) {
                return agoHour + " hours ago";
            } else {
                return agoDay + " days ago";
            }
        }
        catch (Exception j){
            j.printStackTrace();
        }
        return "";
    }

    public static FireStoreDB getInstance(Activity a) {
        if (single_instance == null) {
            single_instance = new FireStoreDB(a);
        }
        return single_instance;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public static String getPushID(long num) {
        pin(String.valueOf(num));
        long n = num;
        String dig = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int m;
        String id = "";

        while(n>0) {
            m = Long.valueOf((n%(dig.length()))).intValue();
            id+= dig.charAt(m);
            n = n/(dig.length());
        }
        return id;
    }

    private static void pin(String m) {
        Log.d(TAG, m);
    }
}
