package com.aditya.repsfinder;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import androidx.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

    private static final String TAG = "Utility";

    public static void setGothamFont(@NonNull TextView textView) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "gotham-book-1361523257.ttf"));
        }
    }

    public static void setGothamBoldFont(TextView textView) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "GOTHAM-BOLD.TTF"));
        }
    }

    public static void setOpenSansRegularFont(TextView textView) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "OpenSans-Regular.ttf"));
        }
    }

    public static void setOpenSansBoldFont(TextView textView) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "OpenSans-Bold.ttf"));
        }
    }

    public static void setOpenSansSemiBold(TextView textView) {
        if (textView != null) {
            textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "OpenSans-Bold.ttf"));
        }
    }

    public static int getAppVersion(@NonNull Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Log.d("UTILITY", "Package Manage Exception in getAppVersion" + "\n" + e.getMessage());
            return -1;
        }
    }

    public static String getAppVersionName(@NonNull Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "";
        }
    }

    public static JSONObject getResultJSONObject(@NonNull String body) throws JSONException {
        JSONObject jsonObject = new JSONObject(body);
        JSONObject result = jsonObject.getJSONObject("result");
        return result;
    }

    public static JSONArray getResultJSONArray(@NonNull String body) throws JSONException {
        JSONArray result = new JSONArray(body);
        return result;
    }

    public static String getResultString(@NonNull String body) throws JSONException {
        JSONObject jsonObject = new JSONObject(body);
        String s = jsonObject.getString("result");
        if (s == null) {
            s = jsonObject.getString("errors");
        }
        return s;
    }

    public static void getJSONFormat(@NonNull String key, @NonNull String value, @NonNull StringBuilder stringBuilder) {
        stringBuilder.append('"');
        stringBuilder.append(key);
        stringBuilder.append('"');
        stringBuilder.append(":");
        stringBuilder.append('"');
        stringBuilder.append(value);
        stringBuilder.append('"');
    }

}

