package com.example.ps17284_chautandat_asm_duan.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ps17284_chautandat_asm_duan.Dao.LoginDao;
import com.example.ps17284_chautandat_asm_duan.Dao.PhieuMuonDao;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.Model.ThuThu;
import com.example.ps17284_chautandat_asm_duan.R;

import java.util.ArrayList;

public class SigninActivity extends AppCompatActivity {

    ArrayList<ThuThu> list = new ArrayList<>();
    LoginDao dao;
    EditText usernames, passwords;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        usernames = findViewById(R.id.edtUsernames);
        passwords = findViewById(R.id.edtPasswords);
        btnSignIn = findViewById(R.id.btnSignin);
        dao = new LoginDao(SigninActivity.this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String users = usernames.getText().toString();
                String passs = passwords.getText().toString();
                if(dao.insert(users, passs)){
                    new AlertDialog.Builder(SigninActivity.this).setMessage("Đăng ký thành công").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }).show();

                }
                else {
                    Toast.makeText(SigninActivity.this, "Bạn đã đăng ký thất bại ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}