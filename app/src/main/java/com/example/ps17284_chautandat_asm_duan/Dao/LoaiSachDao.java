package com.example.ps17284_chautandat_asm_duan.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.SQLite.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class LoaiSachDao {

    DbHelper dbHelper;

    public LoaiSachDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiSach> getAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<LoaiSach> list = new ArrayList<>();
        String sql = "SELECT * FROM LOAISACH";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            LoaiSach loaiSach = new LoaiSach(ma, ten);
            list.add(loaiSach);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean insert(String ten){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "TENLOAI", ten );
        long row = db.insert( "LOAISACH", null, values );
        return (row > 0);
    }
    public boolean update(LoaiSach model){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "TENLOAI", model.getTenLoai() );
        int row = db.update( "LOAISACH", values , "MALOAI=?", new String[]{model.getMaLoai()+""} );
        return (row > 0);
    }
    public boolean delete(int ma){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete( "LOAISACH", "MALOAI =?", new String[]{String.valueOf( ma )} );
        return (row > 0);
    }

}
