package com.example.ps17284_chautandat_asm_duan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ps17284_chautandat_asm_duan.R;

public class GioiThieu1Activity extends AppCompatActivity {

    TextView tvSkip, tvGt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu1);
        tvGt1 = findViewById(R.id.tvgt1);
        tvSkip= findViewById(R.id.tvSkip);
        tvGt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioiThieu1Activity.this, GioiThieu2Activity.class);
                startActivity(intent);
            }
        });
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioiThieu1Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}