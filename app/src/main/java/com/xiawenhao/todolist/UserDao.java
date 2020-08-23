package com.xiawenhao.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface UserDao {

    @Query("SELECT * FROM user LIMIT 0,1")
    User getUserByName();

    @Insert
    void insertOne(User user);

    @Delete
    void delete(User user);
}
