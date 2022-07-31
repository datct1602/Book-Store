package com.example.ps17284_chautandat_asm_duan.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps17284_chautandat_asm_duan.Activity.LoginActivity;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.Model.ThuThu;
import com.example.ps17284_chautandat_asm_duan.SQLite.DbHelper;

public class LoginDao {

    public DbHelper dbHelper;
    SQLiteDatabase db;


    public LoginDao (Context context){
        dbHelper = new DbHelper(context);
    }

    public boolean checkLogin(ThuThu login){
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from THUTHU where USERNAME = ? and PASSWORD = ?", new String[]{login.getUsername(), login.getPassword()});
        if (cursor.getCount() <= 0){
            return false;
        }
            return true;

    }

    public boolean insert(String username, String password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "USERNAME", username );
        values.put( "PASSWORD", password );
        long row = db.insert( "THUTHU", null, values );
        return (row > 0);
    }
    public boolean update(ThuThu model){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "PASSWORD",  model.getPassword() );
        int row = db.update( "THUTHU", values , "USERNAME =?", new String[]{model.getUsername()+""} );
        return (row > 0);
    }
    public boolean checkpass(String check){
        String pass = LoginActivity.CHECK.getPassword();
        if (!check.equals(pass)){
            return false;
        }

            return true;

    }

}
