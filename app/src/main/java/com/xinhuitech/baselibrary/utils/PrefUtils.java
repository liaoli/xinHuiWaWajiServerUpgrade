package com.xinhuitech.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.xinhui.upgradeapp.App;

/**
 * Created by Administrator on 2017/12/9.
 */

public class PrefUtils {
    static private PrefUtils sInstance;

    private SharedPreferences mPref;

    private PrefUtils() {
        mPref = App.getRootContext().getSharedPreferences("__doll_global_pref", Context.MODE_PRIVATE);
    }

    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }


    public PrefUtils setInt(String key, int value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putInt(key, value);
        editor.apply();
        return PrefUtils.sInstance;
    }


    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public PrefUtils setBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return PrefUtils.sInstance;
    }

    public long getLong(String key) {
        return mPref.getLong(key, 0);
    }

    public PrefUtils setLong(String key, long value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putLong(key, value);
        editor.apply();
        return PrefUtils.sInstance;
    }

    public String getString(String key) {
        return mPref.getString(key, "");
    }

    public PrefUtils setString(String key, String value) {
        SharedPreferences.Editor editor = mPref.edit();
        editor.putString(key, value);
        editor.apply();
        return PrefUtils.sInstance;
    }


    static public PrefUtils getInstance() {
        if (sInstance == null) {
            synchronized (PrefUtils.class) {
                if (sInstance == null) {
                    sInstance = new PrefUtils();
                }
            }
        }
        return sInstance;
    }
}
