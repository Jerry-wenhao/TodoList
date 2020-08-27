package com.xiawenhao.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity {
    @BindView(R.id.select_date)
    Button selectDate;
    @BindView(R.id.calender_list)
    CalendarView calendarList;
    @BindView(R.id.item_title)
    EditText itemTitle;
    @BindView(R.id.item_description)
    EditText itemDescription;
    @BindView(R.id.add_item)
    ImageButton addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);
        ButterKnife.bind(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            (actionBar).hide();
        }
    }
}
