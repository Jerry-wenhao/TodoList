package com.xiawenhao.todolist;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.xiawenhao.todolist.MyApplication.userDatabase;

public class GetMessage {
    private static final String url = "https://twc-android-bootcamp.github.io/fake-data/data/user.json";

    public List<User> getUserList() {
        List<User> userList = getMessageFromLocalDatabase();
        if (userList == null || userList.size() == 0) {
            getMessageFromWeb();
        }
        return userList;

    }

    private List<User> getMessageFromLocalDatabase() {
        List<User> users = null;
        try {
            users = new GetMessageFromDB().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return users;
    }


    public void getMessageFromWeb() {
        OkHttpClient okHttpClient = new OkHttpClient();
        List<User> users = new ArrayList<>();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    User user = gson.fromJson(result, User.class);
                    users.add(user);
                    LoadToUserDatabase(user);
                }

            }

            private void LoadToUserDatabase(User user) {
                userDatabase.userDao().insertAll(user);
            }

        });
    }
}



