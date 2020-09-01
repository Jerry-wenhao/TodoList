package com.xiawenhao.todolist;

import java.util.List;

public class ItemRepository {
    private UserDatabase dataBase = UserDatabase.getInstance(MyApplication.getInstance());

    public List<ItemDate> getAllTask() {
        return dataBase.itemDateDao().getAll();
    }

    public void insertTask(ItemDate itemDate) {
        dataBase.itemDateDao().insertAll(itemDate);
    }
}
