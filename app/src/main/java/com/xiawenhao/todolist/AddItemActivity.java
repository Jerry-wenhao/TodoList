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

import java.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity {
    private static final int MONTH_ADD_NUMBER = 1;
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

    private boolean showCalender = false;
    private LocalDate showLocalCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);
        ButterKnife.bind(this);
        calendarList.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            showLocalCalender = LocalDate.of(year, month + MONTH_ADD_NUMBER, dayOfMonth);
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            (actionBar).hide();
        }
    }

    @OnClick({R.id.select_date, R.id.add_item, R.id.back_button})
    void btnClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                Intent intent = new Intent(AddItemActivity.this, ListActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.select_date:
                if (!showCalender) {
                    calendarList.setVisibility(View.VISIBLE);
                    showCalender = true;
                } else {
                    calendarList.setVisibility(View.GONE);
                    showCalender = false;
                }
                break;
            case R.id.add_item:
                break;
        }
    }
}
