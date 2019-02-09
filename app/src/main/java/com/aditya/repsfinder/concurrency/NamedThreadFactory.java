package com.aditya.repsfinder.concurrency;

import java.util.concurrent.ThreadFactory;

import androidx.annotation.NonNull;

public class NamedThreadFactory implements ThreadFactory {

    private String mName;

    public NamedThreadFactory(@NonNull String name) {
        mName = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, mName);
        t.setDaemon(true);
        return t;
    }
}
