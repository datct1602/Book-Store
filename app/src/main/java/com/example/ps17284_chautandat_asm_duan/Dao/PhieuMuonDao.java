package com.example.ps17284_chautandat_asm_duan.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ps17284_chautandat_asm_duan.Model.LoaiSach;
import com.example.ps17284_chautandat_asm_duan.Model.PhieuMuon;
import com.example.ps17284_chautandat_asm_duan.SQLite.DbHelper;

import java.util.ArrayList;

public class PhieuMuonDao {

    DbHelper dbHelper;

    public PhieuMuonDao(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuMuon> getAll(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<PhieuMuon> list = new ArrayList<>();
        String sql = "SELECT * FROM PHIEUMUON";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Integer ma = cursor.getInt(0);
            String ten = cursor.getString(1);
            String sach = cursor.getString(2);
            String ngay = cursor.getString(3);
            PhieuMuon phieuMuon = new PhieuMuon(ma, ten, sach, ngay);
            list.add(phieuMuon);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return list;

    }

    public boolean insert(String ten, String sach, String ngay){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "NGUOIMUON", ten );
        values.put( "SACHMUON", sach );
        values.put( "NGAYMUON", ngay );
        long row = db.insert( "PHIEUMUON", null, values );
        return (row > 0);
    }
    public boolean update(PhieuMuon model){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put( "NGUOIMUON", model.getNguoiMuon() );
        values.put( "SACHMUON",  model.getSachMuon() );
        values.put( "NGAYMUON", model.getNgay() );
        int row = db.update( "PHIEUMUON", values , "MAPHIEU=?", new String[]{model.getMa()+""} );
        return (row > 0);
    }
    public boolean delete(int ma){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int row = db.delete( "PHIEUMUON", "MAPHIEU =?", new String[]{String.valueOf( ma )} );
        return (row > 0);
    }


}
