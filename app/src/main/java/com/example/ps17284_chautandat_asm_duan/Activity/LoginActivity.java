package com.example.ps17284_chautandat_asm_duan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ps17284_chautandat_asm_duan.Dao.LoginDao;
import com.example.ps17284_chautandat_asm_duan.Model.ThuThu;
import com.example.ps17284_chautandat_asm_duan.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    LoginDao dao;
    TextView sigin;
    public static ThuThu CHECK = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        sigin =findViewById(R.id.createAcc);
        dao = new LoginDao(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _username = username.getText().toString();
                String _password = password.getText().toString();
                ThuThu _login = new ThuThu(_username,_password);
                if (dao.checkLogin(_login)){
                    CHECK = _login;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Sai tên tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}