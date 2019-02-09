package com.aditya.repsfinder;

import android.content.Context;
import android.os.StrictMode;

import com.aditya.repsfinder.db.AppDB;
import com.aditya.repsfinder.services.RepoService;

import net.jcip.annotations.GuardedBy;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.room.Room;
import okhttp3.OkHttpClient;

public class Factory {
    private static final Object LOCK = new Object();
    public static final int TIMEOUT_IN_SECONDS = 60;

    @GuardedBy("LOCK")
    private static OkHttpClient mOkHttpClient;
    @GuardedBy("LOCK")
    private static RepoService mRepoService;
    @GuardedBy("LOCK")
    private static AppDB appDB;

    public static OkHttpClient getOkHTTPClient() {
        synchronized (LOCK) {
            if (mOkHttpClient == null) {
                mOkHttpClient = new OkHttpClient.Builder()
                        .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                        .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                        .build();
            }
        }
        return mOkHttpClient;
    }

    public static RepoService getSkillScoreService() {
        synchronized (LOCK) {
            if (mRepoService == null) {
                mRepoService = new RepoService();
            }
        }
        return mRepoService;
    }

    public static AppDB getAppDB(@NonNull Context context) {
        synchronized (LOCK) {
            if (mRepoService == null) {
                appDB=Room.databaseBuilder(context,
                        AppDB.class, "aadi_git_db").build();
            }
        }
        return appDB;
    }

    public static void setUpThreadPolicy() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

}
