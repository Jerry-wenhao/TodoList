package com.xiawenhao.todolist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase userDatabase;

    public static UserDatabase getInstance(Context context) {
        if (userDatabase == null) {
            userDatabase = Room.databaseBuilder(context, UserDatabase.class, DeclareConstants.LOCAL_DATABASE)
                    .build();
        }
        return userDatabase;
    }

    public abstract UserDao userDao();
}
