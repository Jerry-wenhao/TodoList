package com.xiawenhao.todolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindColor;
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
    @BindColor(R.color.error_text)
    int trueMessage;
    @BindColor(R.color.error_text_background)
    int errorMessage;
    private boolean userNameError = false;
    private boolean passwordError = false;
    private GetMessage getMessage;
    private static final String truePassword = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getMessage = new GetMessage();
        getMessage.getUserList();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            (actionBar).hide();
        }
    }

    private void loginMessage() {
        User user = getMessage.getUserList().get(0);
        if (user != null) {
            if (!user.getName().equals(username.getText().toString())) {
                Toast.makeText(this, "用户名不存在", Toast.LENGTH_SHORT).show();
            } else {
                if (!truePassword.equals(password.getText().toString())) {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    this.finish();
                }
            }

        }
    }

    @OnTextChanged(R.id.username)
    void usernameInput(CharSequence text) {
        String usernamePattern = "^[a-zA-Z0-9]{3,12}$";
        boolean judgeUsername = regularJudge(usernamePattern, text.toString());
        if (judgeUsername) {
            hideButton(usernameErrorImage);
        } else {
            showButton(usernameErrorImage);
        }
        loginButtonState();
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
        loginButtonState();
    }

    @OnClick({R.id.username_error_image, R.id.password_error_image, R.id.login})
    void errorButton(View view) {
        switch (view.getId()) {
            case R.id.username_error_image:
                if (!userNameError) {
                    usernameErrorText.setVisibility(View.VISIBLE);
                    userNameError = true;
                } else {
                    usernameErrorText.setVisibility(View.GONE);
                    userNameError = false;
                }
                break;
            case R.id.password_error_image:
                if (!passwordError) {
                    passwordErrorText.setVisibility(View.VISIBLE);
                    passwordError = true;
                } else {
                    passwordErrorText.setVisibility(View.GONE);
                    passwordError = false;
                }
                break;
            case R.id.login:
                loginMessage();
                break;
        }
    }
    private void loginButtonState() {
        User user = getMessage.getUserList().get(0);
        if (user.getName().equals(username.getText().toString())
                && truePassword.equals(password.getText().toString())) {
            login.setEnabled(true);
            login.setTextColor(trueMessage);
            login.setBackgroundResource(R.drawable.true_login_button);
        } else {
            login.setEnabled(false);
            login.setTextColor(errorMessage);
            login.setBackgroundResource(R.drawable.error_login_button);
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