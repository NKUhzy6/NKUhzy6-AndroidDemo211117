package com.example.demo211117;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSingleton{
    public final ThreadPoolExecutor threadPoolExecutor;
    private volatile static ThreadPoolSingleton instance;

    private ThreadPoolSingleton(){
        threadPoolExecutor = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(10));
    };

    public static ThreadPoolSingleton getInstance(){
        if (instance == null){
            synchronized (ThreadPoolSingleton.class){
                if (instance == null){
                    instance = new ThreadPoolSingleton();
                }
            }
        }
        return instance;
    }
}

