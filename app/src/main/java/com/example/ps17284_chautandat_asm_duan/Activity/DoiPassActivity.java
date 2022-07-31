package com.example.ps17284_chautandat_asm_duan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ps17284_chautandat_asm_duan.Dao.LoginDao;
import com.example.ps17284_chautandat_asm_duan.Model.ThuThu;
import com.example.ps17284_chautandat_asm_duan.R;

public class DoiPassActivity extends AppCompatActivity {

    Button btndoipass;
    EditText edtpass, edtpassnew, edtpassnew2;
    LoginDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_pass);

        dao = new LoginDao(DoiPassActivity.this);
        edtpass = findViewById(R.id.edtMatKhauHt);
        edtpassnew = findViewById(R.id.edtMkMoi);
        edtpassnew2 = findViewById(R.id.edtNhapLaiMk);
        btndoipass = findViewById(R.id.btnDoiPass);
        btndoipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MatKhauHt =edtpass.getText().toString();
                String MkMoi =  edtpassnew.getText().toString();
                String NhapLaiMk = edtpassnew2.getText().toString();
                String login = LoginActivity.CHECK.getUsername();
                if(!dao.checkpass(MatKhauHt)){
                    Toast.makeText(DoiPassActivity.this, "Mật khảu cũ không đúng",Toast.LENGTH_SHORT).show();
                } else {
                    if(!MkMoi.equals(NhapLaiMk)){
                        Toast.makeText(DoiPassActivity.this, "Mật khẩu nhập lại không đúng", Toast.LENGTH_SHORT).show();
                    }
                    else if(MatKhauHt.equals("")){
                        Toast.makeText(DoiPassActivity.this, "Vui lòng điền mật khẩu hiện tại", Toast.LENGTH_SHORT).show();
                    }
                    else if(MkMoi.equals("")){
                        Toast.makeText(DoiPassActivity.this, "Vui lòng điền mật khẩu mới", Toast.LENGTH_SHORT).show();}
                    else {
                        if(dao.update(new ThuThu(login,MkMoi))){
                            Toast.makeText(DoiPassActivity.this, "Đổi thành công",Toast.LENGTH_SHORT).show();
                            LoginActivity.CHECK.setPassword(MkMoi);
                        } else  {
                            Toast.makeText(DoiPassActivity.this,"Đổi mật khẩu thất bại",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

    }
}