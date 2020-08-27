package com.xiawenhao.todolist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class ItemDate {
    @NonNull
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private Date date;
    @ColumnInfo
    private boolean itemCreated;
    @ColumnInfo
    private boolean isReminded;

    @Ignore
    public ItemDate(String title, String description, Date date, boolean itemCreated, boolean isReminded) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.itemCreated = itemCreated;
        this.isReminded = isReminded;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDesc(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean itemCreated() {
        return itemCreated;
    }

    public void setCreated(boolean finished) {
        itemCreated = finished;
    }

    public boolean isReminded() {
        return isReminded;
    }

    public void setReminded(boolean itemReminded) {
        isReminded = itemReminded;
    }

}
