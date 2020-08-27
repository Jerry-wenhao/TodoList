package com.xiawenhao.todolist;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDateDao {
    @Query("select * from Itemdate")
    LiveData<List<ItemDate>> getAll();

    @Insert
    void save(ItemDate... reminder);

    @Delete
    void delete(ItemDate reminder);
}
