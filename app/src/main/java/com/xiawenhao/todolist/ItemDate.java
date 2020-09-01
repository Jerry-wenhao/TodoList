package com.xiawenhao.todolist;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "itemDate")
public class ItemDate implements Serializable {
    @NonNull
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String description;
    @ColumnInfo
    private boolean hasDone;
    @ColumnInfo
    private boolean isReminded;
    @ColumnInfo
    private Date date;

    public ItemDate(String title, String description, boolean hasDone, boolean isReminded, Date date) {
        this.title = title;
        this.description = description;
        this.hasDone = hasDone;
        this.isReminded = isReminded;
        this.date = date;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isHasDone() {
        return hasDone;
    }

    public void setHasDone(boolean hasDone) {
        this.hasDone = hasDone;
    }

    public boolean isReminded() {
        return isReminded;
    }

    public void setReminded(boolean reminded) {
        isReminded = reminded;
    }

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", hasDone=" + hasDone +
                ", isReminded=" + isReminded +
                ", dateOfRemind='" + date + '\'' +
                '}';
    }
}