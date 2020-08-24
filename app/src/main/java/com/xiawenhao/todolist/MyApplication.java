package com.xiawenhao.todolist;


import android.app.Application;

import androidx.room.Room;

public class MyApplication extends Application {
    public static MyApplication myApplication;
    public static UserDatabase userDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public UserDatabase userDatabase() {
        userDatabase = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "LocalDataBase")
                .build();
        return userDatabase;
    }
}
