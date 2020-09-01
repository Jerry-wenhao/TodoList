package com.xiawenhao.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDateDao {
    @Query("select * from itemdate")
    List<ItemDate> getAll();

    @Insert
    void insertAll(ItemDate... itemDates);
}
