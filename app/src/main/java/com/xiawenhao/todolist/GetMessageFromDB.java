package com.xiawenhao.todolist;

import android.os.AsyncTask;

import java.util.List;

public class GetMessageFromDB extends AsyncTask<Void, Void, List<User>> {
    private UserDao userDao = MyApplication.getInstance().userDatabase().userDao();
    @Override
    protected List<User> doInBackground(Void... voids) {
        return userDao.getAll();
    }
}
