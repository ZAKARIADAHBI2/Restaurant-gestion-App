package com.example.kharaaaaa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.kharaaaaa.R;
import com.example.kharaaaaa.adapters.DatabaseAdapter;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout mLoginEmail;
    private TextInputLayout mLoginPassword;
    private DatabaseAdapter databaseAdapter;
  private View mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseAdapter = DatabaseAdapter.getInstance(this);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) mToolbar);
        getSupportActionBar().setTitle("Log in to your account");

        mLoginEmail = findViewById(R.id.login_email);
        mLoginPassword = findViewById(R.id.login_password);
    }

    public void onLoginPressed(View view) {
        String email = mLoginEmail.getEditText().getText().toString();
        String password = mLoginPassword.getEditText().getText().toString();

        if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)) {
            loginUser(email, password);
        }
    }

    private void loginUser(String email, String password) {
        boolean status = databaseAdapter.signIn(email, password);
        if (status) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials.", Toast.LENGTH_LONG).show();
        }
    }

    public void onCreateAccountPressed(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
