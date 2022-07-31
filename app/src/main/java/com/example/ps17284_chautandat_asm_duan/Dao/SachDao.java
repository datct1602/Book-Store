package com.example.ps17284_chautandat_asm_duan.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.Model.Sach;
import com.example.ps17284_chautandat_asm_duan.SQLite.DbHelper;

import java.util.ArrayList;

public class SachDao {

    DbHelper dbHelper;

    public SachDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Sach> list = new ArrayList<>();
        String sql = "SELECT * FROM SACH";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String tacGia = cursor.getString(2);
            String gia = cursor.getString(3);
            Sach sach = new Sach(ma, ten, tacGia,gia);
            list.add(sach);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean insert(String ten, String tacGia, String gia){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "TENSACH", ten );
        values.put( "TACGIA", tacGia );
        values.put( "GIA", gia );
        long row = db.insert( "SACH", null, values );
        return (row > 0);
    }
    public boolean update(Sach model){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "TENSACH", model.getTen() );
        values.put( "TACGIA", model.getTacgia() );
        values.put( "GIA", model.getGia() );
        int row = db.update( "SACH", values , "MASACH=?", new String[]{model.getMa()+""} );
        return (row > 0);
    }
    public boolean delete(int ma){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete( "SACH", "MASACH =?", new String[]{String.valueOf( ma )} );
        return (row > 0);
    }

}
