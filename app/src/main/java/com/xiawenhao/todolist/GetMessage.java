package com.xiawenhao.todolist;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetMessage {

    private UserDatabase userDatabase = UserDatabase.getInstance(MyApplication.getInstance());
    private static final String url = "https://twc-android-bootcamp.github.io/fake-data/data/user.json";

    public List<User> getUserList() {
        List<User> userList = getMessageFromLocalDatabase();
        if (userList.size() == 0) {
            userList = getMessageFromWeb();
        }
        return userList;
    }

    private List<User> getMessageFromLocalDatabase() {
        return userDatabase.userDao().getAll();
    }


    public List<User> getMessageFromWeb() {
        OkHttpClient okHttpClient = new OkHttpClient();
        final List<User> users = new ArrayList<>();
        Request request = new Request.Builder().get().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String result = Objects.requireNonNull(response.body()).string();
                    Gson gson = new Gson();
                    User user = gson.fromJson(result, User.class);
                    users.add(user);
                    LoadToUserDatabase(user);
                }

            }
        });
        return users;
    }

    private void LoadToUserDatabase(User user) {
        userDatabase.userDao().insertAll(user);
    }
}
