package com.xiawenhao.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AddItemActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {
    @BindView(R.id.select_date)
    Button selectDate;
    @BindView(R.id.calender_list)
    CalendarView calendarList;
    @BindView(R.id.remind_switch)
    SwitchCompat isReminded;
    @BindView(R.id.item_title)
    EditText itemTitle;
    @BindView(R.id.item_description)
    EditText itemDescription;
    @BindView(R.id.add_item)
    ImageButton addItem;
    @BindString(R.string.date_format)
    String dateFormatter;
    @BindColor(R.color.colorOfCheckBox)
    int selectDateColor;
    @BindString(R.string.choose_yes)
    String chooseYes;
    @BindString(R.string.choose_no)
    String chooseNo;
    @BindView(R.id.checkBox)
    CheckBox hasDone;

    private boolean showCalender = false;
    private LocalDate showLocalCalender;
    private int year, month, day;
    private ItemViewModel itemViewModel;
    boolean deadlineSet = false;
    boolean titleSet = false;
    private boolean isEditPage;
    private ItemDate newItemDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat);
        ButterKnife.bind(this);

        initTaskViewModel();
        Intent intent = getIntent();
        ItemDate taskToEdit = (ItemDate) intent.getSerializableExtra(DeclareConstants.EDIT_TASK_INFO);
        if (taskToEdit != null) {
            isEditPage = true;
            newItemDate = taskToEdit;
            initDate();
            createEditPage(taskToEdit);
        } else {
            isEditPage = false;
            initDate();
            addItemButton();
        }

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            (actionBar).hide();
        }
    }

    private void initTaskViewModel() {
        ItemViewModel.TaskViewModelFactory factory = new ItemViewModel.TaskViewModelFactory();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        new Thread() {
            @Override
            public void run() {
                itemViewModel = viewModelProvider.get(ItemViewModel.class);
            }
        }.start();
    }

    @OnClick(R.id.select_date)
    public void selectDate() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(chooseYes, (dialog, which) -> {
            String dateShow = String.format(Locale.getDefault(), dateFormatter, year, month + 1, day);
            selectDate.setText(dateShow);
            selectDate.setTextColor(selectDateColor);
            deadlineSet = true;
            addItemButton();
        });
        builder.setNegativeButton(chooseNo, (dialog, which) -> dialog.dismiss());
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(this, R.layout.dialog_date, null);
        final DatePicker datePicker = dialogView.findViewById(R.id.date_picker);
        datePicker.setMinDate(new Date().getTime());
        dialog.setView(dialogView);
        dialog.show();
        datePicker.init(year, month, day, this);
    }

    private void setDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @OnTextChanged(value = R.id.item_title, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextChanged() {
        titleSet = itemTitle.getText().toString().length() > 0;
        addItemButton();
    }


    @OnClick({R.id.back_button})
    void btnClick(View view) {
        if (view.getId() == R.id.back_button) {
            goToListPage();
        }
    }

    @OnClick(R.id.add_item)
    public void onClickAddItemBtn() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        new Thread() {
            @Override
            public void run() {
                if (isEditPage) {
                    if (newItemDate != null) {
                        newItemDate.setHasDone(hasDone.isChecked());
                        newItemDate.setDate(calendar.getTime());
                        newItemDate.setReminded(isReminded.isChecked());
                        newItemDate.setTitle(itemTitle.getText().toString());
                        newItemDate.setDescription(itemDescription.getText().toString());
                    }
                    itemViewModel.updateTaskList(newItemDate);
                } else {
                    newItemDate = new ItemDate(itemTitle.getText().toString(), itemDescription.getText().toString(),
                            hasDone.isChecked(), isReminded.isChecked(), calendar.getTime());
                    itemViewModel.insertTask(newItemDate);
                }
            }
        }.start();
        goToListPage();
    }

    private void goToListPage() {
        Intent intent = new Intent(AddItemActivity.this, ListActivity.class);
        startActivity(intent);
        this.finish();
    }


    private void createEditPage(ItemDate itemDate) {
        deadlineSet = true;
        titleSet = true;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(itemDate.getDate());
        hasDone.setChecked(itemDate.isHasDone());
        selectDate.setText(String.format(Locale.getDefault(), dateFormatter,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)));
        selectDate.setTextColor(selectDateColor);
        isReminded.setChecked(itemDate.isReminded());
        itemTitle.setText(itemDate.getTitle());
        itemDescription.setText(itemDate.getDescription());
    }

    private void addItemButton() {
        if (deadlineSet && titleSet) {
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

    private void initDate() {
        if (isEditPage) {
            setDate(newItemDate.getDate());
        } else {
            setDate(null);
        }
    }
}
