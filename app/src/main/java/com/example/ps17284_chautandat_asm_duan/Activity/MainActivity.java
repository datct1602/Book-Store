package com.example.ps17284_chautandat_asm_duan.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.ps17284_chautandat_asm_duan.Fragment.LoaiSachFragment;
import com.example.ps17284_chautandat_asm_duan.Fragment.PhieuMuonFragment;
import com.example.ps17284_chautandat_asm_duan.Fragment.SachFragment;
import com.example.ps17284_chautandat_asm_duan.Fragment.ThanhVienFragment;
import com.example.ps17284_chautandat_asm_duan.Fragment.ThongKeFragment;
import com.example.ps17284_chautandat_asm_duan.R;
import com.google.android.material.navigation.NavigationView;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    MeowBottomNavigation meow;
    ImageView more;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Button dongY, huy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meow = findViewById(R.id.meow);
        more = findViewById(R.id.imgMore);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        huy = findViewById(R.id.btnHuy);




        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener( this );


        meow.add(new MeowBottomNavigation.Model(1, R.drawable.book));
        meow.add(new MeowBottomNavigation.Model(2, R.drawable.lbook));
        meow.add(new MeowBottomNavigation.Model(3, R.drawable.ticket));
        meow.add(new MeowBottomNavigation.Model(4, R.drawable.mem));
        meow.add(new MeowBottomNavigation.Model(5, R.drawable.thongke));

        meow.show(3,true);
        showFragment(new PhieuMuonFragment());
        meow.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        showFragment(new SachFragment());
                        break;
                    case 2:
                        showFragment(new LoaiSachFragment());
                        break;
                    case 3:
                        showFragment(new PhieuMuonFragment());
                        break;
                    case 4:
                        showFragment(new ThanhVienFragment());
                        break;
                    case 5:
                        showFragment(new ThongKeFragment());
                        break;
                }
                return null;
            }
        });

    }

    private void showFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_phieuMuon:
                getSupportFragmentManager().beginTransaction().replace( R.id.frameLayout, new PhieuMuonFragment() ).commit();
                drawerLayout.closeDrawer( Gravity.LEFT );
                meow.show(3,true);
                break;
            case R.id.nav_sach:
                getSupportFragmentManager().beginTransaction().replace( R.id.frameLayout, new SachFragment() ).commit();
                drawerLayout.closeDrawer( Gravity.LEFT );
                meow.show(1,true);
                break;
            case R.id.nav_loaiSach:
                getSupportFragmentManager().beginTransaction().replace( R.id.frameLayout, new LoaiSachFragment() ).commit();
                drawerLayout.closeDrawer( Gravity.LEFT );
                meow.show(2,true);
                break;
            case R.id.nav_thanhVien:
                getSupportFragmentManager().beginTransaction().replace( R.id.frameLayout, new ThanhVienFragment() ).commit();
                drawerLayout.closeDrawer( Gravity.LEFT );
                meow.show(4,true);
                break;
            case R.id.nav_thongKe:
                getSupportFragmentManager().beginTransaction().replace( R.id.frameLayout, new ThongKeFragment() ).commit();
                drawerLayout.closeDrawer( Gravity.LEFT );
                meow.show(5,true);
                break;
            case R.id.nav_doiMk:
                Intent intent = new Intent(MainActivity.this, DoiPassActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_dangXuat:
                new AlertDialog.Builder(MainActivity.this).setMessage("Bạn có chắc chắn muốn đăng xuất không ?")
                        .setPositiveButton("Đăng xuất", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(MainActivity.this, GioiThieu1Activity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
                break;

        }
        return true;
    }
}