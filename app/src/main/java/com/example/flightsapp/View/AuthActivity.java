package com.example.flightsapp.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flightsapp.ViewModel.AuthViewModel;
import com.example.flightsapp.R;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity {

    private AuthViewModel authViewModel;
    private EditText enterLogin, enterPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        init();
    }

    private void init() {
        enterLogin = findViewById(R.id.enterLogin);
        enterPassword = findViewById(R.id.enterPassword);
        this.authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
    }

    @Override
    protected void onStart() { // Переход на MainActivity, если пользователь уже вошел ранее
        super.onStart();
    }

    public void onClickSignUp(View view) { // Метод кнопки регистрации
        if (TextUtils.isEmpty(enterLogin.getText().toString()) || TextUtils.isEmpty(enterPassword
                .getText().toString())) { // Проверка заполнения полей
            Toast.makeText(getApplicationContext(), "Ошибка - логин или пароль не введён!",
                    Toast.LENGTH_SHORT).show();
        } else if (enterPassword.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Пароль должен иметь длину не менее 6 символов", Toast.LENGTH_SHORT).show();
        } else { // Процесс регистрации
            boolean regCheck = authViewModel.registration(enterLogin.getText().toString(), enterPassword.getText().toString());
            if (regCheck) {
                Toast.makeText(getApplicationContext(), "Успешная регистрация", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "Ошибка регистрации", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onClickSignIn(View view) { // Метод кнопки входа
        if (TextUtils.isEmpty(enterLogin.getText().toString()) || TextUtils.isEmpty(enterPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Ошибка - логин или пароль не введён!", Toast.LENGTH_SHORT).show();
        } else if (enterPassword.getText().toString().length() < 4) {
            Toast.makeText(getApplicationContext(), "Невозможная длина пароля", Toast.LENGTH_SHORT).show();
        } else {
            int authCheck = authViewModel.authorization(enterLogin.getText().toString(), enterPassword.getText().toString());
                if (authCheck == 1) {
                    Toast.makeText(getApplicationContext(), "Вход в панель администратора выполнен успешно", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AuthActivity.this, AdminPanelActivity.class);
                    startActivity(i); // Переход на экран
                    finish();
                } else if (authCheck == 0){
                    Toast.makeText(getApplicationContext(), "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AuthActivity.this, MainActivity.class);
                    i.putExtra("airlineId", "tables");
                    startActivity(i); // Переход на главный экран
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Неверный логин/пароль", Toast.LENGTH_SHORT).show();
                }
        }
    }
}