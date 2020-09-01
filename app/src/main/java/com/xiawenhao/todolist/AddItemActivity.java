package com.xiawenhao.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddItemActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {
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
    @BindString(R.string.date_format)
    String dateFormatter;
    @BindString(R.string.choose_yes)
    String chooseYes;
    @BindString(R.string.choose_no)
    String chooseNo;
    @BindColor(R.color.colorOfCheckBox)
    int selectDateColor;

    private boolean showCalender = false;
    private LocalDate showLocalCalender;
    private int year, month, day;
    boolean isDeadlineSet = false;
    boolean isTitleSet = false;

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

    @OnClick(R.id.select_date)
    public void selectDate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(chooseYes, (dialog, which) -> {
            String dateShow = String.format(Locale.getDefault(), dateFormatter, year, month + 1, day);
            selectDate.setText(dateShow);
            selectDate.setTextColor(selectDateColor);
            isDeadlineSet = true;
            addItemButton();
        });
        builder.setNegativeButton(chooseNo, (dialog, which) -> dialog.dismiss());
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.dialog_date, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        dialog.setView(dialogView);
        dialog.show();
        datePicker.init(year, month, day, this);
    }

    @OnClick({R.id.select_date, R.id.add_item, R.id.back_button})
    void btnClick(View view) {
        if (view.getId() == R.id.back_button) {
            Intent intent = new Intent(AddItemActivity.this, ListActivity.class);
            startActivity(intent);
            this.finish();
        }
    }

    private void addItemButton() {
        if (isDeadlineSet && isTitleSet) {
            addItem.setEnabled(true);
        } else {
            addItem.setEnabled(false);
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
