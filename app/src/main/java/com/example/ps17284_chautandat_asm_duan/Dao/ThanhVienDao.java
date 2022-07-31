package com.example.ps17284_chautandat_asm_duan.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.Model.ThanhVien;
import com.example.ps17284_chautandat_asm_duan.SQLite.DbHelper;

import java.util.ArrayList;

public class ThanhVienDao {

    DbHelper dbHelper;

    public ThanhVienDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<ThanhVien> getAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<ThanhVien> list = new ArrayList<>();
        String sql = "SELECT * FROM THANHVIEN";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String email = cursor.getString(2);
            String sdt = cursor.getString(3);
            String dob = cursor.getString(4);
            ThanhVien thanhVien = new ThanhVien(ma, ten, email, sdt, dob);
            list.add(thanhVien);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean insert(String ten, String email, String sdt, String dob){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "TENTV", ten );
        values.put( "EMAIL", email );
        values.put( "SDT", sdt );
        values.put( "NGAYSINH", dob );
        long row = db.insert( "THANHVIEN", null, values );
        return (row > 0);
    }
    public boolean update(ThanhVien model){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "TENTV", model.getTen() );
        values.put( "EMAIL", model.getEmail() );
        values.put( "SDT", model.getSdt() );
        values.put( "NGAYSINH", model.getDob() );
        int row = db.update( "THANHVIEN", values , "MATV=?", new String[]{model.getMaTv()+""} );
        return (row > 0);
    }
    public boolean delete(int ma){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete( "THANHVIEN", "MATV =?", new String[]{String.valueOf( ma )} );
        return (row > 0);
    }

}
