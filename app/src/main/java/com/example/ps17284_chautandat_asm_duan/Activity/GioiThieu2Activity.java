package com.example.ps17284_chautandat_asm_duan.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ps17284_chautandat_asm_duan.R;

public class GioiThieu2Activity extends AppCompatActivity {

    ImageView  back;
    TextView tvSkip, tvgt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu2);
        tvgt2 = findViewById(R.id.tvgt2);
        back = findViewById(R.id.imgback);
        tvgt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioiThieu2Activity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GioiThieu2Activity.this, GioiThieu1Activity.class);
                startActivity(intent);
            }
        });
    }
}