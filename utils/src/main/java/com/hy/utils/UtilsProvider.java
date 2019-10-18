package com.hy.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created time : 2019-10-18 19:38.
 *
 * @author HY
 */
public final class UtilsProvider extends ContentProvider {

    private static Context appContext;
    private static String appName;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        if (context != null) {
            appContext = context.getApplicationContext();
            appName = getApplicationName(context);
        }
        return true;
    }

    @Nullable
    public static Context getAppContext() {
        return appContext;
    }

    public static String getAppName() {
        return appName != null ? appName : "Utils";
    }

    public String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(e.getMessage(), e);
        }
        if (applicationInfo == null) return null;
        return packageManager.getApplicationLabel(applicationInfo).toString();
    }


    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
