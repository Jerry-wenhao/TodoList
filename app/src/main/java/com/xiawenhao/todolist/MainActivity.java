package com.xiawenhao.todolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (getLoginStatus()) {
            skipToList();
        }

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
                Toast.makeText(this, "用户不存在", Toast.LENGTH_SHORT).show();
            } else {
                if (!truePassword.equals(password.getText().toString())) {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
                } else {
                    skipToList();
                    setLoginStatus();
                }
            }

        }
    }

    @OnTextChanged(R.id.username)
    void usernameInput(CharSequence text) {
        String usernamePattern = "^[a-zA-Z0-9]{3,12}$";
        boolean equalOfUsername = matchMessage(usernamePattern, text.toString());
        if (equalOfUsername) {
            hideButton(usernameErrorImage);
        } else {
            showButton(usernameErrorImage);
        }
        loginButtonState();
    }

    @OnTextChanged(R.id.password)
    void passwordInput(CharSequence text) {
        String passwordPattern = "^[\\w|\\W]{6,18}$";
        Boolean equalOfPassword = matchMessage(passwordPattern, text.toString());
        if (equalOfPassword) {
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
        if (username.getText().length() < 3 || username.getText().length() > 12 ||
                password.getText().length() < 6 || password.getText().length() > 18) {
            login.setEnabled(false);
            login.setTextColor(errorMessage);
            login.setBackgroundResource(R.drawable.error_login_button);
        } else {
            login.setEnabled(true);
            login.setTextColor(trueMessage);
            login.setBackgroundResource(R.drawable.true_login_button);
        }
    }

    public boolean getLoginStatus() {
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getBoolean(DeclareConstants.LOGIN_STATUS, false);
    }

    public void setLoginStatus() {
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sharedPref.edit().putBoolean(DeclareConstants.LOGIN_STATUS, true).apply();
    }
    private void showButton(ImageButton usernameErrorImage) {
        usernameErrorImage.setVisibility(View.VISIBLE);
    }

    private void hideButton(ImageButton usernameErrorImage) {
        usernameErrorImage.setVisibility(View.GONE);
    }

    private Boolean matchMessage(String patternString, String target) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    private void skipToList() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
        this.finish();
    }

}