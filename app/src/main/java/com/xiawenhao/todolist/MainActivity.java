package com.xiawenhao.todolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.username_error_image)
    ImageButton usernameErrorImage;
    @BindView(R.id.username_error_text)
    TextView usernameErrorText;
    @BindView(R.id.password_error_image)
    ImageButton passwordErrorImage;
    @BindView(R.id.password_error_text)
    TextView passwordErrorText;
    @BindView(R.id.login)
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            (actionBar).hide();
        }
    }

    @OnTextChanged(R.id.username)
    void usernameInput(CharSequence text) {
        String usernamePattern = "^[\\d|a-z|A-Z]{3,12}$";
        Boolean judgeUsername = regularJudge(usernamePattern, text.toString());
        if (judgeUsername) {
            hideButton(usernameErrorImage);
        } else {
            showButton(usernameErrorImage);
        }
    }

    @OnTextChanged(R.id.password)
    void passwordInput(CharSequence text) {
        String passwordPattern = "^[\\w|\\W]{6,18}$";
        Boolean judgePassword = regularJudge(passwordPattern, text.toString());
        if (judgePassword) {
            hideButton(passwordErrorImage);
        } else {
            showButton(passwordErrorImage);
        }
    }

    @OnClick({R.id.username_error_image, R.id.password_error_image})
    void errorButton(View view) {
        switch (view.getId()) {
            case R.id.username_error_image:
                usernameErrorText.setVisibility(View.VISIBLE);
                break;
            case R.id.password_error_text:
                passwordErrorText.setVisibility(View.VISIBLE);
                break;
        }
    }


    private void showButton(ImageButton usernameErrorImage) {
        usernameErrorImage.setVisibility(View.VISIBLE);
    }

    private void hideButton(ImageButton usernameErrorImage) {
        usernameErrorImage.setVisibility(View.GONE);
    }

    private Boolean regularJudge(String patternString, String target) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }


}