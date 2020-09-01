package com.xiawenhao.todolist;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

public class ItemViewModel extends ViewModel {
    private List<ItemDate> itemDates;
    private ItemRepository itemRepository;

    public ItemViewModel(ItemRepository repository) {
        this.itemRepository = repository;
        initTaskList();
    }

    public void insertTask(ItemDate itemDate) {
        itemRepository.insertTask(itemDate);
        initTaskList();
    }

    public void initTaskList() {
        itemDates = itemRepository.getAllTask();
    }

    public void updateTaskList(ItemDate itemDate) {
    }

    public static class TaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {
        private final ItemRepository itemRepository;

        public TaskViewModelFactory() {
            itemRepository = new ItemRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ItemViewModel(itemRepository);
        }
    }
}
