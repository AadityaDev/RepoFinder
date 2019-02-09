package com.aditya.repsfinder.concurrency;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;

import java.util.concurrent.Executor;

public class UiThreadExecutor implements Executor {
    @Override
    public void execute(@NonNull Runnable command) {
        new Handler(Looper.getMainLooper()).post(command);
    }
}
